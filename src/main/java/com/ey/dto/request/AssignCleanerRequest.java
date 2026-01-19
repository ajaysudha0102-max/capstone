package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;

public class AssignCleanerRequest {
	
	@NotNull(message="boking id is required")
	private Long bookingId;
	 
	@NotNull(message="cleanerId is required")
	private Long cleanerId;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getCleanerId() {
		return cleanerId;
	}

	public void setCleanerId(Long cleanerId) {
		this.cleanerId = cleanerId;
	}
	
	

}
