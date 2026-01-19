package com.ey.dto.request;

import com.ey.enums.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterRequest {

	    @NotBlank(message = "First name is required")
	    private String firstName;

	    @NotBlank(message = "Last name is required")
	    private String lastName;

	    @NotBlank(message = "Email is required")
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotBlank(message = "Phone number is required")
	    private String phoneNumber;

	    @NotBlank(message = "Password is required")
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String password;

	    @NotNull(message = "Role is required")
	    private UserRole role; 

		public UserRegisterRequest(@NotBlank(message = "First name is required") String firstName,
				@NotBlank(message = "Last name is required") String lastName,
				@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
				@NotBlank(message = "Phone number is required") String phoneNumber,
				@NotBlank(message = "Password is required") @Size(min = 6, message = "Password must be at least 6 characters") String password,
				@NotNull(message = "Role is required") UserRole role) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.password = password;
			this.role = role;
		}

		public UserRegisterRequest() {
			super();
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}
	    
	    
	    

	}


