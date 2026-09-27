package com.jbdl63.digitallibrary.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/*
 * @EnableCaching turns the @Cacheable/@CachePut/@CacheEvict annotations on.
 * It lives here and not on the application class: test slices such as @DataJpaTest load the
 * application class but skip @Configuration classes, so they don't need a cache at all.
 */
@Configuration
@EnableCaching
public class CachingConfiguration {
}
