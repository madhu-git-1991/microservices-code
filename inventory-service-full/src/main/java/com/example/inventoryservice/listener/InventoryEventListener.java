package com.example.inventoryservice.listener;

import com.example.inventoryservice.model.BusInventory;
import com.example.inventoryservice.repository.BusInventoryRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class InventoryEventListener {

    private final BusInventoryRepository repository;
    private final JmsTemplate jmsTemplate;

    public InventoryEventListener(BusInventoryRepository repository, JmsTemplate jmsTemplate) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
    }

    // Listen for inventory update events (from payment service)
    // Message payload expected: { busNumber: string, seatsAllocated: int }
//    @JmsListener(destination = "inventory.update")
    public void onInventoryUpdate(Map message) {
        try {
            String busNumber = String.valueOf(message.get("busNumber"));
            int seatsAllocated = ((Number) message.getOrDefault("seatsAllocated", 0)).intValue();

            BusInventory inv = repository.findById(busNumber).orElseGet(() -> {
                BusInventory b = new BusInventory();
                b.setBusNumber(busNumber);
                b.setAvailableSeats(0);
                b.setLastUpdated(Instant.now());
                return b;
            });

            // decrement available seats (ensure non-negative)
            int newAvailable = inv.getAvailableSeats() - seatsAllocated;
            if (newAvailable < 0) newAvailable = 0; // or handle compensating transactions
            inv.setAvailableSeats(newAvailable);
            inv.setLastUpdated(Instant.now());
            repository.save(inv);

            // publish inventory.updated event for interested services (optional)
            Map<String, Object> out = Map.of(
                    "busNumber", busNumber,
                    "availableSeats", inv.getAvailableSeats()
            );
            jmsTemplate.convertAndSend("inventory.updated", out);

        } catch (Exception ex) {
            // in production push to DLQ; for now just log
            ex.printStackTrace();
        }
    }
}
