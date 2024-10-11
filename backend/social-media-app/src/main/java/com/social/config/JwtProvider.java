package com.social.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

	private static SecretKey key=Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt=Jwts.builder().issuer("amirt")
				.issuedAt(new Date())
				.claim("email",auth.getName())
				.signWith(key)
				.compact();
				
		return jwt;
	}
	
	public static String generateEmailFromJwtToken(String jwt) {
		// bearer 7placetoken starts
		jwt=jwt.substring(7);
		
		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email=String.valueOf(claims.get("email"));
		
		
		return email;
	}
	

}
