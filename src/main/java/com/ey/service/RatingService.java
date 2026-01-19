package com.ey.service;

import org.springframework.http.ResponseEntity;

import com.ey.dto.request.RatingRequest;

import jakarta.validation.Valid;

public interface RatingService {

	ResponseEntity<?> addRating(@Valid RatingRequest request);

	ResponseEntity<?> getByCustomerId(Long id);

	ResponseEntity<?> getByCleanerId(Long id);

}
