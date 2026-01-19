package com.ey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.CleaningService;

@Repository
public interface CleaningServiceRepository extends JpaRepository<CleaningService , Long> {

	Optional<CleaningService> findByName(String name);


}
