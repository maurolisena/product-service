package com.mlisena.product.client;

import com.mlisena.product.configuration.feign.inventory.InventoryServiceFeignConfiguration;
import com.mlisena.product.dto.request.inventory.CreateInventoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "INVENTORY-SERVICE",
        configuration = InventoryServiceFeignConfiguration.class
)
public interface InventoryClient {

    @PostMapping("/api/inventory")
    void createInventory(CreateInventoryRequest request);
}
