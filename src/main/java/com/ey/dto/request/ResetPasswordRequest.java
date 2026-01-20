package com.ey.dto.request;

import jakarta.validation.constraints.*;

public class ResetPasswordRequest {
	
    @NotBlank(message = "email is required")
	private String email;
    private String otp;

    @NotBlank(message = "Old password is required")
  
    @NotBlank(message = "New password is required")
    @Size(min = 6, message = "New password must be at least 6 characters")
    private String newPassword;

	

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    
    

}

