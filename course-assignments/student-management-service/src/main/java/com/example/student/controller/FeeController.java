package com.example.student.controller;

import com.example.student.entity.Fee;
import com.example.student.service.FeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/{studentId}/fees")
public class FeeController {
    private final FeeService svc;

    public FeeController(FeeService svc) { this.svc = svc; }

    // END point to handle student fee payment
// This method would call another microservice (Payment Service)
// to process the actual payment transaction.
// Example (pseudo-code):
// ResponseEntity<PaymentResponse> paymentResponse = restTemplate.postForEntity(
//     "http://payment-service/api/payments", paymentRequest, PaymentResponse.class);
// Here we just simulate a successful payment acknowledgment.

    @PostMapping
    public Fee addFee(@PathVariable Long studentId, @RequestBody Fee fee) {
        return svc.create(studentId, fee);
    }

    @GetMapping
    public List<Fee> listFees(@PathVariable Long studentId) {
        return svc.getByStudent(studentId);
    }

}
