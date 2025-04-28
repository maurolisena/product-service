package com.mlisena.product.configuration.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private Topics topics;

    @Getter
    @Setter
    public static class Topics {
        private String inventoryCreated;
    }
}
