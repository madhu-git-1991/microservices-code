package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.BusInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusInventoryRepository extends JpaRepository<BusInventory, String> {

    @Modifying
    @Query(value = "update bus_inventory set available_seats = :seats where bus_number=:busNo", nativeQuery = true)
    int updateSeats(@Param("seats") int seats, String busNo);
}
