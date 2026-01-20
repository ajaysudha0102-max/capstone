package com.ey.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.dto.request.ForgotPasswordRequest;
import com.ey.dto.request.LoginRequest;
import com.ey.dto.request.ResetPasswordRequest;
import com.ey.dto.request.UserRegisterRequest;
import com.ey.service.AuthService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/auth")

public class AuthController {

    @Autowired

    private AuthService authService;

    @PostMapping("/register")

    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));

    }

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String token = authService.login(request.getEmail(), request.getPassword());

        return ResponseEntity.ok(Map.of("token", token));

    }

    @PostMapping("/logout")

    public ResponseEntity<?> logout() {


        return ResponseEntity.ok("loggedout seccesfully");

    }
//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
//        authService.forgotPassword(request);
//        return ResponseEntity.ok("OTP sent to your email");
//    }
//    @PostMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
//        authService.resetPassword(request);
//        return ResponseEntity.ok("Password reset successful");
//    }
    
    

}
 