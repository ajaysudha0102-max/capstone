package com.ey.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ey.entity.User;
import com.ey.enums.UserRole;

@SpringBootTest

class UserEntityTest {

	@Test

	void testSetAndGetEmail() {

		User user = new User();

		user.setEmail("ajay@gmail.com");

		assertEquals("ajay@gmail.com", user.getEmail());

	}

	@Test

	void testSetAndGetPhone() {

		User user = new User();

		user.setPhoneNumber("9999999999");

		assertEquals("9999999999", user.getPhoneNumber());

	}

	@Test

	void testSetAndGetRole() {

		User user = new User();

		user.setRole(UserRole.CUSTOMER);

		assertEquals(UserRole.CUSTOMER, user.getRole());

	}

	@Test

	void testPasswordNotNull() {

		User user = new User();

		user.setPassword("abc123");

		assertNotNull(user.getPassword());

	}

	@Test

	void testUserObjectCreation() {

		User user = new User();

		assertNotNull(user);

	}

}
