package com.ey.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.BookingStatusUpdateRequest;
import com.ey.service.CleanerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cleaner")
public class CleanerController {
	
	@Autowired
	private CleanerService cleanerService;
	
	@GetMapping("/{cleanerId}")
	public ResponseEntity<?> getCleanerBookings(@PathVariable Long cleanerId){
		return cleanerService.getCleanerBookings(cleanerId);
	}
	
	
	 @PutMapping("/bookings/{bookingId}/status")
	    public ResponseEntity<?> updateJobStatus(
	            @PathVariable Long bookingId,
	            @Valid @RequestBody BookingStatusUpdateRequest request) {

	        return cleanerService.updateJobStatus(bookingId, request);
	    }
	 @GetMapping("/{cleanerId}/bookings/date/{date}")
	 public ResponseEntity<?> getCleanerBookingsByDate(
	        @PathVariable Long cleanerId,
	        @PathVariable String date) {
	    LocalDate serviceDate = LocalDate.parse(date);
	    return cleanerService.getCleanerBookingsByDate(cleanerId, serviceDate);
	 }
	 

}
