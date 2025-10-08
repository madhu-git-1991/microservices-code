package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.BusInventory;
import com.example.inventoryservice.repository.BusInventoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final BusInventoryRepository repository;

    public InventoryController(BusInventoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/check/{busNumber}")
    public ResponseEntity<Map<String,Object>> check(@PathVariable String busNumber) {
        return repository.findById(busNumber)
                .map(inv -> {
                    Map<String,Object> resp = new HashMap<>();
                    resp.put("busNumber", inv.getBusNumber());
                    resp.put("availableSeats", inv.getAvailableSeats());
                    resp.put("lastUpdated", inv.getLastUpdated());
                    return ResponseEntity.ok(resp);
                })
                .orElseGet(() -> {
                    // If no inventory exists, return 404 with availableSeats=0
                    Map<String,Object> resp = new HashMap<>();
                    resp.put("busNumber", busNumber);
                    resp.put("availableSeats", 0);
                    resp.put("lastUpdated", Instant.now());
                    return ResponseEntity.ok(resp);
                });
    }

    @PostMapping
    public ResponseEntity<BusInventory> createOrUpdate(@RequestBody BusInventory inv) {
        inv.setLastUpdated(Instant.now());
        BusInventory saved = repository.save(inv);
        return ResponseEntity.ok(saved);
    }
}
