# Spring ORM (Hibernate integration)

> Start of a Spring + Hibernate tutorial: a JPA-annotated `Student` entity and a DAO that saves through `HibernateTemplate`.

## What it teaches
- Mapping a class to a table with `@Entity`, `@Table`, `@Id`, `@Column`
- The DAO pattern on top of Spring's `HibernateTemplate`
- Setter injection as the way the template will be wired in

## Run it
Compile only for now (from this folder):

```bash
mvn compile
```

`App.java` just prints "Hello World!" and `src/main/resources/config.xml` is empty, so nothing talks to a database yet.

## Read the code in this order
1. `src/main/java/com/spring/orm/entities/Student.java` - entity mapping to `student_detail`
2. `src/main/java/com/spring/orm/dao/StudentDao.java` - `insert()` via `hibernateTemplate.save`
3. `src/main/resources/config.xml` - where the beans will go
4. `pom.xml` - why the Hibernate version is pinned (see notes)

## Revision notes
- The next tutorial step is to fill `config.xml` with: `DriverManagerDataSource` -> `LocalSessionFactoryBean` (dataSource, `hibernate.dialect`, `hbm2ddl.auto`, `annotatedClasses`) -> `HibernateTemplate` -> `HibernateTransactionManager` + `<tx:annotation-driven/>` -> `StudentDao` with `<property name="hibernateTemplate" ref="..."/>`. `../spring-mvc/src/main/webapp/WEB-INF/spring-servlet.xml` has a working example of that chain.
- `HibernateTemplate` write methods need a transaction: put `@Transactional` on `insert()`, otherwise Spring throws "Write operations are not allowed in read-only mode".
- Spring 6's `orm.hibernate5.HibernateTemplate` still uses Hibernate 5's old Criteria API, so it needs Hibernate 5. That's why the pom uses `hibernate-core-jakarta` 5.6.15 (the Jakarta build); Hibernate 6/7 cannot load `HibernateTemplate`.
- `HibernateTemplate` is legacy. Modern code injects `SessionFactory` or uses JPA `EntityManager` / Spring Data JPA (see `03-spring-boot/jpa-hibernate`).
- `save()` returns the generated id (`Serializable`); `persist()` returns nothing.

## Status
🚧 Partial - unfinished tutorial. Compiles, but `config.xml` is empty, `App` prints Hello World, and `StudentDao` is not wired yet (it now has the setter for injection). `AppTest` is the JUnit 3.8 archetype placeholder.
