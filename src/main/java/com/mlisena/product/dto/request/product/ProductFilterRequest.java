package com.mlisena.product.dto.request.product;

public record ProductFilterRequest(
    String name,
    String code,
    Double minPrice,
    Double maxPrice,
    int page,
    int size
) { }
