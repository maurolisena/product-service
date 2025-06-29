package com.mlisena.product.service.external;

import com.mlisena.product.service.redis.InventoryRedisHelper;
import inventory.Inventory.*;
import inventory.InventoryClientGRPCGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryGprcService {

    private final InventoryClientGRPCGrpc.InventoryClientGRPCBlockingStub inventoryStub;
    private final InventoryRedisHelper inventoryRedisHelper;

    public int getProductStock(String skuCode) {
        log.info("Getting stock for SKU code: {}", skuCode);
        GetInventoryRequest request = GetInventoryRequest.newBuilder()
                .setSkuCode(skuCode)
                .build();

        InventoryResponse response = inventoryStub.getInventory(request);
        log.info("Stock for Product with SKU code {}: {}", skuCode, response.getQuantity());
        return response.getQuantity();
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

        GetInventoriesRequest request = GetInventoriesRequest.newBuilder()
                .addAllSkuCodes(missingSkuCodes)
                .build();

        log.info("Getting missing SKU codes in cache");
        InventoriesResponse inventories = inventoryStub.getInventories(request);

        inventories.getInventoriesList().forEach(inventory -> {
            inventoryRedisHelper.cacheProductStock(inventory.getSkuCode(), inventory.getQuantity());
            cachedStock.put(inventory.getSkuCode(), inventory.getQuantity());
        });

        return cachedStock;
    }
}
