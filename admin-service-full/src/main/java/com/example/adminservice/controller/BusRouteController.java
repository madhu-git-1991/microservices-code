package com.example.adminservice.controller;

import com.example.adminservice.model.BusRoute;
import com.example.adminservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/admin/routes")
public class BusRouteController {

    private final AdminService adminService;

    public BusRouteController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<BusRoute> all() { return adminService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<BusRoute> get(@PathVariable String id) {
        return adminService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("byBusNo/{busNo}")
    public ResponseEntity<BusRoute> getByBusNo(@PathVariable (name = "busNo") String busNo) {
        return adminService.findByBusNumber(busNo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BusRoute> create(@RequestBody BusRoute route) {
        BusRoute saved = adminService.save(route);
        return ResponseEntity.created(URI.create("/api/admin/routes/" + saved.getBusNumber())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusRoute> update(@PathVariable String id, @RequestBody BusRoute route) {
        return adminService.findById(id).map(existing -> {
            existing.setSource(route.getSource());
            existing.setDestination(route.getDestination());
            existing.setPrice(route.getPrice());
            existing.setTotalSeats(route.getTotalSeats());
            adminService.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
