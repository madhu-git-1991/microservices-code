package com.example.payment.clients;

import com.example.payment.dto.BookingPatchRequest;
import com.example.payment.dto.BookingResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BookingServiceClient {

    @Value("${booking-service.url}")
    private String bookingUrl;


    public BookingResponseDTO getBookingDetails(final String bookingId){
        BookingResponseDTO resp = RestClient.builder()
                .baseUrl(bookingUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/bookings/")
                        .path(bookingId)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(BookingResponseDTO.class).getBody();

        return resp;
    }

    public BookingResponseDTO patchBooking(final String bookingId){
        BookingResponseDTO resp = RestClient.builder()
                .baseUrl(bookingUrl)
                .build()
                .patch()
                .uri(uriBuilder -> uriBuilder.path("/api/bookings")
                        .build())
                .header("Content-Type", "application/json")
                .body(new BookingPatchRequest(bookingId, "SUCCESS"))
                .retrieve()
                .toEntity(BookingResponseDTO.class).getBody();

        return resp;
    }

    public String getInventoryUrl() {
        return bookingUrl;
    }

    public void setInventoryUrl(String inventoryUrl) {
        this.bookingUrl = inventoryUrl;
    }
}
