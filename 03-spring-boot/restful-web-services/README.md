# RESTful Web Services

> A Spring Boot REST API over an in-memory user list, with OpenAPI docs. From the in28minutes Spring Boot course.

## What it teaches
- `@GetMapping`, `@PostMapping` and `@PathVariable`
- Returning a Java bean and letting Jackson convert it to JSON (`HelloWorldBean`)
- Correct HTTP semantics: **404** for a missing resource (`ResponseStatusException`), and **201 Created** plus a `Location` header on POST (`ServletUriComponentsBuilder`)
- Constructor injection (`UserResource(UserDaoService service)`)
- Auto-generated API docs with springdoc OpenAPI 3

## Run it
No database: the data lives in a static list.
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```
The app runs on port 8080.

```bash
curl localhost:8080/hello-world
curl localhost:8080/hello-world-bean
curl localhost:8080/hello-world/path-variable/Adi
curl localhost:8080/users
curl -i localhost:8080/users/1        # 200
curl -i localhost:8080/users/99       # 404
curl -i -X POST localhost:8080/users -H "Content-Type: application/json" \
     -d '{"name":"Test","birthDate":"2000-01-01"}'   # 201 + Location: /users/4
```
API docs are at http://localhost:8080/swagger-ui.html and http://localhost:8080/v3/api-docs.

## Read the code in this order
1. `src/main/java/com/udemy/web/services/restful_web_services/helloworld/HelloWorldController.java`: the simplest endpoints
2. `src/main/java/com/udemy/web/services/restful_web_services/helloworld/HelloWorldBean.java`: a bean serialised to JSON
3. `src/main/java/com/udemy/web/services/restful_web_services/user/User.java`: the resource
4. `src/main/java/com/udemy/web/services/restful_web_services/user/UserDaoService.java`: an in-memory "DAO"
5. `src/main/java/com/udemy/web/services/restful_web_services/user/UserResource.java`: status codes and `Location`

## Revision notes
- Return `ResponseEntity.created(uri).build()` from a POST. Clients then read the `Location` header to find the new resource.
- `ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")` builds the URI from the current request, so there's no hardcoded host.
- `ResponseStatusException(HttpStatus.NOT_FOUND, ...)` is the quickest way to return a 404. A custom exception with `@ResponseStatus` or a `@ControllerAdvice` is the next step.
- `findFirst().get()` throws `NoSuchElementException` (a 500). Use `orElse(null)` or `orElseThrow(...)` instead.
- `UserDaoService` uses a static `ArrayList`, which is **not thread-safe**. It's fine for learning; the course replaces it with JPA later.
- Jackson reads `LocalDate` in ISO format (`"2000-01-01"`).
- springdoc **3.x** is the line that works with Spring Boot 4; 2.x targets Boot 3.

## Status
✅ **Working.** Verified: `/users/1` returns 200, `/users/99` returns 404, POST returns 201 with a `Location` header, and `/v3/api-docs` returns 200. There's no update or delete endpoint yet.
