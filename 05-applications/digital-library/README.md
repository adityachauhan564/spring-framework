# Digital Library

> A Spring Boot REST backend for authors, books and library members: JPA relationships, validation, error handling in one place, issuing and returning books, and caching (in memory by default, Redis if you want). Built during the GeeksforGeeks JBDL batch 63 (package `com.jbdl63`).

**Before this:** [03-spring-boot](../../03-spring-boot) (REST, validation, Spring Data JPA). This is the first "real" application: the same pieces, working together on a small domain (one business area).

## Why it matters
Stage 03 covered each idea on its own. A real backend combines them: three related tables, rules that cross tables ("you can't delete a book someone has borrowed"), errors that must become the right status code, and reads that are fast enough because they are cached (kept ready in memory).

## What it teaches
- JPA relationships: Author 1..* Book (`@OneToMany` / `@ManyToOne`) and User *..* Book (`@ManyToMany` + a join table)
- Which side **owns** a relationship, and why that decides where the foreign key is kept
- Bean Validation (`@Valid`) and one `@RestControllerAdvice` for 400 / 404 / 409
- Spring's cache abstraction: `@Cacheable` / `@CachePut` / `@CacheEvict`, the same code with an in-memory map or Redis
- Redis data structures used directly with `RedisTemplate` (string, list, set, hash)
- Testing at three levels: Mockito unit tests, `@DataJpaTest`, and the full API with MockMvc

## Run it (nothing to install except JDK 21)
```bash
./gradlew bootRun          # Windows: gradlew.bat bootRun
./gradlew test             # 17 tests, no database or Redis needed
```
It starts on H2 with sample data: 2 authors, 3 books, 1 user. Base URL: `http://localhost:8080/digitalLibrary/api`.

```bash
B=localhost:8080/digitalLibrary/api/v1
curl $B/authors
curl "$B/authors/R.K.%20Narayan"                     # a cached read: the second call doesn't query the database
curl "$B/books?category=fantasy"
curl -X POST $B/users/1/books/2                      # issue book 2 to user 1
curl $B/users/1/books
curl -X DELETE $B/books/2                            # 409: the book is issued
curl -X DELETE $B/users/1/books/2                    # return it
curl -X POST $B/authors -H "Content-Type: application/json" -d '{"authorName":""}'   # 400 {"authorName":"..."}
```

| Endpoint | Purpose |
| --- | --- |
| `POST /v1/authors`, `GET /v1/authors`, `GET /v1/authors/{name}`, `GET /v1/authors/usingParam?authorName=` | Create, list, fetch by name (cached) |
| `PUT /v1/authors` (body `{"authorId":1,"address":"..."}`), `DELETE /v1/authors/{id}` | Change the address (refreshes the cache); delete, together with their books |
| `POST /v1/authors/upload-csv` (multipart `file`) | Add many at once; a header line, then `authorName,authorAddress` |
| `POST /v1/books`, `GET /v1/books/{id}`, `PUT /v1/books/{id}`, `DELETE /v1/books/{id}` | Books; the body needs `"author":{"authorId":1}` |
| `GET /v1/books?author=...` / `?category=...` | Filters |
| `POST /v1/users`, `GET/PUT/DELETE /v1/users/{id}` | Members (409 when deleting one who still has books) |
| `GET /v1/users/{id}/books`, `POST` / `DELETE /v1/users/{id}/books/{bookId}` | Issued books; issue (409 if already issued); return (404 if not issued) |
| `/v1/redis/...` | Redis playground, `redis` profile only |

### Optional: MySQL and Redis
```bash
# MySQL (the database is created if missing)
export DB_PASSWORD=...                 # PowerShell: $env:DB_PASSWORD="..."; DB_USERNAME defaults to root
./gradlew bootRun --args='--spring.profiles.active=mysql'

# Redis as the cache, plus the /v1/redis playground
docker compose up -d                   # Redis on localhost:6379
./gradlew bootRun --args='--spring.profiles.active=redis'
docker exec -it digital-library-redis redis-cli    # then: KEYS *   GET "authors::R.K. Narayan"   TTL "authors::R.K. Narayan"
docker compose down
```
Profiles can be combined: `--spring.profiles.active=mysql,redis`.

## Read the code in this order
1. `Requirements`: the original brief
2. `src/main/resources/application.properties`, then `application-mysql.properties` / `application-redis.properties`, and `data.sql`
3. `model/Author.java`, `Book.java`, `User.java`: the entities and their relationships
4. `repository/`: derived queries such as `findByAuthorAuthorName`
5. `service/AuthorService.java`: the cache annotations; `BookService.java`; `UserService.java`: issue and return
6. `controller/`: thin controllers, `@Valid`, status codes
7. `exception/GlobalExceptionHandler.java`: every error becomes a status code
8. `configuration/CachingConfiguration.java`, `RedisConfiguration.java`, then `service/RedisService.java`
9. `src/test/java/...`: `AuthorServiceTest` (Mockito) → `RepositoryTest` (`@DataJpaTest`) → `LibraryApiTest` (MockMvc) → `CachingTest`

## Revision notes
- **Owning side:** the side *without* `mappedBy` owns the relationship and holds the foreign key (`Book.author` → `author_id`). For a many-to-many, the owner (`User.issuedBooks`) controls the join table: adding to its list inserts a row in `books_issued`. Changing only the `mappedBy` side saves nothing.
- **Infinite loops:** Author → books → author → ... goes round forever and breaks both JSON and Lombok's `toString`. Hide one side from JSON (`@JsonIgnore`), and leave relations out of `@ToString`.
- `@Builder` ignores field starting values (`= new ArrayList<>()`) unless the field has `@Builder.Default`.
- **Lazy loading:** a `@OneToMany` / `@ManyToMany` list is loaded only when it is first read, and that needs an open transaction. `UserService.findAllBooksIssuedToUser` is `@Transactional(readOnly = true)` and returns a copy. `spring.jpa.open-in-view=false` makes this visible, instead of keeping a database connection open for the whole HTTP request.
- **Dirty checking:** inside `@Transactional`, changing a loaded entity is enough. It is saved at commit, with no `save()` call.
- **409 from the database:** a duplicate unique name, or deleting a row that another table still points to, throws `DataIntegrityViolationException`. The handler turns it into 409 instead of a 500.
- **Cache keys:** `@CachePut` must write the same key that `@Cacheable` reads (here, the author's name). Otherwise it just adds a second copy. When you can't work out the key (delete by id), `allEntries = true` is the safe choice.
- **Cache location:** put the cache annotations on the service. They work through a Spring proxy, so a call from inside the same class skips the cache.
- **Cache types:** `spring.cache.type=simple` (a `ConcurrentHashMap`) vs `redis`. The code doesn't change, only a property. Redis survives restarts and is shared by several instances. The in-memory cache does neither. Like a shop's notebook (in memory) vs a shared register at the head office (Redis).
- **Redis serialization:** Boot's default for the Redis cache is JDK serialization: binary data that includes the class name, so renaming a package breaks stored values. Here Author is stored as plain JSON. It is readable in `redis-cli` and has no class names. That is why the packages could be made lowercase in this version.
- **`@EnableCaching` placement:** it sits on its own `@Configuration` class, not on the application class. Otherwise test slices like `@DataJpaTest` fail with "No CacheManager".
- **Why ids are ignored on create:** a new entity with an id set is treated as an update of a row that doesn't exist. Hibernate 6.6+ throws an error for that, so the services clear the id.
- **Boot 4 upgrade gotchas:** Jackson 3 writes JSON properties in alphabetical order by default. Redis JSON serializers are now `JacksonJsonRedisSerializer` / `GenericJacksonJsonRedisSerializer` (Jackson 3). `RedisCacheManagerBuilderCustomizer` moved to `org.springframework.boot.cache.autoconfigure`.
- The CSV upload splits on `\r?\n`, so Windows line endings don't leave a `\r` in the last column.

## Status
✅ **Working.** 17 tests pass. Checked over HTTP on H2, and with the `redis` profile against Redis 7 in Docker (JSON values, 10-minute TTL (how long an entry lives), `@CachePut` refresh). The `mysql` profile was checked against MySQL 8.4 in Docker: the tables and the `books_issued` join table are created, the unique-name and foreign-key conflicts return 409, and issued books are still there after a restart.

Changes from the course version:
- Boot 3.2 → 4.0 and Gradle 8.5 → 9.1
- H2 by default, instead of placeholder MySQL credentials
- lowercase packages
- `User.getIssuedBooks()` is no longer a stub that returned `null`
- issue/return, update-book and by-category APIs added
- constructor injection
- Boot's auto-configured Redis client instead of a hard-coded Jedis bean
