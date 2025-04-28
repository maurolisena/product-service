package com.mlisena.product.controller;

import com.mlisena.product.dto.request.product.CreateProductRequest;
import com.mlisena.product.dto.request.product.ProductFilterRequest;
import com.mlisena.product.dto.request.product.UpdateProductRequest;
import com.mlisena.product.dto.response.product.ProductListResponse;
import com.mlisena.product.dto.response.product.ProductResponse;
import com.mlisena.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@RefreshScope
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid CreateProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //TODO: Change ProductListResponse to ProductResponse
    @GetMapping("/search")
    public ResponseEntity<Page<ProductListResponse>> searchProducts(ProductFilterRequest filter) {
        Page<ProductListResponse> products = productService.searchProducts(filter);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
        @PathVariable String id,
        @RequestBody @Valid UpdateProductRequest request
    ) {
        productService.updateProduct(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
