package com.example.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookingservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> { }
