package com.example.inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "bus_inventory")
public class BusInventory {

    @Id
    private String busNumber;

    private int availableSeats;

    private Instant lastUpdated;

    private Double pricePerSeat;

    public BusInventory() {}

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public Instant getLastUpdated() { return lastUpdated; }

    public Double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(Double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public void setLastUpdated(Instant lastUpdated) { this.lastUpdated = lastUpdated; }
}
