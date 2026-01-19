package com.ey.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ey.dto.request.UserRegisterRequest;
import com.ey.dto.response.UserResponse;
import com.ey.entity.User;

@Component
public class UserMapper {

    // Convert UserRegisterRequest → User Entity
    public static User registerRequestToUser(UserRegisterRequest request) {

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        user.setCreatedAt(LocalDateTime.now());

        return user;
    }

    // Convert User Entity → UserResponse DTO
    public static UserResponse userToResponse(User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }
}
