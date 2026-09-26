# 02 - Spring Foundations

This stage covers Spring without Spring Boot: the IoC container, `JdbcTemplate`, Hibernate integration and XML-configured Spring MVC on Tomcat. Everything here is configured by hand (XML or `@Configuration`), so you see exactly what Boot auto-configures later. Next stage: [03 - Spring Boot](../03-spring-boot/).

These are Maven projects without a Maven wrapper. Run `mvn` from the project folder, or point any sibling wrapper at it, e.g. `../../03-spring-boot/restful-web-services/mvnw -f pom.xml compile`.

| # | Project | Topics | Status |
| :- | :--- | :--- | :--- |
| 1 | [spring-core](./spring-core/) | IoC/DI, XML + annotations + Java config, autowiring, lifecycle, scopes, SpEL | ✅ Working |
| 2 | [spring-jdbc](./spring-jdbc/) | `JdbcTemplate`, DAO, `RowMapper`, MySQL | ✅ Working (needs MySQL) |
| 3 | [spring-orm](./spring-orm/) | JPA entity mapping, `HibernateTemplate` DAO | 🚧 Unfinished tutorial |
| 4 | [spring-mvc](./spring-mvc/) | `DispatcherServlet`, controllers, JSP/JSTL, form binding, Hibernate save | 🚧 Builds, not run on Tomcat yet |

## Suggested study order
1. **spring-core**: follow its README order from XML beans through to `@Configuration`.
2. **spring-jdbc**: reuse DI to build a DAO on `JdbcTemplate`.
3. **spring-orm**: swap SQL for entity mapping, then finish `config.xml` (steps are in its README).
4. **spring-mvc**: put it all behind a web layer. Its `spring-servlet.xml` is also the reference for spring-orm's missing config.

## Quick revision checklist
- [ ] IoC vs DI, and constructor vs setter injection trade-offs
- [ ] Autowiring `byType` / `byName` / `constructor`, and what `@Qualifier` solves
- [ ] Singleton vs prototype scope, and when destroy callbacks run
- [ ] How `@Configuration` + `@Bean` replaces XML
- [ ] What `JdbcTemplate` does for you, and why `?` placeholders matter
- [ ] The DataSource -> SessionFactory -> HibernateTemplate -> TransactionManager chain
- [ ] The request flow: `DispatcherServlet` -> controller -> `ViewResolver` -> JSP
- [ ] javax vs jakarta: why Tomcat 10 / Spring 6 need `jakarta.*`
