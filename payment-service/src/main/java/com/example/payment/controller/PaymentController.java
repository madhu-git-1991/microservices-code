package com.example.payment.controller;

import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentProcessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentProcessorService service;

    public PaymentController(PaymentProcessorService service) {
        this.service = service;
    }

    // Public API to take payment for a booking.
    // In a real system, this endpoint would be protected (OAuth2/JWT).
    @PostMapping("/pay")
    public ResponseEntity<Payment> pay(@RequestParam (name = "bookingRef") String bookingRef,
                                       @RequestParam (name = "amount") double amount) {
        Payment p = service.processPayment(bookingRef, amount);
        return ResponseEntity.ok(p);
    }
}
