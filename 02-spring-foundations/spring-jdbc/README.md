# Spring JDBC

> CRUD on a MySQL `student` table with `JdbcTemplate`, a DAO layer and a `RowMapper`, configured with Java `@Configuration`.

## What it teaches
- `DriverManagerDataSource` + `JdbcTemplate` as Spring beans
- DAO pattern: `StudentDao` interface, `StudentDaoImpl` implementation
- `update(...)` for insert/update/delete, `queryForObject` / `query` for reads
- Mapping rows to objects with a `RowMapper`
- Java-based config with `@ComponentScan`

## Run it
Needs a local MySQL. Create the schema first:

```sql
CREATE DATABASE springjdbc;
USE springjdbc;
CREATE TABLE student (id INT PRIMARY KEY, name VARCHAR(100), city VARCHAR(100));
```

Then set `DB_PASSWORD` (and optionally `DB_USERNAME`, default `root`) as environment variables; `jdbcconfig.java` reads them.

```bash
mvn -q compile dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes;$(cat cp.txt)" com.springcore.jdbc.App
```

Use `:` instead of `;` on macOS/Linux. `App` prints every row in `student`.

## Read the code in this order
1. `src/main/java/com/springcore/jdbc/jdbcconfig.java` - DataSource and JdbcTemplate beans
2. `dao/StudentDao.java` - the contract
3. `dao/StudentDaoImpl.java` - SQL with `?` placeholders
4. `dao/RowMapperImpl.java` - `ResultSet` -> `Student`
5. `entities/Student.java`
6. `App.java` - boot the context and call the DAO

## Revision notes
- `JdbcTemplate` removes JDBC boilerplate: opening/closing connections, statements and result sets, and translating `SQLException` into Spring's `DataAccessException`.
- Always use `?` placeholders (as here), never string concatenation - that prevents SQL injection.
- `update()` returns the number of affected rows.
- `queryForObject` throws `EmptyResultDataAccessException` if no row matches - handle it for "find by id".
- `RowMapper` maps one row; `query(sql, rowMapper)` applies it to every row and returns a `List`.
- `DriverManagerDataSource` opens a new connection each time - fine for learning, use a pool (HikariCP) in real apps.
- `getBean("studentDao", StudentDao.class)` works because of `@Component("studentDao")` + `@ComponentScan`.

## Status
✅ Working (compiles; running it needs MySQL and the `DB_PASSWORD` env var).
- Only `getAllStudents()` is called from `App`; insert/update/delete/get-by-id are written but not exercised.
- `src/test/java/.../AppTest.java` is the archetype placeholder test (JUnit 3.8).
