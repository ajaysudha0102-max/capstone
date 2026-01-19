package com.ey.dto.response;

import com.ey.enums.UserRole;

public class LoginResponse {

	
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public LoginResponse(String token) {
		super();
		this.token = token;
	}
	public LoginResponse() {
		super();
	}
	
	
	
	
}
