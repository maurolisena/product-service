package com.mlisena.product.dto.response.product;

public record ProductResponse(
        String id,
        String name,
        String code,
        int stock,
        String description,
        double price
) { }
