package com.mlisena.product.dto.response;

public record ProductResponse(
        String id,
        String name,
        String code,
        int stock,
        String description,
        double price
) { }
