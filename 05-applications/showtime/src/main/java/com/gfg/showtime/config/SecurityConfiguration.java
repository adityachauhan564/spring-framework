package com.gfg.showtime.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gfg.showtime.enums.Role;

/*
 * Spring Security 5.7+ style: beans, instead of extending WebSecurityConfigurerAdapter (removed in 6).
 *   PasswordEncoder     - BCrypt: a slow hash with a random salt. The course used NoOpPasswordEncoder (plain text!).
 *   UserDetailsService  - UserAuthService, which loads a user by email
 *   SecurityFilterChain - who is allowed to call what (below)
 * Login is HTTP Basic: every request sends "Authorization: Basic base64(email:password)".
 *   curl -u asha@example.com:password123 ...
 *
 * 401 = "who are you?" (no login, or a wrong one); 403 = "I know you, but you are not allowed to do this".
 * Like a cinema: no ticket = not let in (401); a normal ticket for the recliner row = sent back (403).
 */
@Configuration
public class SecurityConfiguration {

    private static final String ADMIN = Role.ADMIN.name();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF protects browser sessions that send a cookie automatically. This API has no session
            // and no cookie: each request carries its own login, so there is nothing for an attacker to fake.
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(withDefaults())
            .authorizeHttpRequests(auth -> auth
                // rules are checked from top to bottom: the first one that matches wins
                .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                .requestMatchers(HttpMethod.GET, "/movie/**", "/show/search", "/theater/*", "/review/find").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/movie/add", "/theater/add", "/show/add").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.GET, "/user/me").authenticated()
                .requestMatchers(HttpMethod.GET, "/user/*").hasAuthority(ADMIN)
                .anyRequest().authenticated());            // booking, reviews, tickets: any user who is logged in
        return http.build();
    }
}
