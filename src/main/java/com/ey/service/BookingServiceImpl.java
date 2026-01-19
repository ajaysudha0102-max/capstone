package com.ey.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.ey.mapper.BookingMapper;
import com.ey.repository.AddressRepository;
import com.ey.repository.BookingRepository;
import com.ey.repository.CleaningServiceRepository;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class BookingServiceImpl implements BookingService {
	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

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

	    Optional<User> customer = userRepo.findById(request.getCustomerId());
	    if (customer.isEmpty())
	        return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);

	    Optional<CleaningService> service =
	            serviceRepo.findById(request.getServiceId());
	    if (service.isEmpty())
	        return new ResponseEntity<>("Service not found", HttpStatus.NOT_FOUND);

	    Optional<Address> address =
	            addressRepo.findById(request.getAddressId());
	    if (address.isEmpty())
	        return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);

	    Booking booking = BookingMapper.requestToEntity(request);

	    booking.setCustomer(customer.get());
	    booking.setService(service.get());
	    booking.setServiceAddress(address.get()); // or setAddress(...)
	    booking.setBookingStatus(BookingStatus.CONFIRMED);
	    booking.setTotalPrice(service.get().getPrice());
	    
	    //payment status
	    booking.setCreatedAt(LocalDateTime.now());

	    Booking saved = bookingRepo.save(booking);

	    BookingResponse response = BookingMapper.entityToResponse(saved);

	    logger.info("Booking created with id: " + saved.getBookingId());

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllBookings() {
		List<Booking> list = bookingRepo.findAll();

        if (list.isEmpty())
            return ResponseEntity.ok("No bookings found");

        List<BookingResponse> responses = new ArrayList<>();
        for (Booking b : list)
            responses.add(BookingMapper.entityToResponse(b));

        return ResponseEntity.ok(responses);
	}

	@Override
	public ResponseEntity<?> getBookingById(Long id) {
		 Optional<Booking> booking = bookingRepo.findById(id);

	        if (booking.isEmpty())
	            return new ResponseEntity<>("Booking not found with id: " + id, HttpStatus.NOT_FOUND);

	        return new ResponseEntity<>(
	                BookingMapper.entityToResponse(booking.get()),
	                HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getBookingByCustomer(Long customerId) {
		List<Booking> list = bookingRepo.findByCustomerId(customerId);

        if (list.isEmpty())
            return ResponseEntity.ok("No bookings for this customer");

        List<BookingResponse> responses = new ArrayList<>();
        for (Booking b : list)
            responses.add(BookingMapper.entityToResponse(b));

        return ResponseEntity.ok(responses);
	}

	@Override
	public ResponseEntity<?> getBookingByCleaner(Long cleanerId) {
		  List<Booking> list = bookingRepo.findByCleanerId(cleanerId);

	        if (list.isEmpty())
	            return ResponseEntity.ok("No bookings for this cleaner");

	        List<BookingResponse> responses = new ArrayList<>();
	        for (Booking b : list)
	            responses.add(BookingMapper.entityToResponse(b));

	        return ResponseEntity.ok(responses);
	}

	@Override
	public ResponseEntity<?> cancelBooking(Long id) {
		  Optional<Booking> opt = bookingRepo.findById(id);

	        if (opt.isEmpty())
	            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);

	        Booking booking = opt.get();

	        if (booking.getBookingStatus() != BookingStatus.CONFIRMED)
	            return new ResponseEntity<>("Booking cannot be cancelled now", HttpStatus.BAD_REQUEST);

	        booking.setBookingStatus(BookingStatus.CANCELLED);
	        bookingRepo.save(booking);

	        logger.info("Booking cancelled with id: " + id);

	        return new ResponseEntity<>("Booking cancelled successfully", HttpStatus.OK);
	}

	
		@Override
		public ResponseEntity<?> assignCleaner(AssignCleanerRequest request) {

		    Optional<Booking> bookingOpt = bookingRepo.findById(request.getBookingId());
		    if (bookingOpt.isEmpty())
		        return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);

		    Optional<User> cleanerOpt = userRepo.findById(request.getCleanerId());
		    if (cleanerOpt.isEmpty())
		        return new ResponseEntity<>("Cleaner not found", HttpStatus.NOT_FOUND);

		    User cleaner = cleanerOpt.get();

		    if (cleaner.getRole() != UserRole.CLEANER)
		        return new ResponseEntity<>("User is not a cleaner", HttpStatus.BAD_REQUEST);

		    Booking booking = bookingOpt.get();

		    booking.setCleaner(cleaner);
		    bookingRepo.save(booking);

		    logger.info("Cleaner {} assigned to booking {}", cleaner.getId(), booking.getBookingId());

		    return new ResponseEntity<>("Cleaner assigned successfully", HttpStatus.OK);
		}

		
	

}
