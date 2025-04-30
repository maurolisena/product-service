package com.mlisena.product.service.external;

import com.mlisena.product.client.InventoryClient;
import com.mlisena.product.dto.response.inventory.Inventory;
import com.mlisena.product.service.redis.InventoryRedisHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryClient inventoryClient;
    private final InventoryRedisHelper inventoryRedisHelper;

    public int getProductStock(String skuCode) {
        log.info("Getting stock for SKU code: {}", skuCode);
        Integer cachedStock = inventoryRedisHelper.getCachedInventoryStock(skuCode);

        if (cachedStock != null) {
            return cachedStock;
        }

        Inventory inventory = inventoryClient.getInventory(skuCode);
        log.info("Stock for Product with SKU code {}: {}", skuCode, inventory);

        inventoryRedisHelper.cacheProductStock(skuCode, inventory.quantity());
        return inventory.quantity();
    }

    public Map<String, Integer> getProductStockMap(List<String> skuCodes) {
        Map<String, Integer> cachedStock = inventoryRedisHelper.getCachedInventoryStockList(skuCodes);

        List<String> missingSkuCodes = skuCodes.stream()
                .filter(skuCode -> !cachedStock.containsKey(skuCode))
                .toList();

        if (missingSkuCodes.isEmpty()) {
            log.info("All SKU codes found in cache");
            return cachedStock;
        }

        log.info("Getting missing SKU codes in cache");
        List<Inventory> inventories = inventoryClient.getInventories(missingSkuCodes);

        inventories.forEach(inventory -> {
            inventoryRedisHelper.cacheProductStock(inventory.skuCode(), inventory.quantity());
            cachedStock.put(inventory.skuCode(), inventory.quantity());
        });

        return cachedStock;
    }
}
