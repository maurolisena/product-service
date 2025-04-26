package com.mlisena.product.dto.request.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateInventoryRequest(
        @NotBlank(message = "SKU code cannot be blank") String skuCode,
        @PositiveOrZero(message = "Quantity must be zero or positive") Integer quantity) {
}
