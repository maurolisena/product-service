package com.mlisena.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProductRequest(
    @NotBlank(message = "Product name cannot be blank") String name,
    @NotBlank(message = "Product code cannot be blank") String code,
    @NotNull(message = "Product active cannot be null") boolean active,
    @NotBlank(message = "Product description cannot be blank") String description,
    @Positive(message = "Product price must be positive") double price
) { }
