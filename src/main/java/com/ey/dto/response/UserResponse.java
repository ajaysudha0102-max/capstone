package com.ey.dto.response;

import java.time.LocalDateTime;

import com.ey.enums.UserRole;

public class UserResponse {
	
	 private Long id;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phoneNumber;
	    private UserRole role;
	    private LocalDateTime createdAt;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public UserRole getRole() {
			return role;
		}
		public void setRole(UserRole role) {
			this.role = role;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

	    
}
