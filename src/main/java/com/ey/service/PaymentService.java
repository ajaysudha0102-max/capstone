package com.ey.service;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.ey.dto.request.PaymentRequest;

import jakarta.validation.Valid;

public interface PaymentService {

	ResponseEntity<?> createPayment(@Valid PaymentRequest request);

	ResponseEntity<?> getAllPayments();


	ResponseEntity<?> getByBookingId(Long bookingId);

}
