package com.mlisena.product.dto.request;

public record ProductFilterRequest(
    String name,
    String code,
    Double minPrice,
    Double maxPrice,
    int page,
    int size
) { }
