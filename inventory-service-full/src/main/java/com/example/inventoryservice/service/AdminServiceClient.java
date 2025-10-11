package com.example.inventoryservice.service;

import com.example.inventoryservice.dtos.BusRouteDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AdminServiceClient {

    @Value("${admin-service.url}")
    private String adminServiceUrl;

    public BusRouteDTO fetchRouteDetails(final String busNo){
        var inventoryDTO = RestClient.builder()
                .baseUrl(adminServiceUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/admin/routes/byBusNo/")
                        .path(busNo)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(BusRouteDTO.class).getBody();

        return inventoryDTO;
    }

}
