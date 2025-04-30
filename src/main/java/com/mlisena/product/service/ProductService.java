package com.mlisena.product.service;

import com.mlisena.product.dto.mapper.ProductMapper;
import com.mlisena.product.dto.payload.CreateInventoryEvent;
import com.mlisena.product.dto.request.product.CreateProductRequest;
import com.mlisena.product.dto.request.product.ProductFilterRequest;
import com.mlisena.product.dto.request.product.UpdateProductRequest;
import com.mlisena.product.dto.response.product.ProductResponse;
import com.mlisena.product.entity.Product;
import com.mlisena.product.repository.ProductRepository;
import com.mlisena.product.service.external.InventoryService;
import com.mlisena.product.service.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryService inventoryService;
    private final KafkaProducerService kafkaProducerService;

    public ProductResponse createProduct(CreateProductRequest request) {
        log.info("Creating Product with request: {}", request);
        Product product = ProductMapper.toEntity(request);
        productRepository.save(product);
        log.info("Product saved with id: {}", product.getId());

        CreateInventoryEvent event = CreateInventoryEvent.builder()
            .skuCode(product.getCode())
            .quantity(request.quantity())
            .build();
        kafkaProducerService.sendCreateInventoryEvent(event);

        return ProductMapper.toResponse(product, 1);
    }

    public ProductResponse getProductById(String id) {
        log.info("Getting Product with id: {}", id);
        Product product = productRepository.findById(id);
        log.info("Product found with id: {}", id);
        int stock = inventoryService.getProductStock(product.getCode());
        return ProductMapper.toResponse(product, stock);
    }

    public Page<ProductResponse> searchProducts(ProductFilterRequest request) {
        log.info("Searching Products with filter: {}", request);
        Page<Product> productPage = productRepository.findByFilters(request);
        log.info("Products found: {}", productPage.getTotalElements());

        log.info("Getting stock for Products");
        Map<String, Integer> stockMap = inventoryService.getProductStockMap(
            productPage.getContent().stream()
                .map(Product::getCode)
                .toList());

        return productPage.map(product -> {
            int stock = stockMap.getOrDefault(product.getCode(), 0);
            return ProductMapper.toResponse(product, stock);
        });
    }

    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        log.info("Updating Product with id: {}", id);
        Product product = productRepository.updateById(id, request);
        int stock = inventoryService.getProductStock(product.getCode());
        return ProductMapper.toResponse(product, stock);
    }

    public void deleteProduct(String id) {
        log.info("Soft deleting Product with id: {}", id);
        productRepository.deleteById(id);
        log.info("Product soft deleted with id: {}", id);
    }

}
