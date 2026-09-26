# Digital Library

> A Spring Boot REST backend for authors, books and library users, with Redis caching. Built during the GeeksforGeeks JBDL (Java Backend Development) batch 63; the package is `com.jbdl63`.

## What it teaches
- Layered REST design: Controller → Service → Spring Data JPA Repository
- JPA relationships: Author 1..* Book (`@OneToMany`/`@ManyToOne`) and User *..* Book (`@ManyToMany` + `@JoinTable`)
- Bean Validation (`@Valid`) and centralised errors with `@ControllerAdvice`
- Redis in two roles: a Spring Cache backend (`@Cacheable`/`@CachePut`/`@CacheEvict`) and direct `RedisTemplate` data structures (string, list, set, hash)
- Unit testing a service with Mockito (`@InjectMocks`, `@Mock`)

## Run it
Prerequisites: JDK 17+, MySQL on `localhost:3306`, Redis on `localhost:6379`.

1. Edit `src/main/resources/application.properties`. The `spring.datasource.username` and `spring.datasource.password` values are placeholders (`username` / `Password`). Replace them with your own, or change them to `${DB_USERNAME:root}` / `${DB_PASSWORD}` and set those environment variables.
2. Create the database: `CREATE DATABASE digital_library;` Tables are created automatically because `ddl-auto=update`.
3. Start the app:
   ```bash
   ./gradlew bootRun            # Windows: gradlew.bat bootRun
   ```
4. The base URL is `http://localhost:8080/digitalLibrary/api` (set by `server.servlet.context-path`).

| Endpoint | Purpose |
| --- | --- |
| `POST /v1/authors`, `GET /v1/authors`, `GET /v1/authors/{authorName}`, `GET /v1/authors/usingParam?authorName=` | Create, list and fetch authors (the fetch by name is cached) |
| `PUT /v1/authors` (body `UpdateAuthorDto`), `DELETE /v1/authors/{authorId}` | Update an author's address (refreshes the cache); delete an author (clears the cache) |
| `POST /v1/authors/upload-csv` (multipart `file`) | Bulk-insert authors from a CSV with header `authorId,authorName,authorAddress` |
| `POST /v1/books`, `DELETE /v1/books/{bookId}`, `GET /v1/books/{authorName}` | Books; the POST body must contain `author.authorId` |
| `POST /v1/users`, `GET /v1/users/{userName}` | Users |
| `/v1/redis/...` | Playground endpoints for Redis strings, lists, sets and hashes |

Tests:
```bash
./gradlew test --tests '*AuthorServiceTest'   # 6 Mockito unit tests, no DB needed
./gradlew test                                # also runs DigitalLibraryApplicationTests, which needs MySQL + Redis
```

## Read the code in this order
1. `Requirements`: the original brief (tables, relationships, required APIs).
2. `src/main/java/com/jbdl63/digitalLibrary/Model/`: `Author`, `Book` and `User` entities and their relationships.
3. `.../Repository/`: derived query methods such as `findByAuthorName` and `findByAuthorAuthorName`.
4. `.../Service/AuthorService.java` and `BookService.java`: business rules, not-found handling, CSV parsing.
5. `.../Controller/AuthorController.java`: validation and cache annotations.
6. `.../Exceptions/GlobalExceptionHandler.java`: how exceptions become HTTP 400 or 404 responses.
7. `.../Configuration/RedisConfiguration.java` and `Service/RedisService.java`: how the Redis template and serializers are set up.
8. `src/test/java/.../AuthorServiceTest.java`: mocking the repository.

## Revision notes
- An `@ExceptionHandler` method's parameter type must match the exception it handles. Otherwise Spring can't bind it and you get a 500.
- Don't let a broad `catch (RuntimeException)` swallow your own `DataNotFoundException`. Re-throw it, or a 404 turns into a 400.
- `@CachePut` must use the **same key** as `@Cacheable`. Here that is `#result.authorName`; a key made from a missing parameter is `null`.
- `@EnableCaching` on the application class is what turns the cache annotations on.
- Use `Optional.orElseThrow(...)` instead of `.get()`. `.get()` on an empty Optional produces a 500.
- When parsing uploaded text, split on `\r?\n` so Windows line endings work, trim fields, and check the column count.
- `FetchType.EAGER` on both sides of a relationship is simple but loads a lot of data. Prefer LAZY once you understand the trade-off.
- Style note: the packages are Capitalised (`Controller`, `Service`, ...), which is non-standard Java. Lowercase is the convention. They were deliberately **not** renamed: `RedisConfiguration` uses `JdkSerializationRedisSerializer`, which stores full class names (e.g. `com.jbdl63.digitalLibrary.Model.Author`) inside Redis values, so a rename would make already-stored data unreadable. Rename only together with flushing Redis or switching to a JSON serializer.

## Status
✅ Compiles; the unit tests pass.
🚧 Known issues:
- The datasource credentials in `application.properties` are placeholders.
- Book update and the "fetch by category" / "books issued to user" APIs from `Requirements` are not implemented.
- The Redis host and port are hardcoded in `RedisConfiguration`.
