package com.jwtimplementation.response;

public class LoginResponse {
	
	private String token;
	private long tokenExpirTime;
	
	public LoginResponse(String token, long tokenExpirTime) {
		super();
		this.token = token;
		this.tokenExpirTime = tokenExpirTime;
	}
	
	public LoginResponse() {
		
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getTokenExpirTime() {
		return tokenExpirTime;
	}
	public void setTokenExpirTime(long tokenExpirTime) {
		this.tokenExpirTime = tokenExpirTime;
	}
	

}
