package com.example.bookingservice.service;
import com.example.bookingservice.model.PaymentDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PaymentServiceClient {

    @Value("${payment-service.url}")
    private String paymentServiceUrl;

    public String doPayment(String bookingId, double amount){
      return RestClient.builder()
              .baseUrl(paymentServiceUrl)
              .build()
              .post()
              .uri(uriBuilder -> uriBuilder.path("/api/payments/pay")
                      .queryParam("bookingRef", bookingId)
                      .queryParam("amount", amount)
                      .build())
              .header("Content-Type", "application/json")
              .retrieve()
              .toEntity(PaymentDTO.class).getBody().getStatus();

    }
}
