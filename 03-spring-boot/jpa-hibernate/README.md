# Learn JPA and Hibernate

> Three ways to save and load the same `course` table with Spring Boot, each writing less code than the one before: **Spring JDBC** (you write the SQL) → **JPA `EntityManager`** (Hibernate writes the SQL) → **Spring Data JPA** (you write no implementation at all). Then query methods, `@Query`, paging and sorting. Based on the in28minutes Spring Boot course.

**Before this:** [spring-boot-basics](../spring-boot-basics/). For comparison, [02-spring-foundations](../../02-spring-foundations/) spring-jdbc and spring-orm did the same work while configuring every bean by hand.

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test
```
At startup, each topic's `CommandLineRunner` prints a `=== topicNN ===` section. The `Hibernate:` lines in between are the SQL that JPA generated for you (`spring.jpa.show-sql=true`).

**Browse the data:** http://localhost:8080/h2-console with JDBC URL `jdbc:h2:mem:testdb`, user `sa` and an empty password. Then run `select * from course;`.

## Topics

| # | Package | What you learn |
| :- | :--- | :--- |
| 01 | `topic01_spring_jdbc` | `JdbcTemplate` insert/delete/query, a `RowMapper`, text-block SQL, `schema.sql` |
| 02 | `topic02_jpa_entity_manager` | `@Entity`, `EntityManager` merge/find/remove, `@Transactional`, generated SQL |
| 03 | `topic03_spring_data_jpa` | `JpaRepository`: CRUD with no implementation written |
| 04 | `topic04_queries_paging_sorting` | derived query methods, `@Query` (JPQL), `Sort`, `PageRequest` and `Page` |

## Topic notes

### What Boot did for you
Seeing H2 and JPA on the classpath, Boot created:
- a `DataSource` with a HikariCP connection pool;
- a `JdbcTemplate`;
- an `EntityManagerFactory`;
- a `JpaTransactionManager`.

It also ran `schema.sql`. In stage 02 each of those was a bean you wrote.

### 01 Spring JDBC
- **How:** `jdbcTemplate.update(sql, args...)` for writes. `query(sql, rowMapper, args...)` for reads; a lambda maps each row by **column name**.
- **Mistake (fixed):** the old insert hard-coded `id=2` in the SQL string. Values now go in as `?` parameters.

### 02 JPA with EntityManager
- **How:** map the class once with `@Entity` / `@Id`, then work with objects: `merge` saves (insert or update), `find` loads, and `remove` deletes, all inside `@Transactional`. Hibernate writes the SQL, which you can watch in the console.

### 03 Spring Data JPA
- **How:** `interface CourseSpringDataRepository extends JpaRepository<Course, Long>`. Spring generates the implementation at startup, giving you `save`, `findById`, `findAll`, `deleteById`, `count` and more. Topic02's hand-written class has become one interface.

### 04 Queries, paging and sorting
- **How:**
  - **Derived queries** turn a method name into a query: `findByAuthor`, `findByNameContainingIgnoreCase`, `countByAuthor`.
  - **`@Query("select c from Course c ...")`** covers anything a method name can't express. It's JPQL, so it uses entity and field names.
  - **Paging:** `PageRequest.of(page, size, Sort.by("name"))`. Pages start at 0, and a `Page` also knows the total count.

### `schema.sql` vs `ddl-auto`
Here `schema.sql` defines the table and `spring.jpa.hibernate.ddl-auto=none` stops Hibernate from changing it. The books API does the opposite: Hibernate creates the tables from the entities. Real projects use `validate` plus a migration tool such as Flyway.

## Revision checklist
- [ ] JDBC vs JPA vs Spring Data: who writes the SQL, and who writes the implementation?
- [ ] Which beans Boot auto-configures for a database.
- [ ] How a derived query method name becomes a query.
- [ ] `@Query` JPQL vs SQL.
- [ ] How to request page 2 of 10 items sorted by name, and what a `Page` contains.
- [ ] `ddl-auto` values, and why `update` isn't used in production.

## Status
✅ Working: all 4 topics run at startup (the table ends with 6 courses). 6 tests pass: `@JdbcTest` and `@DataJpaTest` slices plus a full-app test. It's no longer "JDBC step only"; the JPA and Spring Data steps are finished.
