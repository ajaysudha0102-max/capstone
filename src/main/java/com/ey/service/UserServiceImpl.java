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

import com.ey.dto.request.ChangePasswordRequest;
import com.ey.dto.request.UserRegisterRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;
import com.ey.exception.ApiException;
import com.ey.mapper.UserMapper;
import com.ey.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public ResponseEntity<?> registerUser(@Valid UserRegisterRequest request) {
		if (userRepo.findByEmail(request.getEmail()).isPresent()) {
			return new ResponseEntity<>("this email is already registered", HttpStatus.CONFLICT);
		}

		User user = UserMapper.registerRequestToUser(request);
		user.setCreatedAt(LocalDateTime.now());
		User savedUser = userRepo.save(user);

		UserResponse response = UserMapper.userToResponse(savedUser);
		logger.info("user registered succesfully ");
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<?> getAllUsers() {
		List<User> users = userRepo.findAll();

		if (users.isEmpty()) {
			return ResponseEntity.ok("No users registered");
		}

		List<UserResponse> responseList = new ArrayList<>();

		for (User user : users) {
			responseList.add(UserMapper.userToResponse(user));
		}

		return ResponseEntity.ok(responseList);
	}

	@Override
	public ResponseEntity<?> getUserById(Long id) {
		Optional<User> user = userRepo.findById(id);

		if (user.isEmpty()) {
			logger.warn("User not found with id: " + id);
			return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
		}

		UserResponse response = UserMapper.userToResponse(user.get());

		logger.info("User found with id: " + id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserByEmail(String email) {
		Optional<User> user = userRepo.findByEmail(email);

		if (user.isEmpty()) {
			logger.warn("User not found with email: " + email);
			return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
		}

		UserResponse response = UserMapper.userToResponse(user.get());
		logger.info("User found with email: " + email);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteUser(Long id) {
		Optional<User> user = userRepo.findById(id);

		if (user.isEmpty()) {
			logger.warn("User not found with id: " + id);
			return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
		}

		userRepo.deleteById(id);

		logger.info("User deleted with id: " + id);

		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> changePassword(@Valid ChangePasswordRequest request) {
		Optional<User> optUser = userRepo.findByEmailAndPassword(
                request.getEmail(),
                request.getOldPassword()
        );

        if (optUser.isEmpty()) {
            logger.warn("Invalid email or password");
            return new ResponseEntity<>("Invalid email or password", HttpStatus.NOT_FOUND);
        }

        User user = optUser.get();
        user.setPassword(request.getNewPassword());

        userRepo.save(user);

        logger.info("Password reset successful for email: " + request.getEmail());

        return new ResponseEntity<>("Password reset successful", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getUserByRole(String role) {
		   List<User> users = userRepo.findByRole(role);
		   if (users.isEmpty()) {
		       logger.warn("No users found with role: " + role);
		       throw new ApiException("No users found with role: " + role);
		   }
		   List<UserResponse> responses = new ArrayList<>();
		   for (User user : users) {
		       responses.add(UserMapper.userToResponse(user));
		   }
		   logger.info("Fetched {} users with role {}", users.size(), role);
		   return ResponseEntity.ok(responses);
	}

}
