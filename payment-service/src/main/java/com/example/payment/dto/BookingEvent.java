package com.example.payment.dto;

import java.io.Serializable;

/*
  DTO representing the booking event sent by Booking Service.
  Example payload (JSON):
  {
    "bookingRef": "BK-12345",
    "busNo": "BUS-101",
    "seats": 2,
    "amount": 500.0
  }
*/
public class BookingEvent implements Serializable {
    private String bookingRef;
    private String busNo;
    private int seats;
    private double amount;

    public BookingEvent() {}
    public String getBookingRef() { return bookingRef; }
    public void setBookingRef(String bookingRef) { this.bookingRef = bookingRef; }
    public String getBusNo() { return busNo; }
    public void setBusNo(String busNo) { this.busNo = busNo; }
    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
