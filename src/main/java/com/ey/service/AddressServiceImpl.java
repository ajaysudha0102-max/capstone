package com.ey.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ey.dto.request.AddressRequest;
import com.ey.dto.response.AddressResponse;
import com.ey.entity.Address;
import com.ey.entity.User;
import com.ey.exception.ApiException;
import com.ey.mapper.AddressMapper;
import com.ey.repository.AddressRepository;
import com.ey.repository.UserRepository;

@Service

public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired

    private AddressRepository addressRepo;

    @Autowired

    private UserRepository userRepo;

    @Override

    public ResponseEntity<?> addAddress(AddressRequest request) {

        User user = userRepo.findById(request.getUserId())

                .orElseThrow(() -> new ApiException("User not found with id: " + request.getUserId()));

        Address address = AddressMapper.requestToEntity(request);

        address.setUser(user);

        address.setCreatedAt(LocalDateTime.now());

        Address saved = addressRepo.save(address);

        AddressResponse response = AddressMapper.entityToResponse(saved);

        logger.info("Address added for user id: {}", request.getUserId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @Override

    public ResponseEntity<?> getAllAddresses() {

        List<Address> list = addressRepo.findAll();

        if (list.isEmpty()) {

            throw new ApiException("No addresses found");

        }

        List<AddressResponse> responses = new ArrayList<>();

        for (Address address : list) {

            responses.add(AddressMapper.entityToResponse(address));

        }

        return ResponseEntity.ok(responses);

    }

    @Override

    public ResponseEntity<?> getAddressById(Long id) {

        Address address = addressRepo.findById(id)

                .orElseThrow(() -> new ApiException("Address not found with id: " + id));

        return ResponseEntity.ok(AddressMapper.entityToResponse(address));

    }

    @Override

    public ResponseEntity<?> getAddressesByUser(Long userId) {

        List<Address> list = addressRepo.findByUserId(userId);

        if (list.isEmpty()) {

            throw new ApiException("No addresses found for user id: " + userId);

        }

        List<AddressResponse> responses = new ArrayList<>();

        for (Address address : list) {

            responses.add(AddressMapper.entityToResponse(address));

        }

        return ResponseEntity.ok(responses);

    }

    @Override

    public ResponseEntity<?> updateAddress(AddressRequest request) {

        if (request.getId() == null) {

            throw new ApiException("Address id is required for update");

        }

        Address address = addressRepo.findById(request.getId())

                .orElseThrow(() -> new ApiException("Address not found with id: " + request.getId()));

        address.setStreet(request.getStreet());

        address.setCity(request.getCity());

        address.setState(request.getState());

        address.setPincode(request.getPincode());

        address.setCreatedAt(LocalDateTime.now());

        Address saved = addressRepo.save(address);

        AddressResponse response = AddressMapper.entityToResponse(saved);

        logger.info("Address updated id: {}", request.getId());

        return ResponseEntity.ok(response);

    }

    @Override

    public ResponseEntity<?> deleteAddress(Long id) {

        Address address = addressRepo.findById(id)

                .orElseThrow(() -> new ApiException("Address not found with id: " + id));

        addressRepo.delete(address);

        logger.info("Address deleted id: {}", id);

        return ResponseEntity.ok("Address deleted successfully");

    }

}
