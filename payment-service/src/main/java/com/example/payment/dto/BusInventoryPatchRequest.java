package com.example.payment.dto;

public class BusInventoryPatchRequest {
    private String busNumber;
    private int bookedSeats;

    public BusInventoryPatchRequest(String busNumber, int bookedSeats) {
        this.busNumber = busNumber;
        this.bookedSeats = bookedSeats;
    }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}


