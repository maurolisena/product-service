package com.mlisena.product.configuration.gprc;

import inventory.InventoryClientGRPCGrpc;
import inventory.InventoryClientGRPCGrpc.InventoryClientGRPCBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class GrpcClientConfig {

    @Bean
    public InventoryClientGRPCBlockingStub inventoryStub() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("inventory-service", 19003)
                .usePlaintext()
                .build();

        return InventoryClientGRPCGrpc.newBlockingStub(channel);
    }
}