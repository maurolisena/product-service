package com.mlisena.product.service.kafka;

import com.mlisena.product.dto.payload.InventoryCreatedConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductKafkaConsumer {

    @KafkaListener(
        topics = "${kafka.topics.inventory-created-confirmation}",
        groupId = "${kafka.consumer.group-id}"
    )
    public void confirmInventoryCreatedEvent(String payload) {
        log.info("Consumed message from topic: {}", payload);
        InventoryCreatedConfirmation inventoryCreatedConfirmation = KafkaUtils.deserialize(payload, InventoryCreatedConfirmation.class);

        log.info(
            "Inventory confirmed for SKU: {}, success: {}",
            inventoryCreatedConfirmation.skuCode(),
            inventoryCreatedConfirmation.success()
        );
    }

}
