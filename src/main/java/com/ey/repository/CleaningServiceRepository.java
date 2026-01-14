package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.CleaningService;

@Repository
public interface CleaningServiceRepository extends JpaRepository<CleaningService , Long> {

}
