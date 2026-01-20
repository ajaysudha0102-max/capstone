package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ey.entity.User;
import com.ey.enums.UserRole;
import com.ey.exception.ApiException;
import com.ey.repository.UserRepository;

@SpringBootTest

@Transactional

class AuthServiceTest {

    @Autowired

    private AuthService authService;
    
    @Autowired
    private UserRepository userRepo;


    @Test

    void registerUser_success() {

        User user = new User();

        user.setFirstName("Ajay");

        user.setLastName("Reddy");

        user.setEmail("ajay@gmail.com");

        user.setPassword("pass12345");

        user.setPhoneNumber("9999999999");

        user.setRole(UserRole.CUSTOMER);

        User savedUser = userRepo.save(user);

        //assertNotNull(savedUser.getId());

        assertEquals("ajay@gmail.com", savedUser.getEmail());

        assertEquals(UserRole.CUSTOMER, savedUser.getRole());

    }


    @Test

    void registerUser_duplicateEmail_shouldThrowException() {

        User user1 = new User();

        user1.setFirstName("User1");

        user1.setEmail("duplicate@gmail.com");

        user1.setPassword("pass12345");

        user1.setPhoneNumber("8888888888");

        user1.setRole(UserRole.CUSTOMER);

        userRepo.save(user1);

        User user2 = new User();

        user2.setFirstName("User2");

        user2.setEmail("duplicate@gmail.com"); // same email

        user2.setPassword("pass12345");

        user2.setPhoneNumber("7777777777");

        user2.setRole(UserRole.CUSTOMER);

        assertThrows(ApiException.class, () -> {

        	userRepo.save(user2);

        });

    }

}
 