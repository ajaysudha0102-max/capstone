package com.ey.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.BookingStatusUpdateRequest;
import com.ey.dto.response.CleanerBookingDetailsResponse;
import com.ey.entity.Booking;
import com.ey.entity.User;
import com.ey.enums.BookingStatus;
import com.ey.exception.ApiException;
import com.ey.mapper.BookingMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service

public class CleanerServiceImpl implements CleanerService {

    private static final Logger logger = LoggerFactory.getLogger(CleanerServiceImpl.class);

    @Autowired

    private BookingRepository bookingRepo;

    @Autowired

    private UserRepository userRepo;

    @Override

    public ResponseEntity<?> getCleanerBookings(Long cleanerId) {

        User cleaner = userRepo.findById(cleanerId)

                .orElseThrow(() -> new ApiException("Cleaner not found with id: " + cleanerId));

        List<Booking> bookings = bookingRepo.findByCleanerId(cleanerId);

        if (bookings.isEmpty()) {

            throw new ApiException("No bookings assigned to this cleaner");

        }

        List<CleanerBookingDetailsResponse> responses = new ArrayList<>();

        for (Booking booking : bookings) {

            responses.add(BookingMapper.toCleanerBookingResponse(booking));

        }

        logger.info("Fetched bookings for cleaner: {}", cleanerId);

        return ResponseEntity.ok(responses);

    }

    @Override

    public ResponseEntity<?> updateJobStatus(Long bookingId, @Valid BookingStatusUpdateRequest request) {

        Booking booking = bookingRepo.findById(bookingId)

                .orElseThrow(() -> new ApiException("Booking not found with id: " + bookingId));

        BookingStatus newStatus = request.getStatus();

        booking.setBookingStatus(newStatus);

        bookingRepo.save(booking);

        logger.info("Booking status updated for bookingId={} to {}", bookingId, newStatus);

        return ResponseEntity.ok("Job status updated successfully");

    }

}
