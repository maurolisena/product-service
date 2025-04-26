package com.mlisena.product.dto.mapper;

import com.mlisena.product.dto.request.CreateProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;

import java.time.LocalDateTime;

public class ProductMapper {

    private ProductMapper() {
        // Private constructor to prevent instantiation
    }

    public static Product toEntity(CreateProductRequest request) {
        return Product.builder()
            .name(request.name())
            .code(request.code())
            .description(request.description())
            .price(request.price())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getCode(),
            product.getDescription(),
            product.getPrice()
        );
    }
}
