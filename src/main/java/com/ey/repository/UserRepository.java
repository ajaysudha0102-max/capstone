package com.ey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.User;
import com.ey.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String oldPassword);

	List<User> findByRole(String role);	
	



}
