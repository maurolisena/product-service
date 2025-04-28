package com.mlisena.product.dto.response.product;

public record ProductListResponse(
        String id,
        String name,
        String code,
        String description,
        double price
) { }
