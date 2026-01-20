package com.ey.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
	
    @NotBlank(message = "email is required")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

  
    
}