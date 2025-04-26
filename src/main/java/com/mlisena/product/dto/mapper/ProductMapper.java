package com.mlisena.product.dto.mapper;

import com.mlisena.product.dto.request.CreateProductRequest;
import com.mlisena.product.dto.request.UpdateProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {
        // Private constructor to prevent instantiation
    }

    public static Product toEntity(CreateProductRequest request) {
        return Product.builder()
            .name(request.name())
            .code(request.code())
            .active(true)
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

    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
            .map(ProductMapper::toResponse)
            .collect(Collectors.toList());
    }

    public static void updateEntity(Product product, UpdateProductRequest request) {
        product.setName(request.name());
        product.setCode(request.code());
        product.setActive(true);
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setUpdatedAt(LocalDateTime.now());
    }
}
