# Learn JPA and Hibernate

> Three ways to save and load the same `course` table with Spring Boot. Each way needs less code than the one before: **Spring JDBC** (you write the SQL) → **JPA `EntityManager`** (Hibernate writes the SQL) → **Spring Data JPA** (you write no implementation at all). Then query methods, `@Query`, paging and sorting. Based on the in28minutes Spring Boot course.

**Before this:** [spring-boot-basics](../spring-boot-basics/). To compare, [02-spring-foundations](../../02-spring-foundations/) spring-jdbc and spring-orm did the same work, but set up every bean by hand.

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test
```
At startup, each topic's `CommandLineRunner` prints a `=== topicNN ===` section. The `Hibernate:` lines in between are the SQL that JPA wrote for you (`spring.jpa.show-sql=true`).

**See the data:** open http://localhost:8080/h2-console with JDBC URL `jdbc:h2:mem:testdb`, user `sa` and an empty password. Then run `select * from course;`.

## Topics

| # | Package | What you learn |
| :- | :--- | :--- |
| 01 | `topic01_spring_jdbc` | `JdbcTemplate` insert/delete/query, a `RowMapper`, text-block SQL, `schema.sql` |
| 02 | `topic02_jpa_entity_manager` | `@Entity`, `EntityManager` merge/find/remove, `@Transactional`, the SQL Hibernate writes |
| 03 | `topic03_spring_data_jpa` | `JpaRepository`: CRUD without writing any implementation |
| 04 | `topic04_queries_paging_sorting` | derived query methods, `@Query` (JPQL), `Sort`, `PageRequest` and `Page` |

## Topic notes

### What Boot did for you
Boot saw H2 and JPA on the classpath, so it created:
- a `DataSource` with a HikariCP connection pool (a set of ready connections that get reused);
- a `JdbcTemplate`;
- an `EntityManagerFactory`;
- a `JpaTransactionManager`.

It also ran `schema.sql`. In stage 02, each of those was a bean you wrote yourself.

### 01 Spring JDBC
- **How:** `jdbcTemplate.update(sql, args...)` for writes. `query(sql, rowMapper, args...)` for reads, where a lambda turns each row into an object by **column name**.
- **Mistake (fixed):** the old insert hard-coded `id=2` inside the SQL string. Now the values go in as `?` parameters.

### 02 JPA with EntityManager
- **How:** map the class once with `@Entity` / `@Id`. Then work with objects: `merge` saves (insert or update), `find` loads, and `remove` deletes, all inside `@Transactional`. Hibernate writes the SQL, and you can watch it in the console.

### 03 Spring Data JPA
- **How:** `interface CourseSpringDataRepository extends JpaRepository<Course, Long>`. Spring writes the implementation at startup, and you get `save`, `findById`, `findAll`, `deleteById`, `count` and more. Topic02's hand-written class has become just one interface.

### 04 Queries, paging and sorting
- **How:**
  - **Derived queries** turn a method name into a query: `findByAuthor`, `findByNameContainingIgnoreCase`, `countByAuthor`.
  - **`@Query("select c from Course c ...")`** covers anything a method name can't say. It is JPQL, so it uses entity and field names, not table names.
  - **Paging:** `PageRequest.of(page, size, Sort.by("name"))`. Pages start at 0, and a `Page` also knows the total count. Like a train chart split into pages: you ask for one page, and it also tells you the total number of passengers.

### `schema.sql` vs `ddl-auto`
Here `schema.sql` defines the table, and `spring.jpa.hibernate.ddl-auto=none` stops Hibernate from changing it. The books API does the opposite: Hibernate creates the tables from the entities. Real projects use `validate` plus a migration tool such as Flyway.

## Revision checklist
- [ ] JDBC vs JPA vs Spring Data: who writes the SQL, and who writes the implementation?
- [ ] Which beans Boot sets up automatically for a database.
- [ ] How a derived query method name becomes a query.
- [ ] `@Query` JPQL vs SQL.
- [ ] How to ask for page 2 of 10 items sorted by name, and what a `Page` contains.
- [ ] `ddl-auto` values, and why `update` is not used in production.

## Status
✅ Working: all 4 topics run at startup (the table ends with 6 courses). 6 tests pass: `@JdbcTest` and `@DataJpaTest` slices (tests that load only one part of the app) plus a full-app test. It is no longer "JDBC step only": the JPA and Spring Data steps are finished.
