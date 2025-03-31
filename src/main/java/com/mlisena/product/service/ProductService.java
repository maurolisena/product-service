package com.mlisena.product.service;

import com.mlisena.product.dto.request.ProductRequest;
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

    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public void createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .build();

        productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());

            productRepository.save(product);
        }
    }
}
