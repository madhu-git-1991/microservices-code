package com.example.bookingservice.service;

import com.example.bookingservice.model.Booking;
import com.example.bookingservice.repository.BookingRepository;
import com.example.bookingservice.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public BookingService(BookingRepository bookingRepository, PassengerRepository passengerRepository,
                          InventoryServiceClient inventoryServiceClient) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
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
        if(booking.getPassengers().size() > inventory.getAvailableSeats()){
            return Booking.builder().status("Error:Not enough seats").build();
        }
        booking.setStatus("PENDING");
        Booking bookingSaved = bookingRepository.save(booking);
        passengerRepository.saveAll(booking.getPassengers().stream().map(passenger -> {
            passenger.setBookingId(bookingSaved.getBookingNumber());
            return passenger;
        }).toList());
        return bookingSaved;
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
