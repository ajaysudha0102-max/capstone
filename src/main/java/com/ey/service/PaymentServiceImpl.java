package com.ey.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.ey.exception.ApiException;
import com.ey.mapper.PaymentMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.PaymentRepository;

@Service

public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired

	private PaymentRepository paymentRepo;

	@Autowired

	private BookingRepository bookingRepo;

	@Override

	public ResponseEntity<?> createPayment(PaymentRequest request) {

		Booking booking = bookingRepo.findById(request.getBookingId())

				.orElseThrow(() -> new ApiException("Booking not found with id: " + request.getBookingId()));

		Payment payment = new Payment();

		payment.setBooking(booking);

		payment.setPaymentMethod(request.getPaymentMethod());

		payment.setAmount(booking.getTotalPrice());

		payment.setPaymentStatus(PaymentStatus.PENDING);

		payment.setPaymentDate(LocalDateTime.now());

		Payment saved = paymentRepo.save(payment);

		PaymentResponse response = PaymentMapper.entityToResponse(saved);

		logger.info("Payment successful for booking id: {}", request.getBookingId());

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@Override

	public ResponseEntity<?> getAllPayments() {

		List<Payment> list = paymentRepo.findAll();

		if (list.isEmpty()) {

			throw new ApiException("No payments found");

		}

		List<PaymentResponse> response = new ArrayList<>();

		for (Payment p : list) {

			response.add(PaymentMapper.entityToResponse(p));

		}

		return ResponseEntity.ok(response);

	}

	@Override

	public ResponseEntity<?> getByBookingId(Long bookingId) {

		Payment payment = paymentRepo.findByBooking_BookingId(bookingId)

				.orElseThrow(() -> new ApiException("Payment not found for booking id: " + bookingId));

		return ResponseEntity.ok(PaymentMapper.entityToResponse(payment));

	}

}
