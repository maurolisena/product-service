package com.mlisena.product.repository;

import com.mlisena.product.dto.request.ProductFilterRequest;
import com.mlisena.product.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductCustomRepository {
    Page<Product> findByFilters(ProductFilterRequest filter);
}
