package com.ey.dto.request;

import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull(message="booking Id is Required")
	private Long bookingId;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	
	

}
