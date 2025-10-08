package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.BusInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusInventoryRepository extends JpaRepository<BusInventory, String> { }
