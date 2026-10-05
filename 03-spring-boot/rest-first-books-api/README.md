# Rest First: Books API (stage 03 capstone)

> A complete REST API, shaped like a production one: layers, DTOs, validation, errors in one shape, search, paging and sorting, H2 by default with MySQL as an option, OpenAPI docs, and tests at every layer. It builds on everything else in stage 03.

**Before this:** [restful-web-services](../restful-web-services/) (REST design) and [jpa-hibernate](../jpa-hibernate/) (Spring Data JPA).

## Run it
No database to install. By default it uses in-memory H2, filled with 7 sample books from `data.sql`.
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test
```
The API docs are at http://localhost:8080/swagger-ui.html.

```bash
curl localhost:8080/books                                       # page 0, 5 books
curl "localhost:8080/books?page=1&size=3&sort=title,desc"       # paging + sorting
curl "localhost:8080/books?author=sierra"                        # search (case-insensitive)
curl -i localhost:8080/books/99                                  # 404
curl -i -X POST localhost:8080/books -H "Content-Type: application/json" \
     -d '{"title":"Refactoring","author":"Martin Fowler"}'        # 201 + Location
curl -X PUT localhost:8080/books/8 -H "Content-Type: application/json" -d '{"title":"Refactoring 2e","author":"Martin Fowler"}'
curl -i -X DELETE localhost:8080/books/8                         # 204
```

**MySQL (optional):**
```bash
export DB_PASSWORD=your-password                       # DB_USERNAME defaults to root
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```
The database `youtube_springboot_api` is created if it is missing. Hibernate keeps the tables up to date, and the sample data is not loaded.

## The layers (read in this order)

| Layer | Package | Its one job |
| :- | :--- | :--- |
| Entity | `entities.Book` | One table row; `IDENTITY` ids |
| Repository | `dao.BookRepository` | Database access; Spring Data writes it for you |
| DTOs | `dto.BookRequest`, `dto.BookResponse` | What the API receives and what it sends back |
| Service | `services.BookService` | Business logic and transactions; converts between entities and DTOs |
| Controller | `controllers.BookController` | HTTP only: parameters, status codes, `Location` |
| Errors | `exceptions.*` | Every error as ProblemDetail JSON |

Like a restaurant: the waiter (controller) takes the order, the kitchen (service) cooks, and the store room (repository) holds the stock. The waiter never walks into the store room.

## Why it's built this way
- **DTOs instead of the entity:**
  - The request has no `id`, so a client can't choose or overwrite ids (an `id` that is sent is ignored).
  - The database can change without breaking clients.
- **`IDENTITY` ids:** the old `GenerationType.AUTO` made Hibernate create a hidden sequence table on MySQL.
- **No custom `findById(int)`:** it used to hide Spring Data's own `findById`, which returns `Optional`. The service uses `orElseThrow`, so a missing book becomes a 404 instead of a `NullPointerException`.
- **`PagedModel`:** gives a JSON shape that doesn't change between versions: `{"content":[...], "page":{"size","number","totalElements","totalPages"}}`. Returning Spring's `Page` directly is not a stable format.
- **H2 by default:** the old default was MySQL, so `./mvnw test` failed on any machine without the same MySQL setup.

## Tests (one per layer)
- `BookControllerTest`: `@WebMvcTest` with a **`@MockitoBean`** service (a fake service the test controls). It covers HTTP behaviour only, and checks that bad input never reaches the service.
- `BookRepositoryTest`: `@DataJpaTest` covering the sample data, the case-insensitive search and paging.
- `RestFirstApplicationTests`: `@SpringBootTest` running full CRUD from start to end, plus paging.

## Exercises
1. Add a `publishedYear` field. Update the entity, both DTOs and the validation (`@Min(1450)`), then make the list sortable by it.
2. Add `GET /books/search?title=...` using a derived query.
3. Return **409 Conflict** when someone creates a book whose title and author already exist.

## Revision checklist
- [ ] Each layer's one job, and why the controller never sees the entity.
- [ ] How `?page=&size=&sort=` becomes a `Pageable`.
- [ ] Why `IDENTITY`, and why not a custom `findById`.
- [ ] How the `mysql` profile switches the database without changing code.
- [ ] Which test type checks which layer, and what `@MockitoBean` replaces.

## Status
✅ Working: full CRUD, search and paging were checked over HTTP on H2 (with `DB_PASSWORD` unset), and 8 tests pass. The `mysql` profile was checked against MySQL 8.4 in Docker: the database is created, no sample books are inserted, and a book you create is still there after a restart.
