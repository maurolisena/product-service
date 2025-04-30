package com.mlisena.product.client;

import com.mlisena.product.configuration.feign.inventory.InventoryServiceFeignConfiguration;
import com.mlisena.product.dto.response.inventory.Inventory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "INVENTORY-SERVICE",
        configuration = InventoryServiceFeignConfiguration.class
)
public interface InventoryClient {

    @GetMapping("/api/inventory/{skuCode}")
    Inventory getInventory(@PathVariable @NotBlank String skuCode);

    @PostMapping("/api/inventory/list")
    List<Inventory> getInventories(@RequestBody @NotBlank List<String> skuCodeList);

}
