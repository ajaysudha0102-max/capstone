package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.PaymentRequest;
import com.ey.dto.response.PaymentResponse;
import com.ey.entity.Booking;
import com.ey.entity.Payment;
import com.ey.enums.PaymentStatus;
import com.ey.mapper.PaymentMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.PaymentRepository;

import jakarta.validation.Valid;

@Service
public class PaymentServiceImpl implements PaymentService{
	
    Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private BookingRepository bookingRepo;
	

	@Override
	public ResponseEntity<?> createPayment( PaymentRequest request) {
		
		  Optional<Booking> bookingOpt = bookingRepo.findById(request.getBookingId());
	        if (bookingOpt.isEmpty())
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
		
		Booking booking =bookingOpt.get();
		
		 Payment payment = new Payment();
		 
	        payment.setBooking(booking);
	        payment.setPaymentMethod(request.getPaymentMethod());
	        payment.setAmount(booking.getTotalPrice());
	        payment.setPaymentStatus(PaymentStatus.PENDING);

	        Payment saved = paymentRepo.save(payment);

	        PaymentResponse response = PaymentMapper.entityToResponse(saved);

	        logger.info("Payment successful for booking id: " + request.getBookingId());

	        return new ResponseEntity<>(response, HttpStatus.CREATED);
		
		
	}

	@Override
	public ResponseEntity<?> getAllPayments() {
		List<Payment> list= paymentRepo.findAll();
		
//		if(list.isEmpty())
//			return ResponseEntity.ok("no paymnets found");
		
		List<PaymentResponse> response = new ArrayList<>();
		for(Payment p:list)
			response.add(PaymentMapper.entityToResponse(p));
		return ResponseEntity.ok(response);
		
	}



	@Override
	public ResponseEntity<?> getByBookingId(Long bookingId) {
		Optional<Payment> paymnetOpt= paymentRepo.findByBooking_BookingId(bookingId);
		if(paymnetOpt.isEmpty())
			return new ResponseEntity<>("paymnet not found for this booking ",HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(PaymentMapper.entityToResponse(paymnetOpt.get()),HttpStatus.OK);
	}

}
