package com.example.adminservice.repository;

import com.example.adminservice.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRouteRepository extends JpaRepository<BusRoute, String> {
    Optional<BusRoute> findByBusNumber(String busNumber);
}
