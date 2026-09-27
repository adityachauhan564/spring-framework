# RESTful Web Services

> Designing a REST API with Spring Boot, one concern at a time: endpoints and JSON, request data, CRUD with correct status codes, validation and errors, filtering, versioning, docs, tests and security. Based on the in28minutes Spring Boot course; the course's own code is in [`reference/`](../../reference/in28minutes-spring-microservices-v3/02.restful-web-services) for comparison.

**Before this:** [spring-boot-basics](../spring-boot-basics/).

## Run it
No database; the users live in a thread-safe in-memory store.
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test
```
It runs on port 8080. Swagger UI is at http://localhost:8080/swagger-ui.html; click **Authorize** and log in as `admin` / `admin123`.

**Logins (topic09):** reading is public. Changing data needs `admin` / `admin123`, while `reader` / `reader123` is logged in but not allowed to change anything. Set `ADMIN_PASSWORD` / `READER_PASSWORD` for anything that isn't a local demo.

```bash
curl localhost:8080/users
curl -i localhost:8080/users/99                                               # 404 ProblemDetail
curl -i -u admin:admin123 -X POST localhost:8080/users -H "Content-Type: application/json" \
     -d '{"name":"Asha","birthDate":"2000-01-01"}'                             # 201 + Location
curl -i -u admin:admin123 -X DELETE localhost:8080/users/3                    # 204
curl -H "X-API-Version: 2" localhost:8080/person                              # version 2
```

## Topics

| # | Package | Try | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_hello_world` | `/hello-world`, `/hello-world-bean` | `@RestController`; returning text vs JSON |
| 02 | `topic02_request_data` | `/hello-world/path-variable/Adi`, `/greet?name=Adi&times=2` | `@PathVariable`, `@RequestParam`, `@RequestHeader` |
| 03 | `topic03_crud_resource` | `/users` | GET/POST/PUT/DELETE with 200, 201 + `Location`, 204 and 404; a thread-safe store |
| 04 | `topic04_validation_and_errors` | POST an invalid user | `@Valid`, `@RestControllerAdvice`, ProblemDetail error bodies |
| 05 | `topic05_filtering_and_dtos` | `/accounts/1`, `/public`, `/internal`, `/summary` | `@JsonIgnore`, `@JsonView`, DTOs |
| 06 | `topic06_versioning` | `/person` with and without `X-API-Version`, `/v1/person` | Spring Framework 7 API versioning vs URI versioning |
| 07 | `topic07_openapi` | `/swagger-ui.html`, `/v3/api-docs` | Generated API docs with springdoc |
| 08 | tests in `src/test` | `./mvnw test` | `@WebMvcTest` slices, MockMvc, `jsonPath`, `@SpringBootTest` |
| 09 | `topic09_security` | the curl commands above | HTTP Basic, roles, 401 vs 403, CSRF for stateless APIs |

## Topic notes

### 01-02 Endpoints and request data
- **How:** a `@RestController` method returns data. A `String` goes out as text, and an object or record is converted to JSON by Jackson.
- **Where request data lives:**
  - the **path** identifies *which* resource (`@PathVariable`);
  - the **query string** carries options (`@RequestParam`, with `defaultValue`);
  - **headers** carry metadata (`@RequestHeader`);
  - the **body** carries data for POST/PUT (`@RequestBody`).

### 03 CRUD resource
- **Why:** clients rely on status codes, so they must be correct.
- **How:**
  - POST returns **201** with a `Location` header, built from the current request so the host is never hard-coded.
  - DELETE returns **204**.
  - A missing id is **404**: the controller throws `UserNotFoundException`, and topic04 turns it into the response.
- **Mistake (fixed):** the store used to be a static `ArrayList` with a static counter. Requests run on many threads at once, so ids could repeat. It now uses `ConcurrentHashMap` and `AtomicInteger`.

### 04 Validation and errors
- **How:**
  - `@Valid @RequestBody User` checks `@Size`, `@Past` and `@NotNull` before the method runs.
  - One `@RestControllerAdvice` (extending `ResponseEntityExceptionHandler`) returns every error in the same **ProblemDetail** JSON shape (RFC 9457), including Spring's own errors such as bad JSON.
- **Exercise:** add `@Email` to a new field and test it.

### 05 Filtering and DTOs
- **How:**
  - `@JsonIgnore` never sends a field, which is right for secrets.
  - `@JsonView` gives different field sets per endpoint.
  - A **DTO** record is a separate response type, and the clearest choice for real APIs.
- **Jackson 3 note:** Boot 4 moved Jackson's code to `tools.jackson.*`, but the annotations stayed in `com.fasterxml.jackson.annotation`.

### 06 Versioning
- **Why:** changing a JSON shape breaks existing clients, and a new version lets both kinds of client work.
- **How:**
  - Spring Framework 7 has built-in versioning. `configureApiVersioning` says where the version travels (a header here), and `@GetMapping(version = "2")` picks the handler.
  - With no header, you get the default version. An unknown version returns a 400.
  - `/v1/...` URI versioning is shown alongside for comparison. Pick one style per API.

### 07 OpenAPI
- **How:** springdoc generates `/v3/api-docs` and Swagger UI from the code, so the docs can't drift from the code. `OpenApiConfig` adds the title and the HTTP Basic "Authorize" button.

### 08 Testing
- **How:**
  - `@WebMvcTest(UserResource.class)` loads only the web layer, which is fast; you `@Import` anything else it needs. MockMvc and `jsonPath` check the status and the JSON.
  - `@SpringBootTest` starts the whole app. One or two of those are enough.
- **Gotchas:**
  - The tests share one context, so they shared the in-memory store until `@DirtiesContext` gave each test a fresh one. Tests must never depend on their order.
  - In Boot 4, security in `@WebMvcTest` needs `spring-boot-starter-security-test`.

### 09 Security
- **How:**
  - Adding the security starter locks **everything**; `SecurityConfig` then opens GET requests and the docs, and requires the `ADMIN` role for changes.
  - A missing or wrong login is **401**. Being logged in without permission is **403**.
  - Passwords are stored hashed (`{bcrypt}`).
  - CSRF protection is off only because this is a stateless API with credentials on every request. A browser app that uses session cookies must keep CSRF protection on.

## Revision checklist
- [ ] Which status code for create, delete, not found, invalid input, not logged in, and not allowed.
- [ ] Why POST returns a `Location` header, and how to build it.
- [ ] Path vs query vs header vs body: what goes where.
- [ ] What ProblemDetail is, and how one `@RestControllerAdvice` gives consistent errors.
- [ ] `@JsonIgnore` vs `@JsonView` vs DTO.
- [ ] Header vs URI versioning, and what happens with no version.
- [ ] `@WebMvcTest` vs `@SpringBootTest`; why tests must not share mutable state.
- [ ] Authentication vs authorization; when CSRF can be disabled.

## Status
✅ Working: every endpoint was checked over HTTP from the packaged jar, and 14 tests pass. New in this pass: PUT and DELETE, validation, ProblemDetail errors, filtering, versioning, security and real tests; the unused JPA and H2 dependencies were removed.
