package com.ey.dto.response;

import com.ey.enums.UserRole;

public class LoginResponse {

	
	private String token;
	private UserRole role;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public LoginResponse(String token, UserRole role) {
		super();
		this.token = token;
		this.role = role;
	}
	public LoginResponse() {
		super();
	}
	
	
	
	
}
