# Spring ORM: Hibernate and JPA

> Stop writing SQL for CRUD: map a Java class to a table and let Hibernate generate the SQL. Then learn how the Session really behaves, and finish with the standard JPA API that Spring Data is built on.

**Before this:** [spring-jdbc](../spring-jdbc/). You wrote the SQL there, and here Hibernate writes it, so the difference is easy to see.

## Run it
It uses in-memory H2 by default, the same as spring-jdbc. Hibernate creates the tables from your entities at startup.

```bash
./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic03_hibernate_crud_and_hql.HibernateCrudDemo
./mvnw -q -pl spring-orm compile exec:java -Dexec.mainClass=com.spring.orm.topic03_hibernate_crud_and_hql.HibernateCrudDemo -Dshow.sql=true   # see the generated SQL
./mvnw -q -pl spring-orm test
```

For MySQL, set `DB_URL=jdbc:mysql://localhost:3306/springorm?createDatabaseIfNotExist=true`, plus `DB_USERNAME` / `DB_PASSWORD`.

## Topics

| # | Package | Run | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_entity_mapping` | `EntityMappingDemo` | `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, and the table Hibernate generates |
| 02 | `topic02_session_factory_config` | `SessionFactoryDemo` | The DataSource → SessionFactory → TransactionManager chain, in Java and in XML |
| 03 | `topic03_hibernate_crud_and_hql` | `HibernateCrudDemo` | CRUD with `getCurrentSession()`, and HQL queries |
| 04 | `topic04_entity_states_and_session` | `EntityStatesDemo` | Transient / persistent / detached, dirty checking, the first-level cache, `merge()` |
| 05 | `topic05_jpa_entity_manager` | `JpaDemo` | JPA: `EntityManagerFactory`, `EntityManager`, JPQL |

## Topic notes

### 01 Entity mapping
- **Why:** mapping rows to objects by hand (spring-jdbc topic03) gets repetitive. An ORM does it from annotations.
- **How:** `@Entity` marks a class to store, `@Id` is the primary key, and `@GeneratedValue(IDENTITY)` lets the database pick ids. `@Column` sets the name and constraints. JPA needs a no-argument constructor, which can be `protected`.

### 02 Wiring Hibernate into Spring
- **Why:** Hibernate needs connections, your entity classes and transaction handling, and Spring provides all three.
- **How:** `LocalSessionFactoryBean` (with `packagesToScan`) builds the `SessionFactory`. `HibernateTransactionManager` makes `@Transactional` work. `hibernate-config.xml` is the same chain in XML; it was empty in the original tutorial.
- **Note:** `hbm2ddl.auto=create-drop` is for learning only. Real apps use `validate` plus a migration tool such as Flyway.

### 03 CRUD and HQL
- **Why:** it's the everyday work: save, find, update, delete, query.
- **How:** inject the `SessionFactory`, and inside `@Transactional` methods call `getCurrentSession()`. HQL uses **class and field names** (`from Student s where s.studentCity = :city`), not table names.
- **Mistake:** calling `getCurrentSession()` outside a transaction throws "No current session".

### 04 Entity states and the Session
- **Why:** it explains the two classic surprises: "my change saved without `update()`" and "my change didn't save".
- **How:**
  - **Persistent** objects (attached to an open Session) are saved automatically at commit, through dirty checking, and the same id returns the same object with one SELECT (the first-level cache).
  - **Detached** objects (their Session has closed) are ignored until you call `merge()`.
- **Exercise:** call `session.detach(...)` before a change and predict the result.

### 05 JPA and EntityManager
- **Why:** JPA is the standard API, and Hibernate is one implementation of it. Spring Data JPA and Spring Boot (stage 03) are built on JPA.
- **How:** the names map one to one: SessionFactory is the `EntityManagerFactory` (`LocalContainerEntityManagerFactoryBean`), Session is the `EntityManager` (injected with `@PersistenceContext`), HQL is JPQL, and the transaction manager is `JpaTransactionManager`.

### Legacy note: `HibernateTemplate`
The original tutorial saved through Spring's `HibernateTemplate`:

```java
private HibernateTemplate hibernateTemplate;          // setter-injected
@Transactional
public int insert(Student s) { return (int) hibernateTemplate.save(s); }
```

You'll still see this in older code. It's legacy: it predates `getCurrentSession()` and JPA. It **cannot even be created on Hibernate 6** because it refers to the removed Criteria API (`org.hibernate.criterion`), so this project uses the topic03 and topic05 style instead.

## Revision checklist
- [ ] What each mapping annotation does.
- [ ] The Spring + Hibernate bean chain, and what each bean is for.
- [ ] HQL/JPQL vs SQL: which names do they use?
- [ ] Transient vs persistent vs detached, and when changes are saved.
- [ ] What the first-level cache is, and how long it lives.
- [ ] How SessionFactory/Session map onto JPA's EntityManagerFactory/EntityManager.

## Status
✅ Working: all 5 demos run on Hibernate 6.6 with H2, and `./mvnw -pl spring-orm test` passes (5 tests). This tutorial was unfinished before: `config.xml` was empty and `App` printed "Hello World".
