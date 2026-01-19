package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.AssignCleanerRequest;
import com.ey.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/bookings")
public class AdminBookingController {
	@Autowired 
	BookingService bookingService;
	
	@PutMapping("/assignCleaner")
	public ResponseEntity<?> assignCleaner(@Valid @RequestBody AssignCleanerRequest request){
		return bookingService.assignCleaner(request);
	}
	

}
