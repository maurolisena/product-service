package com.mlisena.product.configuration.feign.inventory;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryServiceFeignConfiguration {

    @Bean
    public ErrorDecoder inventoryServiceErrorDecoder() { return new InventoryServiceErrorDecoder(); }
}
