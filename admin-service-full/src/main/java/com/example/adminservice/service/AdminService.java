package com.example.adminservice.service;

import com.example.adminservice.model.BusRoute;
import com.example.adminservice.repository.BusRouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final BusRouteRepository repository;

    public AdminService(BusRouteRepository repository) {
        this.repository = repository;
    }

    public List<BusRoute> findAll() { return repository.findAll(); }

    public Optional<BusRoute> findById(String id) { return repository.findById(id); }

    public BusRoute save(BusRoute route) { return repository.save(route); }

    public void deleteById(String id) { repository.deleteById(id); }

    public Optional<BusRoute> findByBusNumber(String busNumber) {
        return repository.findByBusNumber(busNumber);
    }
}
