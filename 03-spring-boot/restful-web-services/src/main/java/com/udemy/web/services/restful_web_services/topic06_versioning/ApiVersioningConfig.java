package com.udemy.web.services.restful_web_services.topic06_versioning;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Topic    : API versioning
 * Key idea : Once clients depend on your JSON, you cannot change its shape freely.
 *            - A new VERSION lets old clients keep working, while new clients get the new shape.
 *            - Like a new edition of a textbook: old students keep the old edition, new ones get the new.
 *            - Spring Framework 7 (Spring Boot 4) supports this out of the box: tell Spring WHERE
 *              the version is sent, then write @GetMapping(path = ..., version = "2").
 *            - Here the version comes in a request header. Other options are a query
 *              parameter, a media-type parameter, or a path segment (/v2/...).
 * Read     : ApiVersioningConfig -> PersonV1 / PersonV2 -> VersioningController
 */
@Configuration
public class ApiVersioningConfig implements WebMvcConfigurer {

    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("X-API-Version")
                .setDefaultVersion("1")          // no header -> version 1, so old clients never break
                .setVersionRequired(false);
    }
}
