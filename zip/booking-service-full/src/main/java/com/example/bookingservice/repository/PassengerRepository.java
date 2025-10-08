package com.example.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookingservice.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> { }
