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

import com.ey.dto.request.AssignCleanerRequest;
import com.ey.dto.request.BookingRequest;
import com.ey.dto.response.BookingResponse;
import com.ey.entity.Address;
import com.ey.entity.Booking;
import com.ey.entity.CleaningService;
import com.ey.entity.User;
import com.ey.enums.BookingStatus;
import com.ey.enums.UserRole;
import com.ey.exception.ApiException;
import com.ey.mapper.BookingMapper;
import com.ey.repository.AddressRepository;
import com.ey.repository.BookingRepository;
import com.ey.repository.CleaningServiceRepository;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service

public class BookingServiceImpl implements BookingService {

	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired

	private BookingRepository bookingRepo;

	@Autowired

	private UserRepository userRepo;

	@Autowired

	private CleaningServiceRepository serviceRepo;

	@Autowired

	private AddressRepository addressRepo;

	@Override

	public ResponseEntity<?> createBooking(@Valid BookingRequest request) {

		User customer = userRepo.findById(request.getCustomerId())

				.orElseThrow(() -> new ApiException("Customer not found with id: " + request.getCustomerId()));

		CleaningService service = serviceRepo.findById(request.getServiceId())

				.orElseThrow(() -> new ApiException("Service not found with id: " + request.getServiceId()));

		Address address = addressRepo.findById(request.getAddressId())

				.orElseThrow(() -> new ApiException("Address not found with id: " + request.getAddressId()));

		Booking booking = BookingMapper.requestToEntity(request);

		booking.setCustomer(customer);

		booking.setService(service);

		booking.setServiceAddress(address);

		booking.setBookingStatus(BookingStatus.CONFIRMED);

		booking.setTotalPrice(service.getPrice());

		booking.setCreatedAt(LocalDateTime.now());

		Booking saved = bookingRepo.save(booking);

		BookingResponse response = BookingMapper.entityToResponse(saved);

		logger.info("Booking created with id: {}", saved.getBookingId());

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@Override

	public ResponseEntity<?> getAllBookings() {

		List<Booking> list = bookingRepo.findAll();

		if (list.isEmpty()) {

			throw new ApiException("No bookings found");

		}

		List<BookingResponse> responses = new ArrayList<>();

		for (Booking b : list) {

			responses.add(BookingMapper.entityToResponse(b));

		}

		return ResponseEntity.ok(responses);

	}

	@Override

	public ResponseEntity<?> getBookingById(Long id) {

		Booking booking = bookingRepo.findById(id)

				.orElseThrow(() -> new ApiException("Booking not found with id: " + id));

		return ResponseEntity.ok(BookingMapper.entityToResponse(booking));

	}

	@Override

	public ResponseEntity<?> getBookingByCustomer(Long customerId) {

		List<Booking> list = bookingRepo.findByCustomerId(customerId);

		if (list.isEmpty()) {

			throw new ApiException("No bookings found for customer id: " + customerId);

		}

		List<BookingResponse> responses = new ArrayList<>();

		for (Booking b : list) {

			responses.add(BookingMapper.entityToResponse(b));

		}

		return ResponseEntity.ok(responses);

	}

	@Override

	public ResponseEntity<?> getBookingByCleaner(Long cleanerId) {

		List<Booking> list = bookingRepo.findByCleanerId(cleanerId);

		if (list.isEmpty()) {

			throw new ApiException("No bookings found for cleaner id: " + cleanerId);

		}

		List<BookingResponse> responses = new ArrayList<>();

		for (Booking b : list) {

			responses.add(BookingMapper.entityToResponse(b));

		}

		return ResponseEntity.ok(responses);

	}

	@Override

	public ResponseEntity<?> cancelBooking(Long id) {

		Booking booking = bookingRepo.findById(id)

				.orElseThrow(() -> new ApiException("Booking not found with id: " + id));

		if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {

			throw new ApiException("Booking cannot be cancelled now");

		}

		booking.setBookingStatus(BookingStatus.CANCELLED);

		bookingRepo.save(booking);

		logger.info("Booking cancelled with id: {}", id);

		return ResponseEntity.ok("Booking cancelled successfully");

	}

	@Override

	public ResponseEntity<?> assignCleaner(AssignCleanerRequest request) {

		Booking booking = bookingRepo.findById(request.getBookingId())

				.orElseThrow(() -> new ApiException("Booking not found with id: " + request.getBookingId()));

		User cleaner = userRepo.findById(request.getCleanerId())

				.orElseThrow(() -> new ApiException("Cleaner not found with id: " + request.getCleanerId()));

		if (cleaner.getRole() != UserRole.CLEANER) {

			throw new ApiException("User is not a cleaner");

		}

		booking.setCleaner(cleaner);

		bookingRepo.save(booking);

		logger.info("Cleaner {} assigned to booking {}", cleaner.getId(), booking.getBookingId());

		return ResponseEntity.ok("Cleaner assigned successfully");

	}

}
