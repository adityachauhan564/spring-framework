# Spring JDBC: SQL without the boilerplate

> Talking to a relational database (tables with rows and columns) using `JdbcTemplate`: from a first query to DAOs, row mappers, named parameters, batches and transactions.

**Before this:** [spring-core](../spring-core/) topics 06, 07 and 11 (constructor injection, `@Repository`, `@Configuration`) and 13 (proxies, needed for transactions).

## Run it
No database to install. By default every run uses a fresh **in-memory H2** database (it lives only while the program runs), and `db/schema.sql` creates the tables at startup.

```bash
./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic02_crud_dao.CrudDemo
./mvnw -q -pl spring-jdbc test
```

**Using MySQL instead:** create an empty database, then set these environment variables before running:

```bash
export DB_URL="jdbc:mysql://localhost:3306/springjdbc?createDatabaseIfNotExist=true" DB_USERNAME=root DB_PASSWORD=your-password
# PowerShell: $env:DB_URL="jdbc:mysql://localhost:3306/springjdbc?createDatabaseIfNotExist=true"; $env:DB_PASSWORD="your-password"
```

`schema.sql` deletes and re-creates its three tables on every run. So only point it at a database you use for learning. The tests always use H2, even if `DB_URL` is set.

## Topics

| # | Package | Run | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_datasource_and_jdbctemplate` | `JdbcTemplateDemo` | `DataSource` vs `JdbcTemplate`, `update()` vs `queryForObject()`, `?` placeholders |
| 02 | `topic02_crud_dao` | `CrudDemo` | The DAO pattern: create, read, update, delete behind an interface; `Optional` for "not found" |
| 03 | `topic03_row_mappers` | `RowMapperStylesDemo` | Four ways to turn rows into objects |
| 04 | `topic04_named_params_keys_batch` | `NamedParametersDemo` | `:named` parameters, ids made by the database (`KeyHolder`), `batchUpdate` |
| 05 | `topic05_transactions` | `TransactionsDemo` | `@Transactional`: all or nothing, shown with a money-transfer demo |

## Topic notes

### 01 DataSource and JdbcTemplate
- **Why:** plain JDBC means opening and closing connections, statements and result sets yourself, and catching `SQLException` around every query. `JdbcTemplate` does all of that for you.
- **How:** the `DataSource` says *where* connections come from, and `JdbcTemplate` runs SQL on it. `update()` returns how many rows changed. Spring turns database errors into `DataAccessException`, for example `DuplicateKeyException`.
- **Mistake:** building SQL with `+`. Always use `?`, or you open the door to SQL injection (a user typing SQL into your query).

### 02 CRUD with a DAO
- **Why:** callers should never see SQL. With the DAO (Data Access Object) interface you can switch to Hibernate (spring-orm) or Spring Data (stage 03) later.
- **How:** a `@Repository` gets its `JdbcTemplate` through the constructor. `findById` returns `Optional`: use `query(...)` and take the first element, because `queryForObject` throws `EmptyResultDataAccessException` when no row matches.
- **Exercise:** add `findByCity(String city)`.

### 03 Row mappers
- **Why:** a `ResultSet` is rows and columns, but your code wants objects.
- **How:** a `RowMapper` class (can be reused), a lambda (it is a functional interface), `BeanPropertyRowMapper` (matches columns to setters by name), or `queryForList` (a `Map` per row, no class at all).
- **Mistake:** `rs.getInt(1)`. If the columns are reordered, values go into the wrong fields. Read columns by name.

### 04 Named parameters, keys and batches
- **Why:** ten `?` markers are hard to read. Apps usually let the database make the ids. And inserting 1,000 rows one at a time is slow.
- **How:** `NamedParameterJdbcTemplate` with `:title`, `KeyHolder` to get the new id back, and `batchUpdate` to send many rows in one trip to the database. `DataClassRowMapper` fills a **record** from a row through its constructor.

### 05 Transactions
- **Why:** a transfer is two updates. A crash between them must not create or destroy money.
- **How:** use `@EnableTransactionManagement` and a `DataSourceTransactionManager`. On a `@Transactional` method, a RuntimeException means rollback (undo everything) and a normal return means commit (save everything). The demo shows the total staying at 600 with the transaction, and jumping to 1600 without it. With a rollback, the money never actually leaves the account.
- **Mistakes:** checked exceptions don't roll back unless you say `rollbackFor`. Self-invocation skips the transaction (spring-core topic13).

## Revision checklist
- [ ] What `JdbcTemplate` removes compared with plain JDBC.
- [ ] `update` vs `query` vs `queryForObject`, and what each returns or throws.
- [ ] Why `?` / `:name` placeholders stop SQL injection.
- [ ] Four ways to turn rows into objects.
- [ ] How to get an id made by the database back.
- [ ] What "atomic" (all or nothing) means, and when `@Transactional` commits or rolls back.

## Status
✅ Working: all 5 demos run on H2 with nothing installed. `./mvnw -pl spring-jdbc test` passes (8 tests: CRUD, keys and batch, transaction rollback). MySQL mode is chosen with `DB_URL`. All 5 demos were also run against MySQL 8.4 in Docker.
