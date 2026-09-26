# 03 — Spring Boot, REST and Persistence

Spring Boot projects that use auto-configuration and an embedded server: REST controllers, HTTP status codes, and the first steps with databases (JDBC, then JPA). Each one runs on its own with `./mvnw spring-boot:run` from its folder.

| # | Project | Topics | Status |
|---|---------|--------|--------|
| 1 | [restful-web-services](./restful-web-services) | REST basics, path variables, 201 and `Location`, 404, OpenAPI | ✅ Working |
| 2 | [jpa-hibernate](./jpa-hibernate) | H2, `schema.sql`, `JdbcTemplate`, `CommandLineRunner` | 🚧 Partial (JDBC step only) |
| 3 | [rest-first-books-api](./rest-first-books-api) | Controller → Service → Repository, Spring Data JPA, MySQL | 🚧 Partial (GET and POST only) |

## Suggested study order
1. **restful-web-services**: learn REST and HTTP status codes without any database getting in the way.
2. **jpa-hibernate**: see how Spring Boot sets up a database (H2) and runs SQL through `JdbcTemplate`.
3. **rest-first-books-api**: put it together: a REST API on a real MySQL database through Spring Data JPA.

## Quick revision checklist
- [ ] What `@RestController` adds on top of `@Controller`
- [ ] When to return 200, 201 and 404, and how to build a `Location` header
- [ ] What Spring Data's `CrudRepository` gives you for free
- [ ] What `ddl-auto=update` does, and why it isn't used in production
- [ ] How Spring Boot auto-runs `schema.sql` and `data.sql` for H2
- [ ] Why `Optional.get()` is a trap in service code
- [ ] How to keep DB passwords out of git (`${DB_PASSWORD}`)
