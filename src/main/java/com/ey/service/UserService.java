package com.ey.service;

import org.springframework.http.ResponseEntity;

import com.ey.dto.request.ChangePasswordRequest;
import com.ey.dto.request.UserRegisterRequest;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<?> registerUser(@Valid UserRegisterRequest request);

	ResponseEntity<?> getAllUsers();

	ResponseEntity<?> getUserById(Long id);

	ResponseEntity<?> getUserByEmail(String email);

	ResponseEntity<?> deleteUser(Long id);

	ResponseEntity<?> changePassword(@Valid ChangePasswordRequest request);

}
