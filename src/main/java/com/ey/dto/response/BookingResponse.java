package com.ey.dto.response;

import java.time.LocalDate;

import com.ey.enums.BookingStatus;
import com.ey.enums.PaymentStatus;

public class BookingResponse {

    private Long bookingId;
    private Long customerId;
    private Long serviceId;
    private Long addressId;

    private LocalDate serviceDate;
    private BookingStatus bookingStatus;
  //  private PaymentStatus paymentStatus;
    private double totalPrice;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public LocalDate getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(LocalDate serviceDate) {
		this.serviceDate = serviceDate;
	}
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
//	public PaymentStatus getPaymentStatus() {
//		return paymentStatus;
//	}
//	public void setPaymentStatus(PaymentStatus paymentStatus) {
//		this.paymentStatus = paymentStatus;
//	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
    
    

}
