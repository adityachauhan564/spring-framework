# Product Service (Backend for the Angular bootcamp)

> A Spring Boot 4 REST API with CRUD for products, stored in MySQL through Spring Data JPA. It is meant to serve the [`../product-inventory-frontend`](../product-inventory-frontend) Angular app.

## What it teaches
- A minimal Controller → Service → `CrudRepository` stack
- Derived query methods that return `Optional` (`findBypName`)
- Partial update: load the entity, copy only the non-null fields, save
- Returning the right HTTP status with `ResponseEntity` and `ResponseStatusException` (201 on create, 200 on update, 404 when missing)

## Run it
Prerequisites: JDK 21, MySQL on `localhost:3306` with a database named `spring_angular_bootcamp` (`CREATE DATABASE spring_angular_bootcamp;`).
```bash
export DB_PASSWORD=...        # PowerShell: $env:DB_PASSWORD="..."   (DB_USERNAME defaults to root)
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```
The API runs on `http://localhost:8080`, and the table is created automatically (`ddl-auto=update`).

| Method | Path | Result |
| --- | --- | --- |
| GET | `/products` | all products |
| GET | `/product/{pName}` | one product by name, 404 if missing |
| POST | `/save` | create; returns 201 |
| PUT | `/update/{pId}` | partial update; returns 200, or 404 if missing |
| DELETE | `/delete/{pId}` | delete; returns 200, or 404 if missing |

## Read the code in this order
1. `src/main/java/com/bootcamp/productservice/model/Product.java`: the entity.
2. `.../dao/ProductRepository.java`: `CrudRepository` plus a derived query.
3. `.../service/ProductService.java`: business logic and 404 handling.
4. `.../controller/ProductController.java`: routes and status codes.

## Revision notes
- `PUT` updates an existing resource, so return **200 OK**. **201 Created** is for `POST` creating something new.
- `ResponseStatusException(HttpStatus.NOT_FOUND, msg)` is the quickest correct 404. A bare `RuntimeException` becomes a 500.
- **Jackson naming gotcha:** getters like `getPName()` serialise as `pname`, not `pName`. Check the real JSON before writing the Angular model, or rename the fields to `name`, `price`, `quantity`.
- `findBypName` works because Spring Data parses the method name. Keep the property casing exactly as it is on the entity.
- Before the Angular app can call this API from `localhost:4200`, add a CORS config (`@CrossOrigin` or a `WebMvcConfigurer`).
- More RESTful paths would be `POST /products`, `PUT /products/{id}` and `DELETE /products/{id}`. The current ones are verb-style.

## Status
✅ Compiles; the endpoints return correct status codes.
🚧 Known issues:
- No CORS configuration yet.
- The paths are verb-style and not consistently plural.
- The `ProductserviceAngularApplicationTests` context test needs MySQL.
