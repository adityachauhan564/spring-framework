# Product Service (backend for the Angular app)

> A Spring Boot 4 REST API with CRUD and search for products, made to be called from a browser app: plain JSON names, validation errors that a form can show, and **CORS** for the Angular dev server.

**Before this:** [03-spring-boot](../../03-spring-boot) (REST, validation, JPA). This project is the other half of [`../product-inventory-frontend`](../product-inventory-frontend).

## Why it matters
An API that works in Postman can still fail from a browser. The browser blocks cross-origin calls (calls to a different scheme, host or port) unless the server allows them (CORS). The browser app also needs JSON keys that match the frontend's model, and error bodies it can show. This project is the same CRUD you already know, shaped for a frontend.

## What it teaches
- REST paths: one resource URL (`/api/products`), where the HTTP method says what happens
- Controller → Service → `JpaRepository`, with a derived search query
- Bean Validation plus `ProblemDetail` errors, with one message per field
- CORS with a `WebMvcConfigurer`, and what a preflight request is
- How Jackson turns getter names into JSON keys

## Run it (nothing to install except JDK 21)
```bash
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
./mvnw test                   # 6 tests, including the CORS preflight
```
The API runs on `http://localhost:8080` with 4 sample products (H2, fresh on every start).

| Method | Path | Result |
| --- | --- | --- |
| GET | `/api/products?search=pen` | all products sorted by name, optionally filtered (capital letters ignored) |
| GET | `/api/products/{id}` | one product, or 404 |
| POST | `/api/products` | create: 201, or 400 with field errors |
| PUT | `/api/products/{id}` | replace: 200, 400 or 404 |
| DELETE | `/api/products/{id}` | 204, or 404 |

```bash
curl localhost:8080/api/products
curl -X POST localhost:8080/api/products -H "Content-Type: application/json" -d '{"name":"","price":-1}'
# 400 {"detail":"Validation failed","errors":{"name":"Name is required","price":"Price can't be negative",...}}

# the preflight a browser sends before a cross-origin PUT: note the Access-Control-Allow-Origin header
curl -i -X OPTIONS localhost:8080/api/products/1 -H "Origin: http://localhost:4200" -H "Access-Control-Request-Method: PUT"
```
For MySQL: `export DB_PASSWORD=...`, then `./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql`. The database `spring_angular_bootcamp` is created if it is missing.

## Read the code in this order
1. `src/main/resources/application.properties` and `data.sql`
2. `src/main/java/com/bootcamp/productservice/model/Product.java`: the entity and its validation
3. `.../repository/ProductRepository.java`: the derived search query
4. `.../service/ProductService.java`
5. `.../controller/ProductController.java` and `ApiExceptionHandler.java`
6. `.../config/CorsConfig.java`
7. `src/test/java/.../ProductApiTest.java`

## Revision notes
- **CORS is enforced by the browser only.** A page and an API on different ports are different *origins* (an origin = scheme + host + port). The browser sends the request (or first an `OPTIONS` preflight, for PUT/DELETE and JSON bodies). The request still reaches the server, but the browser only lets the page read the answer if the server replies with `Access-Control-Allow-Origin: <that origin>`. curl and Postman ignore all of this, so "it works in Postman" proves nothing about the browser.
- **Allowed origins:** list them exactly (`http://localhost:4200`). `*` would let any website's scripts call your API from a visitor's browser.
- **Jackson naming:** JSON keys come from getter names, and `getPName()` becomes `"pname"`. The course's `pName` / `pPrice` fields never matched the Angular model. Plain `name` / `price` / `quantity` avoid the problem.
- **Status codes:** POST → 201, PUT → 200, DELETE → 204 (nothing to return), missing → 404, invalid → 400. The course returned 200 plus a text message for delete.
- **Paths:** use `POST /api/products`, not `/save`, because the method already says what happens. `/api` also keeps the API apart from pages, and CORS applies only to `/api/**`.
- **404s:** `ResponseStatusException(NOT_FOUND, ...)` is the quickest correct way to send a 404. Extending `ResponseEntityExceptionHandler` gives every error the same `ProblemDetail` shape.
- **Tests that change data:** `@DirtiesContext(AFTER_EACH_TEST_METHOD)` gives every test a fresh app and database. It is simple but slower, and fine for 6 tests.

## Status
✅ **Working.** 6 tests pass. Checked from a real (headless, with no window) browser through the Angular app: the list loaded across origins. The `mysql` profile was checked against MySQL 8.4 in Docker: CRUD, search and CORS work, and the data is still there after a restart.

Changes from the course version:
- H2 by default, so MySQL is no longer required;
- REST paths, and JSON names fixed;
- validation, `ProblemDetail` errors, delete returns 204;
- CORS added;
- the `dao` package renamed to `repository`;
- constructor injection.
