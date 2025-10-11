package com.example.bookingservice.service;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.model.BusInventoryPatchRequest;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final PaymentServiceClient paymentServiceClient;

    public BookingService(BookingRepository bookingRepository, PassengerRepository passengerRepository,
                          InventoryServiceClient inventoryServiceClient, PaymentServiceClient paymentServiceClient) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.paymentServiceClient = paymentServiceClient;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        //check inventory
        final var inventory = inventoryServiceClient.fetchInventory(booking.getBusNumber());
        if(booking.getNumberOfSeats() > inventory.getAvailableSeats()){
            return Booking.builder().status("Error:Not enough seats").build();
        }
        booking.setStatus("PENDING");
        Booking bookingSaved = bookingRepository.save(booking);
        passengerRepository.saveAll(booking.getPassengers().stream().map(passenger -> {
            passenger.setBookingId(bookingSaved.getBookingNumber());
            return passenger;
        }).toList());

        //payment, fetch price from relevant microservice
        String paymentStatus = paymentServiceClient.doPayment(String.valueOf(bookingSaved.getBookingNumber()), inventory.getPricePerSeat()*booking.getNumberOfSeats());
        log.info("payment of {}, for booking {} statyus={}", inventory.getPricePerSeat()*booking.getNumberOfSeats(), bookingSaved.getBookingNumber(), paymentStatus);
        //update inventory
        String inventoryUpdateStatus = inventoryServiceClient.patchInventory(BusInventoryPatchRequest.builder()
                .busNumber(booking.getBusNumber()).bookedSeats(booking.getNumberOfSeats()).build());
        log.info("Update inventory status: {}", inventoryUpdateStatus);
        // if payment is complete, mark the booking as success else, keep it as pending for now, check status later in batch and update accordingly
        if ("COMPLETED".equalsIgnoreCase(paymentStatus)){
            bookingSaved.setStatus("SUCCESS");
        }
        return bookingSaved;
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
