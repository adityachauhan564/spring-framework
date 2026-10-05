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
 * Key idea : - springdoc reads your controllers at startup and creates an OpenAPI description
 *              (/v3/api-docs, in JSON), plus an interactive page (/swagger-ui.html) where you
 *              can try every endpoint.
 *            - The docs are made from the CODE itself, so they can never go out of date.
 *              Like a restaurant menu that updates by itself whenever the chef adds a dish.
 *            - This bean only adds the title and description, and declares HTTP Basic login,
 *              so the Swagger page gets an "Authorize" button for the secured endpoints (topic09).
 * Try this : Open http://localhost:8080/swagger-ui.html , click Authorize, and log in as admin.
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
