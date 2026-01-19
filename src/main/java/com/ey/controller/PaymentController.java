package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.PaymentRequest;
import com.ey.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest request){
		return paymentService.createPayment(request);
	}
	  @GetMapping
	  public ResponseEntity<?> getAllPayments(){
		  return  paymentService.getAllPayments();
	  }
	  
	  @GetMapping("/booking/{bookingId}")
	  public ResponseEntity<?> getByBookingId(@PathVariable Long bookingId){
		  return paymentService.getByBookingId(bookingId);
	  }
}
