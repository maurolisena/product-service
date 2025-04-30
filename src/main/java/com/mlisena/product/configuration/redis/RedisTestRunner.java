package com.mlisena.product.configuration.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisTestRunner implements CommandLineRunner {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void run(String... args) {
        redisTemplate.opsForValue().set("connection-key", "OK");
        String value = redisTemplate.opsForValue().get("connection-key");
        System.out.println("Connection to Redis: " + value);
    }
}
