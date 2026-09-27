# Spring Web MVC: pages, forms and a JSON API

> A classic Spring MVC web app: the `DispatcherServlet`, controllers, JSP views, forms with validation, a service and DAO layer on Hibernate, error pages, and a REST API that returns JSON.

**Before this:** [spring-core](../spring-core/) and [spring-orm](../spring-orm/) topics 02-03 (the data layer here reuses that setup).

## Run it
There's no server to install and no database to set up (in-memory H2):

```bash
./mvnw -pl spring-mvc jetty:run          # from 02-spring-foundations, then open:
```

**http://localhost:8080/springmvc/**. Every page has a navigation bar linking all the topics. Stop the server with `Ctrl+C`.

Other ways to run it:
- **Tomcat 10.1:** run `./mvnw -pl spring-mvc package` and deploy `spring-mvc/target/springmvc.war`.
- **MySQL:** set `DB_URL=jdbc:mysql://localhost:3306/springmvc?createDatabaseIfNotExist=true` plus `DB_USERNAME` / `DB_PASSWORD` in the environment the server runs in.
- **Tests:** `./mvnw -pl spring-mvc test`. The MockMvc tests need no server.

Jetty prints many `scanned from multiple locations` warnings about the JSTL jars at startup. They're harmless: the plugin sees the same jar twice while scanning.

## Topics

| # | Package | Try it | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_dispatcher_and_config` | read `web.xml` → `spring-servlet.xml` → `WebMvcConfig` | How a request reaches your code; the view resolver |
| 02 | `topic02_controllers_and_views` | `/`, `/about`, `/help` | `@Controller`, `@GetMapping`, `Model` vs `ModelAndView`, EL and JSTL |
| 03 | `topic03_request_data` | `/greet?name=Asha`, `/students/7`, `/search?city=Pune&minAge=18` | `@RequestParam`, `@PathVariable`, `@ModelAttribute`; XSS-safe output |
| 04 | `topic04_service_and_dao_layers` | (used by 05-07) | Controller → service → DAO; transactions; BCrypt password hashing |
| 05 | `topic05_forms_validation_prg` | `/contact` | Form binding, `@Valid` + `BindingResult`, Post/Redirect/Get, flash attributes |
| 06 | `topic06_exception_handling` | `/users`, `/users/999`, `/error-demo` | `@ControllerAdvice` + `@ExceptionHandler`, proper 404/500 pages |
| 07 | `topic07_rest_controller_json` | `/api/users` (see below) | `@RestController`, JSON, `ResponseEntity`, 201/400/404/409, `ProblemDetail` |

Try the API from a terminal:

```bash
curl http://localhost:8080/springmvc/api/users
curl -X POST -H "Content-Type: application/json" \
     -d '{"userName":"Asha","email":"asha@example.com","password":"secret123"}' \
     http://localhost:8080/springmvc/api/users
```

## Topic notes

### 01 How a request reaches your code
- **Why:** everything else in web MVC hangs on this flow.
- **How:** browser → `DispatcherServlet` (registered in `web.xml`) → the matching `@Controller` method → it returns a view name → `/WEB-INF/views/<name>.jsp` → HTML.
  - `spring-servlet.xml` is found by naming convention (`<servlet-name>-servlet.xml`).
  - The beans now live in Java (`WebMvcConfig`, `PersistenceConfig`), so the XML only points at them, and the tests load exactly the same config.
- **No-XML alternative:** Spring Boot does this for you, and without Boot a class does the same job as `web.xml` (don't add it alongside `web.xml`, or you get two DispatcherServlets):

```java
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses()    { return null; }
    protected Class<?>[] getServletConfigClasses() { return new Class<?>[] {WebMvcConfig.class}; }
    protected String[] getServletMappings()        { return new String[] {"/"}; }
}
```

### 02 Controllers and views
- **How:** the method returns a view name. Data goes into the `Model`, and the JSP reads it with `${...}` and `<c:forEach>`. `ModelAndView` bundles the data and the view name in one object.
- **Mistake:** scriptlets (`<% ... %>`) in JSPs. `index.jsp` shows the old way in a comment. Use EL and JSTL instead.

### 03 Request data
- **How:** Spring converts request text into typed parameters. A wrong type (`/students/abc`) is a **400 Bad Request**, not a crash.
- **Security:** anything from the request printed with a bare `${value}` can inject HTML or scripts (XSS). The pages print user data with `<c:out>`, which escapes it. Try `/greet?name=<b>x</b>`.

### 04 Service and DAO layers
- **Why:** each layer has one job. The web form and the JSON API share the same `UserService`.
- **How:** the controller handles HTTP, the `@Service` holds the business rules and the `@Transactional` boundary, and the `@Repository` talks to the database.
- **Security:** passwords are hashed with **BCrypt** (`spring-security-crypto`) before they're stored. The old version saved the plain password and `success.jsp` printed it back.

### 05 Forms, validation, Post/Redirect/Get
- **How:**
  - A `SignupForm` object with `@NotBlank`, `@Email` and `@Size` receives the form fields.
  - `@Valid` checks it, and the problems land in `BindingResult`, which must come right after the form parameter.
  - On error, show the same view again; `<form:errors>` displays the messages and the typed values are kept.
  - On success, `redirect:/success`, carrying the new user in a flash attribute.
- **Why redirect:** after a plain POST, pressing F5 re-submits the form and registers the user twice.
- **Gotcha:** Spring renders validation messages through `MessageFormat`, so a single `'` in a message disappears. Write "is not" instead of "doesn't".

### 06 Exception handling
- **How:** controllers just throw, for example `UserNotFoundException`. One `@ControllerAdvice` turns each exception into a response: 404 with a helpful message for a known problem, and 500 with a **generic** message (no internal details) for bugs. Spring's own errors (bad parameter, unknown URL) are re-thrown so they keep their proper 400/404/405 status.

### 07 REST controller and JSON
- **How:**
  - `@RestController` returns data, which Jackson turns into JSON.
  - `ResponseEntity.created(...)` gives 201 plus a `Location` header.
  - Validation failures come back as 400 `ProblemDetail` JSON (RFC 9457) with field errors.
  - A separate `@RestControllerAdvice`, scoped to the API package and ordered first, keeps API errors as JSON rather than HTML.
- **Security:** return a `UserResponse` record, never the entity, so `passwordHash` can't leak.

## Revision checklist
- [ ] Trace a request from the browser to the JSP and back.
- [ ] `Model` vs `ModelAndView`; `@Controller` vs `@RestController`.
- [ ] `@RequestParam` vs `@PathVariable` vs `@ModelAttribute`, and when each fits.
- [ ] Why `BindingResult` must follow the `@Valid` parameter.
- [ ] What Post/Redirect/Get prevents, and what a flash attribute is.
- [ ] Why passwords are hashed, and why entities aren't returned from APIs.
- [ ] How `@ControllerAdvice` centralises error handling; 400 vs 404 vs 409 vs 500.
- [ ] What XSS is, and how `<c:out>` prevents it.

## Status
✅ Working: runs on Jetty 12 with `jetty:run`, and every page, the form flow and the JSON API were checked over real HTTP. `./mvnw -pl spring-mvc test` passes (15 MockMvc tests). The WAR targets Tomcat 10.1 / Jakarta EE 10; deploying it to Tomcat itself wasn't tested in this pass.
