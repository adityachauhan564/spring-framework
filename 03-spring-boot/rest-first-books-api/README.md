# Rest First — Books API

> My first Spring Boot REST API: a small Book API backed by MySQL through Spring Data JPA.

## What it teaches
- `@RestController` with `@GetMapping` / `@PostMapping`
- The layers: Controller → Service → Repository → Entity
- Spring Data `CrudRepository`: no DAO code needed for `findAll()` / `save()`
- JPA entity mapping: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`
- Configuring a datasource and Hibernate DDL (`ddl-auto=update`) in `application.properties`

## Run it
Needs a running **MySQL** with a database named `youtube_springboot_api`. Hibernate creates the `books` table.

```bash
export DB_PASSWORD=your_mysql_password      # DB_USERNAME defaults to root
./mvnw spring-boot:run                      # Windows: set DB_PASSWORD=... && mvnw.cmd spring-boot:run
```
The app runs on port 8080.

```bash
curl localhost:8080/books
curl -X POST localhost:8080/books -H "Content-Type: application/json" \
     -d '{"title":"Head First Java","author":"Kathy Sierra"}'
```

## Read the code in this order
1. `src/main/resources/application.properties`: DB connection and Hibernate settings
2. `src/main/java/com/api/book/Rest_first/entities/Book.java`: the table mapping
3. `src/main/java/com/api/book/Rest_first/dao/BookRepository.java`: Spring Data interface
4. `src/main/java/com/api/book/Rest_first/services/BookService.java`: business layer
5. `src/main/java/com/api/book/Rest_first/controllers/BookController.java`: HTTP endpoints

## Revision notes
- `@RestController` = `@Controller` + `@ResponseBody`, so return values are written as JSON by Jackson.
- `@RequestBody` turns the JSON body into a `Book`. Jackson needs the **no-arg constructor** and setters.
- `CrudRepository<Book, Integer>` gives you `save`, `findAll`, `findById`, `deleteById` and more for free.
- `BookRepository.findById(int)` *overloads* the inherited `findById(Integer)`, which returns `Optional`. That's confusing: prefer the inherited method.
- `ddl-auto=update` is handy while learning; production uses migrations (Flyway or Liquibase) instead.
- `@Column(name="book_title")` maps the Java field `title` to a different column name.
- `@Component` on `BookService` works, but `@Service` states the intent more clearly.
- Credentials come from env vars (`${DB_PASSWORD}`). Never commit real passwords.

## Status
🚧 **Partial.** GET all books and POST a book work. GET-by-id is commented out in the controller and service, and PUT/DELETE aren't written yet. Compiles; running it needs MySQL.
