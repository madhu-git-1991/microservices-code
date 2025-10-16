package com.example.payment.dto;

public class BookingPatchRequest {
    private String bookingNumber;
    private String status;

    public BookingPatchRequest(String bookingNumber, String status) {
        this.bookingNumber = bookingNumber;
        this.status = status;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

