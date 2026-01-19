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

import com.ey.dto.request.ChangePasswordRequest;
import com.ey.dto.request.UserRegisterRequest;
import com.ey.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest request){
		return userService.registerUser(request);
		
	}
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		return userService.getUserById(id);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email){
		return userService.getUserByEmail(email);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		return userService.deleteUser(id);
	}
	
     @PutMapping("/reset")
 	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request){
    	 return userService.changePassword(request);
     }
}
