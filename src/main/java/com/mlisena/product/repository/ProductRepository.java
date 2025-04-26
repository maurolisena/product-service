package com.mlisena.product.repository;

import com.mlisena.product.dto.request.product.ProductFilterRequest;
import com.mlisena.product.dto.request.product.UpdateProductRequest;
import com.mlisena.product.entity.Product;
import com.mlisena.product.exception.product.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepository implements ProductCustomRepository {

    private final MongoTemplate mongoTemplate;

    public Product findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id).and("active").is(true));
        return mongoTemplate.findOne(query, Product.class);
    }

    @Override
    public Page<Product> findByFilters(ProductFilterRequest filter) {
        Query query = new Query();

        if (filter.name() != null) {
            query.addCriteria(Criteria.where("name").regex(filter.name(), "i"));
        }
        if (filter.code() != null) {
            query.addCriteria(Criteria.where("code").regex(filter.code(), "i"));
        }
        if (filter.minPrice() != null || filter.maxPrice() != null) {
            Criteria priceCriteria = new Criteria();
            if (filter.minPrice() != null && filter.maxPrice() != null) {
                priceCriteria.gte(filter.minPrice()).lte(filter.maxPrice());
            } else if (filter.minPrice() != null) {
                priceCriteria.gte(filter.minPrice());
            } else {
                priceCriteria.lte(filter.maxPrice());
            }
            query.addCriteria(priceCriteria);
        }
        query.addCriteria(Criteria.where("active").is(true));

        long total = mongoTemplate.count(query, Product.class);
        query.with(PageRequest.of(filter.page(), filter.size()));

        List<Product> products = mongoTemplate.find(query, Product.class);
        return new PageImpl<>(products, PageRequest.of(filter.page(), filter.size()), total);
    }

    public void updateById(String id, UpdateProductRequest request) {

        Query query = new Query(Criteria.where("_id").is(id).and("active").is(true));
        Update update = new Update()
                .set("name", request.name())
                .set("code", request.code())
                .set("description", request.description())
                .set("price", request.price())
                .currentDate("updatedAt");
        mongoTemplate.findAndModify(query, update, Product.class);
    }

    public void save(Product product) {
        mongoTemplate.save(product);
    }

    public void deleteById(String id) {

        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update()
                .set("active", false)
                .currentDate("updatedAt");

        Product updatedProduct = mongoTemplate.findAndModify(query, update, Product.class);

        if (updatedProduct == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }
}
