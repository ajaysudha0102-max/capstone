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

import com.ey.dto.request.AddressRequest;
import com.ey.dto.response.AddressResponse;
import com.ey.entity.Address;
import com.ey.entity.User;
import com.ey.mapper.AddressMapper;
import com.ey.repository.AddressRepository;
import com.ey.repository.UserRepository;
import com.ey.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public ResponseEntity<?> addAddress(AddressRequest request) {

        Optional<User> userOpt = userRepo.findById(request.getUserId());

        if (userOpt.isEmpty()) {
            logger.warn("User not found with id: " + request.getUserId());
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        Address address = AddressMapper.requestToEntity(request);
        address.setUser(userOpt.get());
        
        address.setCreatedAt(LocalDateTime.now());

        Address saved = addressRepo.save(address);

        AddressResponse response = AddressMapper.entityToResponse(saved);

        logger.info("Address added for user id: " + request.getUserId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllAddresses() {

        List<Address> list = addressRepo.findAll();

        if (list.isEmpty()) {
            return ResponseEntity.ok("No addresses found");
        }

        List<AddressResponse> responses = new ArrayList<>();

        for (Address address : list) {
            responses.add(AddressMapper.entityToResponse(address));
        }

        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<?> getAddressById(Long id) {

        Optional<Address> opt = addressRepo.findById(id);

        if (opt.isEmpty()) {
            logger.warn("Address not found with id: " + id);
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                AddressMapper.entityToResponse(opt.get()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAddressesByUser(Long userId) {

        List<Address> list = addressRepo.findByUserId(userId);

        if (list.isEmpty()) {
            return ResponseEntity.ok("No addresses for this user");
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
            return new ResponseEntity<>("Address id is required for update", HttpStatus.BAD_REQUEST);
        }

        Optional<Address> opt = addressRepo.findById(request.getId());

        if (opt.isEmpty()) {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }

        Address address = opt.get();

        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setCreatedAt(LocalDateTime.now());

        Address saved = addressRepo.save(address);

        AddressResponse response = AddressMapper.entityToResponse(saved);

        logger.info("Address updated id: " + request.getId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {

        Optional<Address> opt = addressRepo.findById(id);

        if (opt.isEmpty()) {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }

        addressRepo.deleteById(id);

        logger.info("Address deleted id: " + id);

        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }
}
