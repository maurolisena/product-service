package com.mlisena.product.service;

import com.mlisena.product.dto.mapper.ProductMapper;
import com.mlisena.product.dto.request.ProductRequest;
import com.mlisena.product.dto.response.ProductResponse;
import com.mlisena.product.entity.Product;
import com.mlisena.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getProducts() {
        List<Product> productList = productRepository.findAll();
        return ProductMapper.toResponseList(productList);
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toResponse(product);
    }

    public void createProduct(ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            ProductMapper.updateEntity(product, productRequest);
            productRepository.save(product);
        }
    }
}
