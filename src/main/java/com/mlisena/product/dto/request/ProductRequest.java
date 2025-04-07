package com.mlisena.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Product code cannot be blank")
    private String code;

    @NotBlank(message = "Product description cannot be blank")
    private String description;

    @Positive(message = "Product price must be positive")
    private double price;

    @PositiveOrZero(message = "Product stock must be zero or positive")
    private int stock;

    @NotBlank(message = "Product category cannot be blank")
    private String categoryId;
}
