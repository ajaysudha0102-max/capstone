package com.ey.dto.response;

import java.time.LocalDateTime;

import com.ey.enums.PaymentMethod;
import com.ey.enums.PaymentStatus;

public class PaymentResponse {

    private Long paymentId;
    private Long bookingId;
    private double amount;
    private PaymentMethod method;
    private PaymentStatus status;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public PaymentMethod getMethod() {
		return method;
	}
	public void setMethod(PaymentMethod method) {
		this.method = method;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
    
    

}
