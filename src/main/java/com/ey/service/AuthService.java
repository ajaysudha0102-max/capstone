package com.ey.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.dto.request.ForgotPasswordRequest;
import com.ey.dto.request.ResetPasswordRequest;
import com.ey.dto.request.UserRegisterRequest;
import com.ey.entity.User;
import com.ey.exception.ApiException;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@Service

public class AuthService {
	   private final Logger logger = LoggerFactory.getLogger(AuthService.class);


    @Autowired

    private UserRepository userRepo;

    @Autowired

    private PasswordEncoder encoder;

    @Autowired

    private JwtUtil jwtUtil;

    public String login(String email, String password) {

        User user = userRepo.findByEmail(email)

                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!encoder.matches(password, user.getPassword())) {

            throw new RuntimeException("Invalid password");

        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());

    }

    public void register(UserRegisterRequest request) {

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {

            throw new RuntimeException("Email already exists");

        }

        User user = new User();
		user.setCreatedAt(LocalDateTime.now());


        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());

        user.setPhoneNumber(request.getPhoneNumber());

        user.setPassword(encoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        userRepo.save(user);

    }

   
}
 