package com.ey.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating , Long> {

	List<Rating> findByCleanerId(Long id);

	List<Rating> findByCustomerId(Long id);

}
