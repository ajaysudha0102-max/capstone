package com.ey.service;

import org.springframework.http.ResponseEntity;

import com.ey.dto.request.AddressRequest;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<?> addAddress(@Valid AddressRequest request);

	ResponseEntity<?> getAllAddresses();

	ResponseEntity<?> getAddressById(Long id);

	ResponseEntity<?> getAddressesByUser(Long userId);

	ResponseEntity<?> deleteAddress(Long id);

	ResponseEntity<?> updateAddress(@Valid AddressRequest request);

}
