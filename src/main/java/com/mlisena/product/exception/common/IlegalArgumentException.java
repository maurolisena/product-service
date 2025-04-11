package com.mlisena.product.exception.common;

import java.io.Serial;

public class IlegalArgumentException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IlegalArgumentException(String message) {
        super(message);
    }
}
