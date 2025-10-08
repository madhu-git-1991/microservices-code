package com.example.bookingservice.service;

import com.example.bookingservice.model.BusInventoryDTO;
import com.example.bookingservice.model.BusInventoryPatchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InventoryServiceClient {

    @Value("${inventory-service.url}")
    private String inventoryUrl;

    public BusInventoryDTO fetchInventory(final String busNo){
        var inventoryDTO = RestClient.builder()
                .baseUrl(inventoryUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/api/inventory/check/")
                        .path(busNo)
                        .build())
                .header("Content-Type", "application/json")
                .retrieve()
                .toEntity(BusInventoryDTO.class).getBody();

        return inventoryDTO;
    }



    public String patchInventory(final BusInventoryPatchRequest updateInventoryRequest){
        var resp = RestClient.builder()
                .baseUrl(inventoryUrl)
                .build()
                .patch()
                .uri(uriBuilder -> uriBuilder.path("/api/inventory")
                        .build())
                .header("Content-Type", "application/json")
                .body(updateInventoryRequest)
                .retrieve()
                .toEntity(String.class).getBody();

        return resp;
    }

    public String getInventoryUrl() {
        return inventoryUrl;
    }

    public void setInventoryUrl(String inventoryUrl) {
        this.inventoryUrl = inventoryUrl;
    }
}
