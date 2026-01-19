package com.ey.mapper;

import org.springframework.stereotype.Component;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Payment;

@Component
public class PaymentMapper {
	
	
	public static Payment requestToEntity(PaymentRequest request) {
		Payment payment = new Payment();
		
return payment;		
	}
	
	public static PaymentResponse entityToResponse(Payment payment) {
		PaymentResponse response = new PaymentResponse();
		
		response.setPaymentId(payment.getPaymentId());
		response.setBookingId(payment.getBooking().getBookingId());
		response.setAmount(payment.getAmount());
		response.setMethod(payment.getPaymentMethod());
		response.setStatus(payment.getPaymentStatus());
		return response;
	}

}
