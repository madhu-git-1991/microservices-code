package com.example.payment.service;

import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Payment processor handles creating Payment records and publishing events.
 * In a real world setup this would integrate with a Payment Gateway (Stripe/PayU/etc).
 * Here we *simulate* payment success and publish a "payment.processed" message to the event queue.
 */
@Service
public class PaymentProcessorService {

    private final PaymentRepository repo;
    private final JmsTemplate jmsTemplate;

    public PaymentProcessorService(PaymentRepository repo, JmsTemplate jmsTemplate) {
        this.repo = repo;
        this.jmsTemplate = jmsTemplate;
    }

    public Payment processPayment(String bookingRef, double amount) {
        // create payment record (PENDING)
        Payment p = new Payment(bookingRef, amount);
        p.setStatus("PENDING");
        p.setPaidAt(null);
        p = repo.save(p);

        // Simulate payment gateway call here (card/UPI processing).
        // For demo, assume payment always succeeds.
        p.setStatus("COMPLETED");
        p.setPaidAt(LocalDateTime.now());
        p = repo.save(p);

        // Publish event so Inventory/Booking services can continue choreography.
        // The event payload can be a small DTO or JSON string.
        // Using JMS queue 'payment.processed' here.
//        String event = String.format("{\"bookingRef\":\"%s\",\"paymentRef\":\"%s\",\"amount\":%s}", p.getBookingRef(), p.getPaymentRef(), p.getAmount());
//        jmsTemplate.convertAndSend("payment.processed", event);

        return p;
    }
}
