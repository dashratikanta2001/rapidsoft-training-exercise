package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Booking;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BookingDao extends JpaRepository<Booking, Long> {

}
