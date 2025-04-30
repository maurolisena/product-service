package com.mlisena.product.service.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryRedisHelper {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String INVENTORY_KEY_PREFIX  = "product-stock::";

    public void cacheProductStock(String skuCode, Integer quantity) {
        log.info("Cache Product Stock for SKU code {}: {}", skuCode, quantity);
        redisTemplate.opsForValue().set(INVENTORY_KEY_PREFIX + skuCode, quantity.toString(), Duration.ofMinutes(10));
    }

    public Integer getCachedInventoryStock(String skuCode) {
        String quantity = redisTemplate.opsForValue().get(INVENTORY_KEY_PREFIX + skuCode);

        if (quantity == null) {
            log.info("Stock for SKU code {} not found in cache", skuCode);
            return null;
        }

        log.info("Stock for SKU code {} found in cache: {}", skuCode, quantity);
        return Integer.parseInt(quantity);
    }

    public Map<String, Integer> getCachedInventoryStockList(List<String> skuCodes) {
        List<String> keys = skuCodes.stream()
                .map(skuCode -> INVENTORY_KEY_PREFIX + skuCode)
                .toList();

        log.info("Getting cached stocks for product SKU codes");
        List<String> quantities = redisTemplate.opsForValue().multiGet(keys);

        Map<String, Integer> stockList = new HashMap<>();
        for (int i = 0; i < skuCodes.size(); i++) {
            String skuCode = skuCodes.get(i);
            String quantity = quantities.get(i);
            if (quantity != null) {
                stockList.put(skuCode, Integer.parseInt(quantity));
            }
        }

        return stockList;
    }
}
