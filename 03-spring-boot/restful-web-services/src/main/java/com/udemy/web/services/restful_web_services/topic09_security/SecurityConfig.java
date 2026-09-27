package com.udemy.web.services.restful_web_services.topic09_security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
 * Topic    : Securing the API with Spring Security
 * Key idea : adding the security starter locks EVERY endpoint by default. This class says
 *            who may do what:
 *              - reading (GET) and the API docs are public
 *              - changing data (POST/PUT/DELETE) needs the ADMIN role
 *            Authentication = WHO you are (HTTP Basic: user + password on every request)
 *            Authorization  = WHAT you may do (roles checked per URL/method)
 *              401 Unauthorized - no or wrong login;  403 Forbidden - logged in, but not allowed
 *   CSRF protection is off because this is a STATELESS API called by programs with credentials
 *   on each request, not a browser app with a session cookie (that one MUST keep CSRF on).
 * Try this : curl -i -X DELETE localhost:8080/users/3                       -> 401
 *            curl -i -u reader:reader123 -X DELETE localhost:8080/users/3   -> 403
 *            curl -i -u admin:admin123 -X DELETE localhost:8080/users/3     -> 204
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .anyRequest().hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    // Demo users kept in memory. Passwords come from application.properties, which reads the
    // ADMIN_PASSWORD / READER_PASSWORD environment variables - set them for anything real.
    @Bean
    public UserDetailsService users(PasswordEncoder encoder,
                                    @Value("${app.security.admin-password}") String adminPassword,
                                    @Value("${app.security.reader-password}") String readerPassword) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin").password(encoder.encode(adminPassword)).roles("ADMIN").build(),
                User.withUsername("reader").password(encoder.encode(readerPassword)).roles("USER").build());
    }

    // stores passwords as "{bcrypt}$2a$..." - the prefix says which algorithm was used
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
