package com.mlisena.product.configuration.feign.inventory;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class InventoryServiceFeignConfiguration {

    private InventoryServiceFeignConfiguration() {
        // Private constructor to prevent instantiation
    }

    @Configuration
    public static class InventoryServiceFeignConfig {

        @Bean
        public ErrorDecoder inventoryServiceErrorDecoder() {
            return new InventoryServiceErrorDecoder();
        }
    }
}
