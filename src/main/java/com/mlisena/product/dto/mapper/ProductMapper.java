package com.mlisena.product.dto.mapper;

import com.mlisena.product.dto.request.ProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {
        // Private constructor to prevent instantiation
    }

    public static Product toEntity(ProductRequest productRequest) {
        return Product.builder()
            .name(productRequest.name())
            .code(productRequest.code())
            .description(productRequest.description())
            .price(productRequest.price())
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

    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
            .map(ProductMapper::toResponse)
            .collect(Collectors.toList());
    }

    public static void updateEntity(Product product, ProductRequest productRequest) {
        product.setName(productRequest.name());
        product.setCode(productRequest.code());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
