package com.social.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.config.JwtProvider;
import com.social.models.User;
import com.social.repository.UserRepository;
import com.social.request.LoginRequest;
import com.social.response.AuthRespose;
import com.social.service.CustomUserDetailsService;
import com.social.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/signup")
	public AuthRespose createUser(@RequestBody User newUser) throws Exception{
		User isExist=userService.findUseryEmail(newUser.getEmail());
		if (isExist!=null) {
			throw new Exception("email is alredy regiserer try new one");
		}
		List<User> userList=new ArrayList<>();
		User u1=new User();
		u1.setId(newUser.getId());
		u1.setFirstName(newUser.getFirstName());
		u1.setLastName(newUser.getLastName());
		u1.setEmail(newUser.getEmail());
		u1.setPassword(passwordEncoder.encode(newUser.getPassword()));
		
		User savedUser= userRepository.save(u1);
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		System.out.println(authentication+"auth generated");
		String token=JwtProvider.generateToken(authentication);
		System.out.println(token+"token generated");
		AuthRespose res=new AuthRespose(token,"token generated succsefully");
		return res;
	}
	
	@PostMapping("/signin")
	public AuthRespose signIn(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		System.out.println(authentication+"auth generated");
		String token=JwtProvider.generateToken(authentication);
		System.out.println(token+"token generated");
		AuthRespose res=new AuthRespose(token,"login  succsefully");
		return res;
	}

	private Authentication authenticate(String email, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid usernname");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password noy matching");
		}
		//Authentication auth=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
