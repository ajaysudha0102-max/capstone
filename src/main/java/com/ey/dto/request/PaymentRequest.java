package com.ey.dto.request;

import com.ey.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public class PaymentRequest {
	
	@NotNull(message="booking Id is Required")
	private Long bookingId;
	
	@NotNull
	private PaymentMethod paymentMethod;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
	

}
