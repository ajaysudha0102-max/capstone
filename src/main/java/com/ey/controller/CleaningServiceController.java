package com.ey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.CleaningServiceRequest;
import com.ey.service.CleaningServiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class CleaningServiceController {
	
	@Autowired
	private CleaningServiceService cleaningService;
	
	@PostMapping("/add")
   public ResponseEntity<?> addService(@Valid @RequestBody CleaningServiceRequest request){
		return cleaningService.addService(request);
	}
	
	@GetMapping
	 public ResponseEntity<?> getAllServices(){
		return cleaningService.getAllServices();
		
	}
	@GetMapping("/get/{id}")
	 public ResponseEntity<?> getById(@PathVariable Long id){
		return cleaningService.getById(id);
	}
	@DeleteMapping("/delete/{id}")
	 public ResponseEntity<?> deleteService(@PathVariable Long id){
		return cleaningService.deleteService(id);
	}
	
	

}
