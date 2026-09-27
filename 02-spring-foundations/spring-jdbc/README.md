# Spring JDBC: SQL without the boilerplate

> Talking to a relational database with `JdbcTemplate`, from a first query to DAOs, row mappers, named parameters, batches and transactions.

**Before this:** [spring-core](../spring-core/) topics 06, 07 and 11 (constructor injection, `@Repository`, `@Configuration`) and 13 (proxies, for transactions).

## Run it
No database to install: by default every run uses a fresh **in-memory H2** database, and `db/schema.sql` creates the tables on startup.

```bash
./mvnw -q -pl spring-jdbc compile exec:java -Dexec.mainClass=com.springcore.jdbc.topic02_crud_dao.CrudDemo
./mvnw -q -pl spring-jdbc test
```

**Using MySQL instead:** create an empty database, then set environment variables before running:

```bash
export DB_URL="jdbc:mysql://localhost:3306/springjdbc" DB_USERNAME=root DB_PASSWORD=your-password
# PowerShell: $env:DB_URL="jdbc:mysql://localhost:3306/springjdbc"; $env:DB_PASSWORD="your-password"
```

`schema.sql` drops and re-creates its three tables on every run, so only point it at a learning database. The tests always use H2, even if `DB_URL` is set.

## Topics

| # | Package | Run | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_datasource_and_jdbctemplate` | `JdbcTemplateDemo` | `DataSource` vs `JdbcTemplate`, `update()` vs `queryForObject()`, `?` placeholders |
| 02 | `topic02_crud_dao` | `CrudDemo` | The DAO pattern: create, read, update, delete behind an interface; `Optional` for "not found" |
| 03 | `topic03_row_mappers` | `RowMapperStylesDemo` | Four ways to turn rows into objects |
| 04 | `topic04_named_params_keys_batch` | `NamedParametersDemo` | `:named` parameters, database-generated ids (`KeyHolder`), `batchUpdate` |
| 05 | `topic05_transactions` | `TransactionsDemo` | `@Transactional`: all or nothing, with a money-transfer demo |

## Topic notes

### 01 DataSource and JdbcTemplate
- **Why:** plain JDBC means opening and closing connections, statements and result sets, plus catching `SQLException` around every query. `JdbcTemplate` does all of that for you.
- **How:** the `DataSource` says *where* connections come from, and `JdbcTemplate` runs SQL on it. `update()` returns the number of rows changed. Spring translates database errors into `DataAccessException`, for example `DuplicateKeyException`.
- **Mistake:** building SQL with `+`. Always use `?`, or you invite SQL injection.

### 02 CRUD with a DAO
- **Why:** callers should never see SQL. The DAO interface lets you swap in Hibernate (spring-orm) or Spring Data (stage 03) later.
- **How:** a `@Repository` gets its `JdbcTemplate` through the constructor. `findById` returns `Optional`: use `query(...)` and take the first element, because `queryForObject` throws `EmptyResultDataAccessException` when no row matches.
- **Exercise:** add `findByCity(String city)`.

### 03 Row mappers
- **Why:** a `ResultSet` is rows and columns, and your code wants objects.
- **How:** a `RowMapper` class (reusable), a lambda (it's a functional interface), `BeanPropertyRowMapper` (maps columns to setters by name), or `queryForList` (a `Map` per row, no class at all).
- **Mistake:** `rs.getInt(1)`. If the columns are reordered, values go into the wrong fields. Read columns by name.

### 04 Named parameters, keys and batches
- **Why:** ten `?` markers are hard to read, apps usually let the database generate ids, and inserting 1,000 rows one at a time is slow.
- **How:** `NamedParameterJdbcTemplate` with `:title`, `KeyHolder` for the generated id, and `batchUpdate` to send many rows in one round trip. `DataClassRowMapper` maps rows onto a **record** through its constructor.

### 05 Transactions
- **Why:** a transfer is two updates, and a crash between them must not create or destroy money.
- **How:** use `@EnableTransactionManagement` and a `DataSourceTransactionManager`. On a `@Transactional` method, a RuntimeException means rollback and a normal return means commit. The demo shows the total staying at 600 with the transaction, and jumping to 1600 without it.
- **Mistakes:** checked exceptions don't roll back unless you say `rollbackFor`. Self-invocation skips the transaction (spring-core topic13).

## Revision checklist
- [ ] What `JdbcTemplate` removes compared with plain JDBC.
- [ ] `update` vs `query` vs `queryForObject`, and what each returns or throws.
- [ ] Why `?` / `:name` placeholders prevent SQL injection.
- [ ] Four ways to map rows to objects.
- [ ] How to get a database-generated id back.
- [ ] What "atomic" means, and when `@Transactional` commits or rolls back.

## Status
✅ Working: all 5 demos run on H2 with nothing installed. `./mvnw -pl spring-jdbc test` passes (8 tests: CRUD, keys and batch, transaction rollback). MySQL mode is selected with `DB_URL`.
