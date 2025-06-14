package com.mlisena.product.dto.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record InventoryCreateRequest (
    @NotBlank String skuCode,
    @PositiveOrZero Integer quantity
) {}
