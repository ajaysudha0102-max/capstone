package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.BookingRequest;
import com.ey.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/book")
	public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest request){
		return bookingService.createBooking(request);
		
	}
	@GetMapping
	public ResponseEntity<?> getAllBookings(){
		return bookingService.getAllBookings();
	}
	@GetMapping("/get/{id}")
	 public ResponseEntity<?> getBookingById(@PathVariable Long id){
		return bookingService.getBookingById(id);
		
	}
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<?> getBookingByCustomer(@PathVariable Long customerId){
		return bookingService.getBookingByCustomer(customerId);
		
	}
	@GetMapping("/cleaner/{cleanerId}")
	public ResponseEntity<?> getBookingByCleaner(@PathVariable Long cleanerId){
		return bookingService.getBookingByCleaner(cleanerId);
		
	}
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long id){
		return bookingService.cancelBooking(id);
	}
	
	

}
