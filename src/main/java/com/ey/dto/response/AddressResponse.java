package com.ey.dto.response;

import java.time.LocalDateTime;

public class AddressResponse {
	
	 private Long id;
	    private String houseNumber;
	    private String street;
	    private String city;
	    private String state;
	    private String pincode;
	    private LocalDateTime createdAt;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getHouseNumber() {
			return houseNumber;
		}
		public void setHouseNumber(String houseNumber) {
			this.houseNumber = houseNumber;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
	    
	    
	    

}
