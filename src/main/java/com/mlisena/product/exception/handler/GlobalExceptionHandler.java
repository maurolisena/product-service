package com.mlisena.product.exception.handler;

import com.mlisena.product.exception.common.IlegalArgumentException;
import com.mlisena.product.exception.product.ProductAlreadyExistsException;
import com.mlisena.product.exception.product.ProductIllegalArgumentException;
import com.mlisena.product.exception.product.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String detail, String instancePath, String typeUri, String title) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setInstance(URI.create(instancePath));
        problemDetail.setTitle(title != null ? title : status.getReasonPhrase());
        problemDetail.setType(URI.create(typeUri != null ? typeUri : "about:blank"));
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String path = getPath(request);
        log.error("Validation error at {}: {}", path, ex.getMessage());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();
        ProblemDetail problemDetail = createProblemDetail(
            HttpStatus.BAD_REQUEST,
            "Validation failed: " + String.join(", ", errors),
            path,
            "https://example.com/errors/validation-failed",
            "Validation Error"
        );
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ExceptionHandler({ProductIllegalArgumentException.class, IlegalArgumentException.class})
    public ProblemDetail handleIllegalArgument(ProductIllegalArgumentException ex, WebRequest request) {
        String path = getPath(request);
        log.error("Illegal argument at {}: {}", path, ex.getMessage());
        return createProblemDetail(
            HttpStatus.BAD_REQUEST,
            ex.getMessage(),
            path,
            "https://example.com/errors/illegal-argument",
            "Illegal Argument"
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(ProductNotFoundException ex, WebRequest request) {
        String path = getPath(request);
        log.error("Product not found at {}: {}", path, ex.getMessage());
        return createProblemDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage(),
            path,
            "https://example.com/errors/product-not-found",
            "Product Not Found"
        );
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ProblemDetail handleProductAlreadyExists(ProductAlreadyExistsException ex, WebRequest request) {
        String path = getPath(request);
        log.error("Product already exists at {}: {}", path, ex.getMessage());
        return createProblemDetail(
            HttpStatus.CONFLICT,
            ex.getMessage(),
            path,
            "https://example.com/errors/product-already-exists",
            "Product Already Exists"
        );
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex, WebRequest request) {
        String path = getPath(request);
        log.error("Unhandled exception at {}: {}", path, ex.getMessage());
        return createProblemDetail(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An unexpected error occurred: " + ex.getMessage(),
            path,
            "https://example.com/errors/internal-server-error",
            "Internal Server Error"
        );
    }
}
