package com.mlisena.product.service.external;

import com.mlisena.product.client.InventoryClient;
import com.mlisena.product.dto.response.inventory.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryClient inventoryClient;

    public int productStock(String skuCode) {
        log.info("Getting stock for SKU code: {}", skuCode);
        Inventory inventory = inventoryClient.getInventory(skuCode);
        return inventory.quantity();
    }
}
