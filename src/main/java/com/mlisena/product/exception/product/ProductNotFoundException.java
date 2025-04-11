package com.mlisena.product.exception.product;

import com.mlisena.product.exception.common.NotFoundException;

import java.io.Serial;

public class ProductNotFoundException extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}