package com.ey.service;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ey.dto.request.UserRegisterRequest;
class UserServiceSimpleTest {
   @Test
   void testUserRegisterRequestCreation() {
       UserRegisterRequest req = new UserRegisterRequest();
       req.setEmail("test@gmail.com");
       req.setPhoneNumber("9999999999");
       req.setPassword("1234");
       assertEquals("test@gmail.com", req.getEmail());
       assertEquals("9999999999", req.getPhoneNumber());
       assertEquals("1234", req.getPassword());
   }
   @Test
   void testEmailNotNull() {
       UserRegisterRequest req = new UserRegisterRequest();
       req.setEmail(null);
       assertNull(req.getEmail());
   }
}