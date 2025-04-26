package com.mlisena.product.configuration.feign.inventory;

import feign.Response;
import feign.codec.ErrorDecoder;

public class InventoryServiceErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 400 -> new IllegalArgumentException("Bad Request: " + response.reason());
            case 401 -> new IllegalAccessException("Unauthorized access to Inventory Service: " + response.reason());
            case 403 -> new SecurityException("Inventory service Forbidden resource: " + response.reason());
            case 404 -> new IllegalStateException("Inventory service resource Not Found: " + response.reason());
            case 500 -> new RuntimeException("Inventory service Internal Server Error: " + response.body().toString());
            default -> defaultErrorDecoder.decode(s, response);
        };
    }
}