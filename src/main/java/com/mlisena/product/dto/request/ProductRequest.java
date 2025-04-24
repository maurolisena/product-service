package com.mlisena.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record ProductRequest(
        @NotBlank(message = "Product name cannot be blank") String name,
        @NotBlank(message = "Product code cannot be blank") String code,
        @NotBlank(message = "Product description cannot be blank") String description,
        @Positive(message = "Product price must be positive") double price,
        @PositiveOrZero(message = "Product stock must be zero or positive") int stock,
        @NotBlank(message = "Product category cannot be blank") String categoryId
) { }
