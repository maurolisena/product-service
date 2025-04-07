package com.mlisena.product.dto.mapper;

import com.mlisena.product.dto.request.ProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    private ProductMapper() {
        // Private constructor to prevent instantiation
    }

    public static Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static void updateEntity(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
    }
}
