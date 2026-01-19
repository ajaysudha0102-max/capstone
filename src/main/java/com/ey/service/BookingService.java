package com.ey.service;

import org.springframework.http.ResponseEntity;

import com.ey.dto.request.AssignCleanerRequest;
import com.ey.dto.request.BookingRequest;

import jakarta.validation.Valid;

public interface BookingService {

	ResponseEntity<?> createBooking(@Valid BookingRequest request);

	ResponseEntity<?> getAllBookings();

	ResponseEntity<?> getBookingById(Long id);

	ResponseEntity<?> getBookingByCustomer(Long customerId);

	ResponseEntity<?> getBookingByCleaner(Long cleanerId);

	ResponseEntity<?> cancelBooking(Long id);

	ResponseEntity<?> assignCleaner(@Valid AssignCleanerRequest request);

}
