package com.ey.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.RatingRequest;
import com.ey.dto.response.RatingResponse;
import com.ey.entity.Booking;
import com.ey.entity.Rating;
import com.ey.entity.User;
import com.ey.mapper.RatingMapper;
import com.ey.repository.BookingRepository;
import com.ey.repository.RatingRepository;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public ResponseEntity<?> addRating(@Valid RatingRequest request) {
		Optional<User> customer = userRepo.findById(request.getCustomerId());
		Optional<User> cleaner = userRepo.findById(request.getCleanerId());

		Optional<Booking> booking = bookingRepo.findById(request.getBookingId());
 
		
		Rating rating = RatingMapper.requestToEntity(request);
		rating.setCustomer(customer.get());
		rating.setCleaner(cleaner.get());
		rating.setBooking(booking.get());
		
		
		Rating saved = ratingRepo.save(rating);
		return new ResponseEntity<>(RatingMapper.entityToResponse(saved),HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<?> getByCustomerId(Long id) {
List<Rating> list = ratingRepo.findByCustomerId(id);
		
		if(list.isEmpty())
			return ResponseEntity.ok("no ratings found");
		
		List<RatingResponse> res = new ArrayList<>();
		for(Rating r: list)
			res.add(RatingMapper.entityToResponse(r));
		
		return ResponseEntity.ok(res);
		
	}

	@Override
	public ResponseEntity<?> getByCleanerId(Long id) {
		List<Rating> list = ratingRepo.findByCleanerId(id);
		
		if(list.isEmpty())
			return ResponseEntity.ok("no ratings found");
		
		List<RatingResponse> res = new ArrayList<>();
		for(Rating r: list)
			res.add(RatingMapper.entityToResponse(r));
		
		return ResponseEntity.ok(res);
		
	}

}
