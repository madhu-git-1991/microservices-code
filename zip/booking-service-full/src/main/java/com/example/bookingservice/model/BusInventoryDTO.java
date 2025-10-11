package com.example.bookingservice.model;

import java.time.Instant;

public class BusInventoryDTO {

    private String busNumber;

    private int availableSeats;

    private Instant lastUpdated;

    private double pricePerSeat;

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }
    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
    public Instant getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Instant lastUpdated) { this.lastUpdated = lastUpdated; }
    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    @Override
    public String toString() {
        return "BusInventoryDTO{" +
                "busNumber='" + busNumber + '\'' +
                ", availableSeats=" + availableSeats +
                ", lastUpdated=" + lastUpdated +
                ", pricePerSeat=" + pricePerSeat +
                '}';
    }
}
