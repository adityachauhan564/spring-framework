package com.jbdl63.digitallibrary.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/*
 * @EnableCaching switches on the @Cacheable / @CachePut / @CacheEvict annotations.
 * (A cache = a quick copy kept close by, like keeping your phone's most-used numbers on speed dial.)
 * It is placed here, not on the application class, on purpose: test slices such as @DataJpaTest
 * load the application class but skip @Configuration classes, so those tests don't need a cache at all.
 */
@Configuration
@EnableCaching
public class CachingConfiguration {
}
