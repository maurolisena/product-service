package com.mlisena.product.service;

import com.mlisena.product.client.InventoryClient;
import com.mlisena.product.dto.request.inventory.CreateInventoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryClient inventoryClient;

    public void createInventory(CreateInventoryRequest request) {
        log.info("Calling inventory service to create inventory to product with code {}", request.skuCode());
        inventoryClient.createInventory(request);
    }
}
