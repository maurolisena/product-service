package com.mlisena.product.configuration.mongo;

import com.mongodb.client.MongoClient;
import io.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver;
import io.mongock.runner.springboot.EnableMongock;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMongock
public class MongockConfig {

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(MongoClient mongoClient) {
        return MongockSpringboot.builder()
                .setDriver(MongoSync4Driver.withDefaultLock(mongoClient, "product_db"))
                .addChangeLogsScanPackage("com.mlisena.product.migration")
                .buildInitializingBeanRunner();
    }
}