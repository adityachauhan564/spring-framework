package com.jbdl63.digitallibrary.configuration;

import java.time.Duration;

import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.jbdl63.digitallibrary.model.Author;
import com.jbdl63.digitallibrary.service.AuthorService;

/*
 * Only with the "redis" profile. The connection itself needs no code: Boot builds the
 * RedisConnectionFactory (Lettuce client) from spring.data.redis.host/port.
 *
 * Serialization = how a Java object becomes the bytes Redis stores.
 *   JDK serialization (Boot's default for the cache) stores binary data that includes the full
 *   class name, so renaming a package makes stored values unreadable, and redis-cli shows gibberish.
 *   JSON for one known type (Author) stores plain readable JSON with no class names in it.
 */
@Configuration
@Profile("redis")
public class RedisConfiguration {

    // The "authors" @Cacheable cache: JSON values that expire after 10 minutes
    @Bean
    public RedisCacheManagerBuilderCustomizer authorsCacheAsJson() {
        return builder -> builder.withCacheConfiguration(AuthorService.CACHE,
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10))
                        .serializeValuesWith(SerializationPair.fromSerializer(new JacksonJsonRedisSerializer<>(Author.class))));
    }

    // For the /v1/redis playground: String keys, Author values as JSON
    @Bean
    public RedisTemplate<String, Author> authorRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Author> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        JacksonJsonRedisSerializer<Author> json = new JacksonJsonRedisSerializer<>(Author.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(json);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(json);
        return template;
    }
}
