package com.mlisena.product.dto.mapper;

import com.mlisena.product.dto.request.product.CreateProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;

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
