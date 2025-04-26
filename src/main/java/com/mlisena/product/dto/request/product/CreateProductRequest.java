package com.mlisena.product.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record CreateProductRequest(
        @NotBlank(message = "Product name cannot be blank") String name,
        @NotBlank(message = "Product code cannot be blank") String code,
        @PositiveOrZero(message = "Product quantity must be zero or positive") int quantity,
        @NotBlank(message = "Product description cannot be blank") String description,
        @Positive(message = "Product price must be positive") double price
) { }
