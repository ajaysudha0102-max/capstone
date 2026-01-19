package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.RatingRequest;
import com.ey.service.RatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<?> addRating(@Valid @RequestBody RatingRequest request){
		return ratingService.addRating(request);
	}
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getByCustomerId(@PathVariable Long id){
		return ratingService.getByCustomerId(id);
	}
	@GetMapping("/cleaner/{id}")
	public ResponseEntity<?> getByCleanerId(@PathVariable Long id){
		return ratingService.getByCleanerId(id);
	}

}
