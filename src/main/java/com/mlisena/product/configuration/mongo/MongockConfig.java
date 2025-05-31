package com.mlisena.product.configuration.mongo;

import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongockConfig {

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(
        ApplicationContext springContext,
        MongoTemplate mongoTemplate
    ) {

        SpringDataMongoV4Driver driver = SpringDataMongoV4Driver.withDefaultLock(mongoTemplate);

        return MongockSpringboot.builder()
                .setDriver(driver)
                .setSpringContext(springContext)
                .addChangeLogsScanPackage("com.mlisena.product.configuration.mongo.migrations")
                .buildInitializingBeanRunner();
    }
}
