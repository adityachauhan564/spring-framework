# 02 - Spring Foundations

Spring **without** Spring Boot: the IoC container, JDBC, Hibernate/JPA and web MVC, all configured by hand. Boot automates exactly this setup, so knowing it by hand turns Boot's "magic" into something you can read, debug and change. Next stage: [03 - Spring Boot](../03-spring-boot/).

**Before this:** [01 - Core Java](../01-core-java/): classes and interfaces, collections, lambdas, Maven ([topic 51](../01-core-java/09-testing-and-build/topic51_maven/)), and especially interfaces + dependency injection by hand ([topic 14](../01-core-java/03-oop/topic14_interfaces_and_dependency_injection/)).

## Run it (nothing to install except a JDK 21)
All four projects are modules of one Maven build, with the Maven wrapper in this folder. Databases are in-memory H2 by default, and the web app runs on an embedded Jetty.

```bash
cd 02-spring-foundations
./mvnw verify                                   # build all 4 projects and run all 43 tests
./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ContainerWiringDemo
./mvnw -pl spring-mvc jetty:run                 # then open http://localhost:8080/springmvc/
```

On Windows, use `mvnw.cmd` instead of `./mvnw`. Each project README lists its demo classes. MySQL is optional: set `DB_URL`, `DB_USERNAME` and `DB_PASSWORD` (see the spring-jdbc README).

| # | Project | Topics | Status |
| :- | :--- | :--- | :--- |
| 1 | [spring-core](./spring-core/) | 13 topics: why Spring, XML/annotation/Java config, autowiring, scopes, lifecycle, SpEL, profiles, AOP | ✅ Working (15 tests) |
| 2 | [spring-jdbc](./spring-jdbc/) | 5 topics: `JdbcTemplate`, DAO, row mappers, named parameters/keys/batch, transactions | ✅ Working (8 tests) |
| 3 | [spring-orm](./spring-orm/) | 5 topics: entity mapping, SessionFactory, CRUD + HQL, entity states, JPA | ✅ Working (5 tests) |
| 4 | [spring-mvc](./spring-mvc/) | 7 topics: DispatcherServlet, controllers/views, request data, layers, forms + validation, error handling, REST JSON | ✅ Working (15 tests) |

## Suggested study order
1. **spring-core** 01 → 13. By topic13 you'll know what a proxy is, which the next two projects depend on.
2. **spring-jdbc** 01 → 05. SQL by hand, finishing with transactions.
3. **spring-orm** 01 → 05. The same data work with Hibernate writing the SQL, then JPA.
4. **spring-mvc** 01 → 07. Put everything behind web pages and a JSON API.

## Quick revision checklist
- [ ] IoC vs DI; constructor vs setter injection, and why constructor is the default choice
- [ ] Autowiring `byName` / `byType` / `constructor`, and what `@Qualifier` and `@Primary` solve
- [ ] Singleton vs prototype scope; when init and destroy callbacks run
- [ ] How `@Configuration` + `@Bean` replaces XML, and why `@Bean` methods return singletons
- [ ] `${...}` properties vs `#{...}` SpEL; how `@Profile` picks beans
- [ ] What an AOP proxy is, and why self-invocation skips `@Transactional`
- [ ] What `JdbcTemplate` does for you, and why `?` / `:name` placeholders matter
- [ ] When `@Transactional` commits and when it rolls back
- [ ] The DataSource → SessionFactory/EntityManagerFactory → TransactionManager chain
- [ ] Persistent vs detached entities; dirty checking
- [ ] The request flow: `DispatcherServlet` → controller → view resolver → JSP
- [ ] Validation with `@Valid` + `BindingResult`, and Post/Redirect/Get
- [ ] `@ControllerAdvice` error handling; `@RestController` + JSON with proper status codes
- [ ] Why passwords are hashed (BCrypt) and entities are never returned from an API
- [ ] javax vs jakarta: Spring 6 and Tomcat 10+ need `jakarta.*`
