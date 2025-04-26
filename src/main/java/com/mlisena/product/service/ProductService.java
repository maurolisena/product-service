package com.mlisena.product.service;

import com.mlisena.product.dto.mapper.ProductMapper;
import com.mlisena.product.dto.request.CreateProductRequest;
import com.mlisena.product.dto.request.UpdateProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;
import com.mlisena.product.exception.product.ProductNotFoundException;
import com.mlisena.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<ProductResponse> getProducts() {
        log.info("Getting Product List");
        List<Product> productList = productRepository.findAll();
        return ProductMapper.toResponseList(productList);
    }

    public ProductResponse getProductById(String id) {
        log.info("Getting Product with id: {}", id);
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        log.info("Product found with id: {}", id);
        return ProductMapper.toResponse(product);
    }

    public void updateProduct(String id, UpdateProductRequest request) {
        log.info("Updating Product with id: {}", id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresentOrElse(product -> {
            log.info("Product found with id: {}", id);
            ProductMapper.updateEntity(product, request);
            productRepository.save(product);
            log.info("Product updated successfully with id: {}", id);
        }, () -> {
            log.warn("Product we want to update does not exist with id: {}", id);
        });
    }

    public void deleteProduct(String id) {
        log.info("Attempting to delete product with id: {}", id);
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
        log.info("Product deleted successfully with id: {}", id);
    }

}
