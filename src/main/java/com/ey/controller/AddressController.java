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

import com.ey.dto.request.AddressRequest;
import com.ey.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {
	
	 @Autowired
	    private AddressService addressService;

	    @PostMapping
	    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressRequest request) {
	        return addressService.addAddress(request);
	    }

	    @GetMapping
	    public ResponseEntity<?> getAllAddresses() {
	        return addressService.getAllAddresses();
	    }

	    @GetMapping("/get/{id}")
	    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
	        return addressService.getAddressById(id);
	    }

	    @GetMapping("/user/{userId}")
	    public ResponseEntity<?> getByUser(@PathVariable Long userId) {
	        return addressService.getAddressesByUser(userId);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
	        return addressService.deleteAddress(id);
	    }

	    @PutMapping
	    public ResponseEntity<?> updateAddress(@Valid @RequestBody AddressRequest request) {
	        return addressService.updateAddress(request);
	    }

}
