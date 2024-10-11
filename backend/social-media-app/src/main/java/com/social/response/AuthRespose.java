package com.social.response;

public class AuthRespose {

	private String token;
	private String message;
	public AuthRespose() {
		// TODO Auto-generated constructor stub
	}
	public AuthRespose(String token, String message) {
		super();
		this.token = token;
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
