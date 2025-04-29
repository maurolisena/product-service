package com.mlisena.product.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class KafkaUtils {

    private final ObjectMapper objectMapper;

    public String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error serializing object to JSON: {}", e.getMessage());
            throw new RuntimeException("Error serializing object to JSON", e);
        }
    }
}
