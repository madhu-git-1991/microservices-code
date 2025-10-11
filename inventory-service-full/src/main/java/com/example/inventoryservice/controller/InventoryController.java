package com.example.inventoryservice.controller;

import com.example.inventoryservice.dtos.BusRouteDTO;
import com.example.inventoryservice.model.BusInventory;
import com.example.inventoryservice.model.BusInventoryPatchRequest;
import com.example.inventoryservice.repository.BusInventoryRepository;
import com.example.inventoryservice.service.AdminServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final BusInventoryRepository repository;
    private final AdminServiceClient adminServiceClient;

    public InventoryController(BusInventoryRepository repository, AdminServiceClient adminServiceClient) {
        this.repository = repository;
        this.adminServiceClient = adminServiceClient;
    }

    @GetMapping("/check/{busNumber}")
    public ResponseEntity<Map<String,Object>> check(@PathVariable String busNumber) {

        //check if first booking
        var busInventoryOptional  = repository.findById(busNumber);
        System.out.println("busInventoryOptional "+busInventoryOptional);
        if (busInventoryOptional.isEmpty()){
            BusRouteDTO busRouteDetails = adminServiceClient.fetchRouteDetails(busNumber);
            System.out.println("busRouteDetails "+busRouteDetails);
            if (Objects.isNull(busRouteDetails) || busRouteDetails.getTotalSeats() == 0){
                Map<String, Object> resp = buildInvalidBusNoResponse();
                return ResponseEntity.ok(resp);
            } else {
                BusInventory inventory = new BusInventory();
                inventory.setAvailableSeats(busRouteDetails.getTotalSeats());
                inventory.setLastUpdated(Instant.now());
                inventory.setBusNumber(busRouteDetails.getBusNumber());
                repository.save(inventory);
                return buildValidResponse(inventory);
            }
        }

        return busInventoryOptional
                .map(InventoryController::buildValidResponse)
                .orElseGet(() -> {
                    // If no inventory exists, return 404 with availableSeats=0
                    Map<String, Object> resp = buildInvalidBusNoResponse();
                    return ResponseEntity.ok(resp);
                });
    }

    private static ResponseEntity<Map<String, Object>> buildValidResponse(BusInventory inv) {
        Map<String,Object> resp = new HashMap<>();
        resp.put("busNumber", inv.getBusNumber());
        resp.put("availableSeats", inv.getAvailableSeats());
        resp.put("lastUpdated", inv.getLastUpdated());
        return ResponseEntity.ok(resp);
    }

    private static Map<String, Object> buildInvalidBusNoResponse() {
        Map<String,Object> resp = new HashMap<>();
        resp.put("busNumber", "INVALID");
        return resp;
    }

    @PatchMapping
    ResponseEntity<String> updateInventory(@RequestBody BusInventoryPatchRequest busInventoryPatchRequest){
        var busInventoryOptional = repository.findById(busInventoryPatchRequest.getBusNumber());
        if (busInventoryOptional.isEmpty()){
            return ResponseEntity.badRequest().body("NOT_FOUND");
        }
        if (busInventoryOptional.get().getAvailableSeats() < busInventoryPatchRequest.getBookedSeats()){
            return ResponseEntity.badRequest().body("INVALID_SEATS");
        }

        var invextory = busInventoryOptional.get();
        invextory.setAvailableSeats(busInventoryOptional.get().getAvailableSeats() - busInventoryPatchRequest.getBookedSeats());
        invextory.setLastUpdated(Instant.now());
        repository.save(invextory);

        return ResponseEntity.ok("SUCCESS");
    }

    @PostMapping
    public ResponseEntity<BusInventory> createOrUpdate(@RequestBody BusInventory inv) {
        inv.setLastUpdated(Instant.now());
        BusInventory saved = repository.save(inv);
        return ResponseEntity.ok(saved);
    }
}
