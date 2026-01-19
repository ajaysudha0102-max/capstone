package com.ey.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ey.dto.request.CleaningServiceRequest;
import com.ey.dto.response.CleaningServiceResponse;
import com.ey.entity.CleaningService;

@Component
public class CleaningServiceMapper {
	public static CleaningService requestToEntity(CleaningServiceRequest request) {
		CleaningService service = new CleaningService();

		service.setName(request.getName());
		service.setDescription(request.getDescription());
		service.setPrice(request.getPrice());
		service.setCreatedAt(LocalDateTime.now());
		return service;

	}

	public static CleaningServiceResponse entityToResponse(CleaningService service) {
		CleaningServiceResponse response = new CleaningServiceResponse();
		 response.setId(service.getId());
	        response.setName(service.getName());
	        response.setDescription(service.getDescription());
	        response.setPrice(service.getPrice());
	        response.setCreatedAt(service.getCreatedAt());
	        return response;
		
	}

}
