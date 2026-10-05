package com.bootcamp.productservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * CORS: the Angular app is served from http://localhost:4200 and calls http://localhost:8080.
 * A different port = a different ORIGIN, and browsers block such calls unless the server answers
 * with "Access-Control-Allow-Origin: http://localhost:4200".
 * Like your own building's guard (the browser) who checks the other society's guest list
 * (the backend's allowed origins) before handing you their reply. The request itself still reaches the server.
 * For PUT/DELETE and JSON bodies the browser first sends an OPTIONS "preflight" request to ask
 * permission. Spring answers it from this configuration.
 * CORS is checked by the BROWSER only: curl and Postman ignore it - that is why the API "worked" before.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

	private final String[] allowedOrigins;

	public CorsConfig(@Value("${app.cors.allowed-origins}") String[] allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins(allowedOrigins)             // never "*" (everyone) for an API that changes data
				.allowedMethods("GET", "POST", "PUT", "DELETE");
	}
}
