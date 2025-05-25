package com.mlisena.product.configuration.mongo.migrations;

import io.mongock.driver.mongodb.springdata.v3.SpringDataMongoV3Driver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongockConfig {

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(MongoTemplate mongoTemplate) {
        SpringDataMongoV3Driver driver = SpringDataMongoV3Driver.withDefaultLock(mongoTemplate);

        return MongockSpringboot.builder()
                .setDriver(driver)
                .addChangeLogsScanPackage("com.mlisena.product.configuration.mongo.migrations")
                .buildInitializingBeanRunner();
    }
}
