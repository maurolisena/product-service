package com.mlisena.product.mapper;

import com.mlisena.product.dto.request.CreateProductRequest;
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

    public static ProductResponse toResponse(Product product, int productStock) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getCode(),
            productStock,
            product.getDescription(),
            product.getPrice()
        );
    }
}
