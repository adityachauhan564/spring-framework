# 05 — Real-World Backend Applications

These are larger, end-to-end backend projects that combine what the earlier sections teach: REST design, JPA relationships, validation, exception handling, caching (Redis), messaging (Kafka), email and security. Each one is a separate build (Maven or Gradle); run it from its own folder.

| # | Project | Topics | Status |
| --- | --- | --- | --- |
| 1 | [digital-library](./digital-library) | Spring Boot 3, JPA 1..* / *..*, validation, `@ControllerAdvice`, Redis cache + `RedisTemplate`, Mockito tests | ✅ builds, unit tests pass; needs MySQL + Redis |
| 2 | [showtime](./showtime) | Booking domain, DTO mapping, Kafka producer/consumer, email, Spring Security filter chain, springfox | ✅ builds; Boot 2.7 (EOL); security not secure |
| 3 | [movieshark](./movieshark) | Near-copy of showtime (different `User` login field, no Swagger) | ✅ builds; duplicate |
| 4 | [irctc-ticket-booking](./irctc-ticket-booking) | Plain Java + Gradle, Jackson over JSON files | 🚧 work in progress |

Common prerequisites: JDK 17+ (21 recommended), MySQL on `localhost:3306`, and `DB_PASSWORD` set in your environment (`DB_USERNAME` defaults to `root`). showtime and movieshark also need Kafka (`showtime/docker-compose.yml`). digital-library also needs Redis.

## Suggested study order
1. **digital-library**: the cleanest layered REST + JPA example. Learn the exception handling and caching here.
2. **showtime**: the same layering on a richer domain, plus async notifications with Kafka and Spring Security.
3. **movieshark**: diff it against showtime as a review exercise (why should the login field be unique?).
4. **irctc-ticket-booking**: rebuild the ideas without Spring, to see what the framework was doing for you.

## Quick revision checklist
- [ ] Can I explain `@OneToMany(mappedBy=...)` vs `@ManyToOne` vs `@ManyToMany` + `@JoinTable`, and which side owns the relationship?
- [ ] Do I know why an `@ExceptionHandler` parameter must match its exception type, and how a custom exception becomes a 404?
- [ ] Can I explain `@Cacheable` / `@CachePut` / `@CacheEvict`, including why their keys must match?
- [ ] Can I trace a booking in showtime from `POST /ticket/book` through Kafka to the email?
- [ ] Why must `loadUserByUsername` throw instead of returning `null`? Why is `NoOpPasswordEncoder` unsafe?
- [ ] Why use `Optional.orElseThrow` instead of `.get()`?
- [ ] Why do credentials go in environment variables (`${DB_PASSWORD}`) rather than in `application.properties`?
