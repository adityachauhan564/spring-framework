# 03 - Spring Boot, REST and Persistence

Spring Boot takes everything you configured by hand in [02 - Spring Foundations](../02-spring-foundations/) and does it for you, based on what's on the classpath. This stage covers how that works, then REST API design, then databases with JPA, and finishes with a complete API. Next stage: [04 - Microservices](../04-microservices/), which relies on Boot's configuration, profiles and Actuator.

## Run it (nothing to install except JDK 21)
Every project uses in-memory H2 or no database at all, and runs on Boot's embedded Tomcat.
```bash
cd 03-spring-boot
./mvnw verify                                    # build and test all 4 projects (37 tests)
cd restful-web-services && ./mvnw spring-boot:run   # or run any single project from its folder
```
On Windows use `mvnw.cmd`. Each project listens on port 8080, so run one at a time.

| # | Project | Topics | Status |
| :- | :--- | :--- | :--- |
| 1 | [spring-boot-basics](./spring-boot-basics/) | 6 topics: auto-configuration, `@ConfigurationProperties`, profiles, runners and logging, Actuator, the executable jar | ✅ Working (9 tests) |
| 2 | [restful-web-services](./restful-web-services/) | 9 topics: endpoints, request data, CRUD and status codes, validation and ProblemDetail, filtering, versioning, OpenAPI, testing, security | ✅ Working (14 tests) |
| 3 | [jpa-hibernate](./jpa-hibernate/) | 4 topics: Spring JDBC → JPA `EntityManager` → Spring Data JPA → queries, paging and sorting | ✅ Working (6 tests) |
| 4 | [rest-first-books-api](./rest-first-books-api/) | Capstone: layers, DTOs, validation, errors, search, paging, H2/MySQL profiles, tests per layer | ✅ Working (8 tests) |

## Suggested study order
1. **spring-boot-basics**: how Boot configures itself. Everything after this builds on it.
2. **restful-web-services**: REST design, without a database getting in the way.
3. **jpa-hibernate**: the three persistence approaches, from most code to least.
4. **rest-first-books-api**: put it all together into one complete API.

## Spring Boot 4 notes
These projects use Spring Boot **4.0** (Spring Framework 7, Hibernate 7, Jackson 3). Older tutorials differ in a few places:
- **Starters were renamed or split:** `spring-boot-starter-webmvc` (was `-web`), `spring-boot-h2console`, and one test starter per technology (`spring-boot-starter-webmvc-test`, `-data-jpa-test`, `-security-test`...).
- **Test annotations moved packages:** for example `org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest` and `org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest`.
- **Jackson 3:** the code lives in `tools.jackson.*`, but the annotations (`@JsonIgnore`, `@JsonView`) are still in `com.fasterxml.jackson.annotation`.
- **API versioning is built in:** `@GetMapping(version = "2")` (restful-web-services topic06).
- **Security in `@WebMvcTest`** needs `spring-boot-starter-security-test`; `spring-security-test` alone is no longer enough.
- **springdoc:** the 3.x line works with Boot 4, while 2.x targets Boot 3.

## Quick revision checklist
- [ ] What auto-configuration is, how `@ConditionalOnMissingBean` lets your beans win, and how to see Boot's decisions
- [ ] `@ConfigurationProperties` vs `@Value`; what overrides `application.yml`
- [ ] Profiles: profile-specific files and `@Profile` beans
- [ ] Actuator health/info/metrics, and which endpoints must never be public
- [ ] REST status codes: 200, 201 + `Location`, 204, 400, 401, 403, 404, 409
- [ ] `@Valid` + `@RestControllerAdvice` + ProblemDetail for consistent errors
- [ ] DTOs vs returning entities; `@JsonIgnore` vs `@JsonView`
- [ ] API versioning options and their trade-offs
- [ ] Spring JDBC vs JPA vs Spring Data JPA; derived queries; paging and sorting
- [ ] `@WebMvcTest` vs `@DataJpaTest` vs `@SpringBootTest`, and what `@MockitoBean` does
- [ ] Authentication vs authorization; when disabling CSRF is acceptable
- [ ] How to keep DB passwords out of git (`${DB_PASSWORD}`)
