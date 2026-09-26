# Learn JPA and Hibernate

> Spring Boot persistence hands-on with an H2 in-memory database. From the in28minutes Spring Boot course. So far it covers the **Spring JDBC** step only.

## What it teaches
- H2 in-memory database auto-configured by Spring Boot
- Creating the schema from `schema.sql` at startup
- `JdbcTemplate` for running plain SQL (`update(...)`)
- `CommandLineRunner`: code that runs once the app has started
- Java text blocks (`"""`) for multi-line SQL

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test                     # context-load test (passes)
```
The app runs on port 8080 and inserts one row into `course` at startup.

Browse the data at http://localhost:8080/h2-console using JDBC URL `jdbc:h2:mem:testdb`, user `sa` and an empty password, then run `select * from course;`.

## Read the code in this order
1. `src/main/resources/application.properties`: H2 URL and console
2. `src/main/resources/schema.sql`: the `course` table
3. `src/main/java/com/springboot/udemy/ranga/course/jdbc/CourseJdbcRepository.java`: `JdbcTemplate` insert
4. `src/main/java/com/springboot/udemy/ranga/course/jdbc/CourseJdbcCommandLineRunner.java`: runs the insert at startup

## Revision notes
- Spring Boot runs `schema.sql` (and `data.sql`) automatically for embedded databases such as H2.
- Setting `spring.datasource.url=jdbc:h2:mem:testdb` fixes the database name, so the console URL is predictable.
- `JdbcTemplate.update(sql, args...)` handles INSERT, UPDATE and DELETE. For values, pass `?` placeholders plus arguments rather than concatenating strings.
- The insert here hardcodes `id=2`, so it only runs cleanly once per start. The in-memory DB is wiped on every restart.
- A `CommandLineRunner` bean's `run()` executes after the context has started, which makes it good for seed data.
- In the course, the progression is **JDBC → Spring JDBC → JPA (`EntityManager`) → Spring Data JPA**.

## Status
🚧 **Partial.** The Spring JDBC step works and the test passes. There are no JPA entities or `EntityManager` code yet, despite the project name.
