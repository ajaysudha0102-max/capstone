package com.ey.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.ey.dto.request.BookingStatusUpdateRequest;

import jakarta.validation.Valid;

public interface CleanerService {

	ResponseEntity<?> getCleanerBookings(Long cleanerId);

	ResponseEntity<?> updateJobStatus(Long bookingId, @Valid BookingStatusUpdateRequest request);

	ResponseEntity<?> getCleanerBookingsByDate(Long cleanerId, LocalDate serviceDate);

}
