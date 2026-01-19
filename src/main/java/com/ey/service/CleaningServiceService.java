package com.ey.service;

import org.springframework.http.ResponseEntity;

import com.ey.dto.request.CleaningServiceRequest;

import jakarta.validation.Valid;

public interface CleaningServiceService {

	ResponseEntity<?> addService(@Valid CleaningServiceRequest request);

	ResponseEntity<?> getAllServices();

	ResponseEntity<?> getById(Long id);

	ResponseEntity<?> deleteService(Long id);


}
