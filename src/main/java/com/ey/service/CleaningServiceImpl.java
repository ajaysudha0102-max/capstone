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

import com.ey.dto.request.CleaningServiceRequest;
import com.ey.dto.response.CleaningServiceResponse;
import com.ey.entity.CleaningService;
import com.ey.mapper.CleaningServiceMapper;
import com.ey.repository.CleaningServiceRepository;

import jakarta.validation.Valid;

@Service
public class CleaningServiceImpl implements CleaningServiceService{
	
    Logger logger = LoggerFactory.getLogger(CleaningServiceImpl.class);

	@Autowired
	private CleaningServiceRepository serviceRepo;

	@Override
	public ResponseEntity<?> addService(@Valid CleaningServiceRequest request) {

        if (serviceRepo.findByName(request.getName()).isPresent()) {
            return new ResponseEntity<>("Service already exists", HttpStatus.CONFLICT);
        }

        CleaningService service = CleaningServiceMapper.requestToEntity(request);
        service.setCreatedAt(LocalDateTime.now());

        CleaningService saved = serviceRepo.save(service);

        CleaningServiceResponse response = CleaningServiceMapper.entityToResponse(saved);

        logger.info("Cleaning service added: " + request.getName());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllServices() {
		 List<CleaningService> services = serviceRepo.findAll();

	        if (services.isEmpty()) {
	            return ResponseEntity.ok("No services available");
	        }

	        List<CleaningServiceResponse> list = new ArrayList<>();

	        for (CleaningService s : services) {
	            list.add(CleaningServiceMapper.entityToResponse(s));
	        }

	        return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<?> getById(Long id) {
		  Optional<CleaningService> opt = serviceRepo.findById(id);

	        if (opt.isEmpty()) {
	            logger.warn("Service not found with id: " + id);
	            return new ResponseEntity<>("Service not found with id: " + id, HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(
	                CleaningServiceMapper.entityToResponse(opt.get()),
	                HttpStatus.OK
	        );
	}

	@Override
	public ResponseEntity<?> deleteService(Long id) {
		 Optional<CleaningService> opt = serviceRepo.findById(id);

	        if (opt.isEmpty()) {
	            return new ResponseEntity<>("Service not found with id: " + id, HttpStatus.NOT_FOUND);
	        }

	        serviceRepo.deleteById(id);

	        logger.info("Service deleted with id: " + id);

	        return new ResponseEntity<>("Service deleted successfully", HttpStatus.OK);
	}


}
