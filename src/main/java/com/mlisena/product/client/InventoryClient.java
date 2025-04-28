package com.mlisena.product.client;

import com.mlisena.product.configuration.feign.inventory.InventoryServiceFeignConfiguration;
import com.mlisena.product.dto.response.inventory.Inventory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "INVENTORY-SERVICE",
        configuration = InventoryServiceFeignConfiguration.class
)
public interface InventoryClient {

    @GetMapping("/api/inventory/{skuCode}")
    Inventory getInventory(@PathVariable @NotBlank String skuCode);

}
