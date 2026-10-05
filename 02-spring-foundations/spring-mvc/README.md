# Spring Web MVC: pages, forms and a JSON API

> A classic Spring MVC web app: the `DispatcherServlet` (the front desk that receives every request), controllers, JSP views, forms with validation, a service and DAO layer on Hibernate, error pages, and a REST API that returns JSON.

**Before this:** [spring-core](../spring-core/) and [spring-orm](../spring-orm/) topics 02-03 (the data layer here reuses that setup).

## Run it
There is no server to install and no database to set up (it uses in-memory H2):

```bash
./mvnw -pl spring-mvc jetty:run          # from 02-spring-foundations, then open:
```

**http://localhost:8080/springmvc/**. Every page has a navigation bar with links to all the topics. Stop the server with `Ctrl+C`.

Other ways to run it:
- **Tomcat 10.1:** run `./mvnw -pl spring-mvc package` and deploy `spring-mvc/target/springmvc.war`.
- **MySQL:** set `DB_URL=jdbc:mysql://localhost:3306/springmvc?createDatabaseIfNotExist=true` plus `DB_USERNAME` / `DB_PASSWORD` in the environment where the server runs.
- **Tests:** `./mvnw -pl spring-mvc test`. The MockMvc tests (fake requests, sent without a real server) need no server.

At startup, Jetty prints many `scanned from multiple locations` warnings about the JSTL jars. They are harmless: the plugin just sees the same jar twice while scanning.

## Topics

| # | Package | Try it | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_dispatcher_and_config` | read `web.xml` → `spring-servlet.xml` → `WebMvcConfig` | How a request reaches your code; the view resolver |
| 02 | `topic02_controllers_and_views` | `/`, `/about`, `/help` | `@Controller`, `@GetMapping`, `Model` vs `ModelAndView`, EL and JSTL |
| 03 | `topic03_request_data` | `/greet?name=Asha`, `/students/7`, `/search?city=Pune&minAge=18` | `@RequestParam`, `@PathVariable`, `@ModelAttribute`; output that is safe from XSS |
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
- **Why:** everything else in web MVC depends on this flow.
- **How:** browser → `DispatcherServlet` (registered in `web.xml`) → the matching `@Controller` method → it returns a view name → `/WEB-INF/views/<name>.jsp` → HTML.
  - `spring-servlet.xml` is found by its name (the rule is `<servlet-name>-servlet.xml`).
  - The beans now live in Java (`WebMvcConfig`, `PersistenceConfig`). So the XML only points at them, and the tests load exactly the same config.
- **No-XML alternative:** Spring Boot does this for you. Without Boot, a class can do the same job as `web.xml`. Don't add it next to `web.xml`, or you get two DispatcherServlets:

```java
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses()    { return null; }
    protected Class<?>[] getServletConfigClasses() { return new Class<?>[] {WebMvcConfig.class}; }
    protected String[] getServletMappings()        { return new String[] {"/"}; }
}
```

### 02 Controllers and views
- **How:** the method returns a view name. Data goes into the `Model`, and the JSP reads it with `${...}` and `<c:forEach>`. `ModelAndView` keeps the data and the view name together in one object.
- **Mistake:** scriptlets (`<% ... %>`, Java code inside a JSP). `index.jsp` shows the old way in a comment. Use EL and JSTL instead.

### 03 Request data
- **How:** Spring converts the request's text into typed parameters. A wrong type (`/students/abc`) gives a **400 Bad Request**, not a crash.
- **Security:** anything from the request that is printed with a bare `${value}` can add HTML or scripts to the page (XSS, cross-site scripting). The pages print user data with `<c:out>`, which escapes it (makes it show as plain text). Try `/greet?name=<b>x</b>`.

### 04 Service and DAO layers
- **Why:** each layer has one job. The web form and the JSON API use the same `UserService`.
- **How:** the controller handles HTTP. The `@Service` holds the business rules and the `@Transactional` boundary. The `@Repository` talks to the database.
- **Security:** passwords are hashed with **BCrypt** (`spring-security-crypto`) before they are stored. A hash is a one-way scramble: you can check a password against it, but not get the password back. The old version saved the plain password, and `success.jsp` printed it back.

### 05 Forms, validation, Post/Redirect/Get
- **How:**
  - A `SignupForm` object with `@NotBlank`, `@Email` and `@Size` receives the form fields.
  - `@Valid` checks it, and the problems go into `BindingResult`, which must come right after the form parameter.
  - On error, show the same view again. `<form:errors>` shows the messages, and the values the user typed are kept.
  - On success, `redirect:/success`, with the new user carried in a flash attribute (a value that lives for one redirect only).
- **Why redirect:** after a plain POST, pressing F5 sends the form again and registers the user twice.
- **Gotcha:** Spring builds validation messages through `MessageFormat`, so a single `'` in a message disappears. Write "is not" instead of "doesn't".

### 06 Exception handling
- **How:** controllers just throw, for example `UserNotFoundException`. One `@ControllerAdvice` turns each exception into a response: 404 with a helpful message for a known problem, and 500 with a **generic** message (no internal details) for bugs. Spring's own errors (bad parameter, unknown URL) are thrown again, so they keep their proper 400/404/405 status.

### 07 REST controller and JSON
- **How:**
  - `@RestController` returns data, and Jackson turns it into JSON.
  - `ResponseEntity.created(...)` gives 201 plus a `Location` header (where the new resource lives).
  - Validation failures come back as 400 `ProblemDetail` JSON (RFC 9457) with the field errors.
  - A separate `@RestControllerAdvice`, limited to the API package and ordered first, keeps API errors as JSON instead of HTML.
- **Security:** return a `UserResponse` record, never the entity, so `passwordHash` can't leak out.

## Revision checklist
- [ ] Trace a request from the browser to the JSP and back.
- [ ] `Model` vs `ModelAndView`; `@Controller` vs `@RestController`.
- [ ] `@RequestParam` vs `@PathVariable` vs `@ModelAttribute`, and when each fits.
- [ ] Why `BindingResult` must come right after the `@Valid` parameter.
- [ ] What Post/Redirect/Get prevents, and what a flash attribute is.
- [ ] Why passwords are hashed, and why entities are not returned from APIs.
- [ ] How `@ControllerAdvice` keeps error handling in one place; 400 vs 404 vs 409 vs 500.
- [ ] What XSS is, and how `<c:out>` prevents it.

## Status
✅ Working: runs on Jetty 12 with `jetty:run`. Every page, the form flow and the JSON API were checked over real HTTP, on H2 and against MySQL 8.4 in Docker (users stored with BCrypt hashes). `./mvnw -pl spring-mvc test` passes (15 MockMvc tests). The WAR (`./mvnw -pl spring-mvc package`) was also deployed to Tomcat 10.1 in Docker: the pages, the form flow and the JSON API work there too.
