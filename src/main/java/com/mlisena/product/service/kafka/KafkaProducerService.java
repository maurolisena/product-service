package com.mlisena.product.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlisena.product.configuration.kafka.KafkaProperties;
import com.mlisena.product.dto.payload.CreateInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaProperties kafkaProperties;
    private final ObjectMapper objectMapper;

    public void sendCreateInventoryEvent(CreateInventoryEvent payload) {
        String topic = kafkaProperties.getTopics().getInventoryCreated();
        String jsonPayload;
        try {
            jsonPayload = objectMapper.writeValueAsString(payload);
        } catch (Exception e) {
            log.error("Error serializing payload to JSON: {}", e.getMessage());
            throw new RuntimeException("Error serializing payload to JSON", e);
        }
        log.info("Producing inventory created event to kafka: {}", jsonPayload);
        kafkaTemplate.send(topic, payload.getSkuCode(), jsonPayload);
        log.info("Inventory created event sent successfully");
    }
}
