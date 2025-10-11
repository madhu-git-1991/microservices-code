package com.example.payment.listener;

import com.example.payment.dto.BookingEvent;
import com.example.payment.service.PaymentProcessorService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Listens for booking events from Booking Service.
 * Booking Service will publish a message (e.g. to queue 'booking.created') after creating booking with status PENDING.
 * This listener consumes that event and triggers payment processing.
 *
 * NOTE: The actual Booking Service is not implemented here. This class only demonstrates how the Payment Service
 * would be wired to receive events via JMS (ActiveMQ) and process them.
 */
@Component
public class BookingEventListener {

    private final PaymentProcessorService paymentService;

    public BookingEventListener(PaymentProcessorService paymentService) {
        this.paymentService = paymentService;
    }

//    @JmsListener(destination = "booking.created")
    public void onBookingCreated(BookingEvent event) {
        // In production, validate event, handle idempotency, and handle failures (retry/dead-letter).
        // For simplicity, directly process payment.
        paymentService.processPayment(event.getBookingRef(), event.getAmount());
    }
}
