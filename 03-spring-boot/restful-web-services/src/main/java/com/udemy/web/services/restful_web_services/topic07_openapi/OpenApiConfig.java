package com.udemy.web.services.restful_web_services.topic07_openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/*
 * Topic    : API documentation with OpenAPI
 * Key idea : springdoc scans your controllers at startup and generates an OpenAPI description
 *            (/v3/api-docs, JSON) plus an interactive page (/swagger-ui.html) where you can
 *            call every endpoint. The docs are generated from the CODE, so they can't drift.
 *            This bean only adds the title/description and declares HTTP Basic login, so the
 *            Swagger page gets an "Authorize" button for the secured endpoints (topic09).
 * Try this : open http://localhost:8080/swagger-ui.html , click Authorize, log in as admin.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful Web Services - learning API")
                        .version("1.0")
                        .description("Users CRUD, validation, filtering, versioning and security examples"))
                .components(new Components().addSecuritySchemes("basicAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}
