package com.ey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking , Long> {

}
