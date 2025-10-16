package com.example.payment.clients;

import com.example.payment.dto.BusInventoryPatchRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class InventoryServiceClient {

    @Value("${inventory-service.url}")
    private String inventoryUrl;



    public String patchInventory(final BusInventoryPatchRequest updateInventoryRequest){
        String resp = RestClient.builder()
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
