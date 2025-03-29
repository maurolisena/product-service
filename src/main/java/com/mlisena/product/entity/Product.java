package com.mlisena.product.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "products")
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;


}
