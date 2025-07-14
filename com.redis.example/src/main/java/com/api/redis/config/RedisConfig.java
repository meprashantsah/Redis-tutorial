package com.api.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration  // Marks this class as a source of Spring bean definitions
public class RedisConfig {

    // Bean to create a RedisConnectionFactory using Lettuce (a scalable Redis client)
    @Bean
    public RedisConnectionFactory connectionFactory() {
        // LettuceConnectionFactory creates connections to Redis
        // You can optionally pass host and port or use application.properties to configure
        return new LettuceConnectionFactory(); // Default: localhost:6379
    }

    // Bean to configure RedisTemplate which is used to perform Redis operations
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // RedisTemplate is the central class for interacting with Redis
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        // Set the Redis connection factory that provides Redis connections
        redisTemplate.setConnectionFactory(connectionFactory());

        // Set the key serializer - this converts Java Strings to a Redis-compatible format
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // Set the value serializer - this converts Java objects to JSON for storing in Redis
        // GenericJackson2JsonRedisSerializer uses Jackson to convert POJOs to/from JSON
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // You can also set serializers for hash keys/values if using Redis hashes
        // redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }
}
