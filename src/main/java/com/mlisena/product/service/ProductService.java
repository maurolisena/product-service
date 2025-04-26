package com.mlisena.product.service;

import com.mlisena.product.dto.mapper.ProductMapper;
import com.mlisena.product.dto.request.CreateProductRequest;
import com.mlisena.product.dto.request.ProductFilterRequest;
import com.mlisena.product.dto.request.UpdateProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;
import com.mlisena.product.exception.product.ProductNotFoundException;
import com.mlisena.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(CreateProductRequest request) {
        log.info("Creating Product with request: {}", request);
        Product product = ProductMapper.toEntity(request);
        productRepository.save(product);
        log.info("Product saved with id: {}", product.getId());
    }

    public ProductResponse getProductById(String id) {
        log.info("Getting Product with id: {}", id);
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        log.info("Product found with id: {}", id);
        return ProductMapper.toResponse(product);
    }

    public Page<ProductResponse> searchProducts(ProductFilterRequest request) {
        log.info("Searching Products with filter: {}", request);
        Page<Product> productPage = productRepository.findByFilters(request);
        log.info("Products found: {}", productPage.getTotalElements());
        return productPage.map(ProductMapper::toResponse);
    }

    public void updateProduct(String id, UpdateProductRequest request) {
        log.info("Updating Product with id: {}", id);
        Product product = productRepository.findById(id);
        if (product != null) {
            productRepository.updateById(id, request);
            log.info("Product updated successfully with id: {}", id);
        } else {
            log.warn("Product not found with id: {}", id);
        }
    }

    public void deleteProduct(String id) {
        log.info("Soft deleting Product with id: {}", id);
        productRepository.deleteById(id);
        log.info("Product soft deleted with id: {}", id);
    }

}
