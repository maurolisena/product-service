package com.mlisena.product.configuration.mongo.migrations;

import com.mongodb.client.MongoDatabase;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ChangeUnit(id="create-collection-changelog-01", order = "002", author = "mongock")
@Slf4j
@RequiredArgsConstructor
public class InitProductDbMigration {

    private final MongoDatabase mongoDatabase;

    @Execution
    public void createProductsCollection() {
        log.info("🛠️ Creando colección 'products' si no existe");

        boolean collectionExists = mongoDatabase.listCollectionNames()
                .into(new java.util.ArrayList<>())
                .contains("products");

        if (!collectionExists) {
            mongoDatabase.createCollection("products");
            log.info("✅ Colección 'products' creada");
        }
    }

    @RollbackExecution
    public void rollback() {
        log.info("🛠️ Eliminando colección 'products'");

        if (mongoDatabase.listCollectionNames().into(new java.util.ArrayList<>()).contains("products")) {
            mongoDatabase.getCollection("products").drop();
            log.info("✅ Colección 'products' eliminada");
        }
    }
}