package com.mlisena.product.configuration.mongo.migrations;

import com.mlisena.product.entity.Product;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

@ChangeUnit(id="create-products-changelog-01", order = "001", author = "mongock")
@Slf4j
@RequiredArgsConstructor
public class CreateProducts {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void changeSet() {

        log.info("🚀 Insert initials products...");

        if (!mongoTemplate.collectionExists("products")) {
            mongoTemplate.createCollection("products");
        }
        mongoTemplate.insertAll(List.of(
                new Product(null, "Filtro de aceite", "Filtro para sistema de lubricación de motor.", "FO-001", 10.99, true, LocalDateTime.of(2024, 6, 10, 0, 0), LocalDateTime.of(2024, 9, 1, 0, 0)),
                new Product(null, "Pastillas de freno", "Pastillas para frenos delanteros.", "PF-002", 25.50, true, LocalDateTime.of(2024, 7, 5, 0, 0), LocalDateTime.of(2024, 9, 3, 0, 0)),
                new Product(null, "Amortiguador delantero", "Amortiguador para suspensión delantera.", "AD-003", 75.00, true, LocalDateTime.of(2024, 8, 18, 0, 0), LocalDateTime.of(2024, 9, 2, 0, 0)),
                new Product(null, "Bujías", "Juego de bujías para encendido.", "BJ-004", 15.20, true, LocalDateTime.of(2024, 6, 25, 0, 0), LocalDateTime.of(2024, 7, 15, 0, 0)),
                new Product(null, "Filtro de aire", "Filtro para entrada de aire del motor.", "FA-005", 12.30, true, LocalDateTime.of(2024, 9, 4, 0, 0), LocalDateTime.of(2024, 9, 18, 0, 0)),
                new Product(null, "Correa de distribución", "Correa sincronizadora del motor.", "CD-006", 45.99, true, LocalDateTime.of(2024, 10, 1, 0, 0), LocalDateTime.of(2024, 11, 10, 0, 0)),
                new Product(null, "Radiador", "Radiador para sistema de refrigeración.", "RD-007", 110.00, true, LocalDateTime.of(2024, 11, 11, 0, 0), LocalDateTime.of(2024, 12, 2, 0, 0)),
                new Product(null, "Batería 12V", "Batería estándar de 12 voltios.", "BT-008", 89.95, true, LocalDateTime.of(2024, 12, 21, 0, 0), LocalDateTime.of(2025, 1, 5, 0, 0)),
                new Product(null, "Alternador", "Alternador para sistema eléctrico.", "AL-009", 130.00, true, LocalDateTime.of(2025, 1, 14, 0, 0), LocalDateTime.of(2025, 1, 30, 0, 0)),
                new Product(null, "Sensor de oxígeno", "Sensor para mezcla aire-combustible.", "SO-010", 40.00, true, LocalDateTime.of(2024, 7, 12, 0, 0), LocalDateTime.of(2024, 7, 24, 0, 0)),
                new Product(null, "Faro delantero", "Faro halógeno delantero derecho.", "FD-011", 60.00, true, LocalDateTime.of(2024, 9, 21, 0, 0), LocalDateTime.of(2024, 10, 1, 0, 0)),
                new Product(null, "Espejo retrovisor", "Espejo lateral izquierdo manual.", "ER-012", 35.00, true, LocalDateTime.of(2024, 11, 1, 0, 0), LocalDateTime.of(2024, 11, 11, 0, 0)),
                new Product(null, "Filtro de combustible", "Filtro para línea de combustible.", "FC-013", 18.00, true, LocalDateTime.of(2024, 8, 7, 0, 0), LocalDateTime.of(2024, 8, 20, 0, 0)),
                new Product(null, "Compresor de aire acondicionado", "Compresor para sistema de A/C.", "CA-014", 210.00, true, LocalDateTime.of(2025, 1, 31, 0, 0), LocalDateTime.of(2025, 2, 15, 0, 0)),
                new Product(null, "Kit de embrague", "Kit completo para embrague manual.", "KE-015", 150.00, true, LocalDateTime.of(2024, 10, 18, 0, 0), LocalDateTime.of(2024, 11, 2, 0, 0)),
                new Product(null, "Parabrisas", "Cristal delantero laminado.", "PB-016", 95.00, true, LocalDateTime.of(2025, 3, 6, 0, 0), LocalDateTime.of(2025, 3, 12, 0, 0)),
                new Product(null, "Tubo de escape", "Tubo final del sistema de escape.", "TE-017", 80.00, true, LocalDateTime.of(2024, 6, 15, 0, 0), LocalDateTime.of(2024, 6, 25, 0, 0)),
                new Product(null, "Sensor de temperatura", "Sensor para temperatura del refrigerante.", "ST-018", 22.00, true, LocalDateTime.of(2025, 3, 28, 0, 0), LocalDateTime.of(2025, 4, 15, 0, 0)),
                new Product(null, "Motor de arranque", "Motor eléctrico para arranque del vehículo.", "MA-019", 140.00, true, LocalDateTime.of(2024, 12, 6, 0, 0), LocalDateTime.of(2024, 12, 15, 0, 0)),
                new Product(null, "Lámparas LED", "Juego de lámparas LED para faros.", "LL-020", 35.99, true, LocalDateTime.of(2025, 4, 18, 0, 0), LocalDateTime.of(2025, 4, 23, 0, 0))
        ));

        log.info("✅ Successfully insertion of products.");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Filtro de aceite")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Pastillas de freno")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Amortiguador delantero")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Bujías")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Filtro de aire")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Correa de distribución")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Radiador")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Batería 12V")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Alternador")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Sensor de oxígeno")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Faro delantero")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Espejo retrovisor")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Filtro de combustible")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Compresor de aire acondicionado")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Kit de embrague")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Parabrisas")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Tubo de escape")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Sensor de temperatura")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Motor de arranque")), Product.class);
        mongoTemplate.remove(Query.query(Criteria.where("name").is("Lámparas LED")), Product.class);
    }
}