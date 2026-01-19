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

import com.ey.dto.request.BookingStatusUpdateRequest;
import com.ey.dto.response.CleanerBookingDetailsResponse;
import com.ey.entity.Booking;
import com.ey.entity.User;
import com.ey.enums.BookingStatus;
import com.ey.mapper.BookingMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class CleanerServiceImpl implements CleanerService{
	
    Logger logger = LoggerFactory.getLogger(CleanerServiceImpl.class);

	@Autowired
	private BookingRepository bookingRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public ResponseEntity<?> getCleanerBookings(Long cleanerId) {
		Optional<User> cleaner =userRepo.findById(cleanerId);
		
		 if (cleaner.isEmpty()) {
	            logger.warn("Cleaner not found: " + cleanerId);
	            return new ResponseEntity<>("Cleaner not found", HttpStatus.NOT_FOUND);
	        }

	        List<Booking> bookings = bookingRepo.findByCleanerId(cleanerId);

	        if (bookings.isEmpty()) {
	            return ResponseEntity.ok("No bookings assigned to this cleaner");
	        }

	        List<CleanerBookingDetailsResponse> responses = new ArrayList<>();

	        for (Booking booking : bookings) {
	            responses.add(BookingMapper.toCleanerBookingResponse(booking));
	        }

	        logger.info("Fetched bookings for cleaner: " + cleanerId);

	        return ResponseEntity.ok(responses);
	}

	@Override
	public ResponseEntity<?> updateJobStatus(Long bookingId, @Valid BookingStatusUpdateRequest request) {

        Optional<Booking> optBooking = bookingRepo.findById(bookingId);

        if (optBooking.isEmpty()) {
            logger.warn("Booking not found: " + bookingId);
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }

        Booking booking = optBooking.get();

        BookingStatus newStatus = request.getStatus();

        

        booking.setBookingStatus(newStatus);
        bookingRepo.save(booking);

        logger.info("Booking status updated for bookingId=" + bookingId + " to " + newStatus);

        return ResponseEntity.ok("Job status updated successfully");
    }
	}
	


