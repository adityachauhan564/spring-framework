# ShowTime: Movie Ticket Booking Backend

> A Spring Boot backend for movies, theatres, shows, reviews and ticket booking. It sends booking notifications through Kafka and email. Built during the GeeksforGeeks JBDL course (package `com.gfg.showtime`).

## What it teaches
- Modelling a booking domain with JPA: Movie → Shows → ShowSeats, Theater → TheaterSeats, User → Tickets
- Keeping API shape separate from entities: request/response `resource/*Resource` classes vs `domain/*` entities
- Event-driven side effects: `TicketService` publishes to the Kafka topic `TICKET_BOOKED`, and `NotificationConsumer` (`@KafkaListener`) sends the email
- Spring Security 5.7+ style: `SecurityFilterChain` and `PasswordEncoder` beans with a custom `UserDetailsService` / `AuthenticationProvider`, no `WebSecurityConfigurerAdapter`
- Swagger docs with springfox and validation with `javax.validation`

## Run it
Prerequisites: JDK 17+ (builds on JDK 21), MySQL on `localhost:3306`, Docker for Kafka.

```bash
docker compose up -d                  # Zookeeper + Kafka on localhost:9092 (docker-compose.yml)

# Windows PowerShell: $env:DB_PASSWORD="..."   bash: export DB_PASSWORD=...
# DB_USERNAME defaults to root. MAIL_USERNAME / MAIL_PASSWORD are optional (Gmail SMTP, app password).
../../03-spring-boot/restful-web-services/mvnw spring-boot:run   # this project has no own wrapper; any mvnw or mvn works
```
- The app runs on `http://localhost:8080`. The `showtime` database is created automatically (`createDatabaseIfNotExist=true`).
- Swagger UI: `http://localhost:8080/swagger-ui/`
- Postman: import `Showtime.postman_collection.json` and run the requests in order: addUser → addTheater → addMovie → addShow → addReview → bookTicket → the get requests.

Main endpoints:
- Users: `POST /user/signup`, `GET /user/{id}`
- Theaters: `POST /theater/add`, `GET /theater/{id}`
- Movies: `POST /movie/add`, `GET /movie/{id}`, `GET /movie/title?title=`
- Shows: `POST /show/add`, `GET /show/search?city=&movieName=&theaterName=`
- Reviews: `POST /review/add`, `GET /review/find?reviewId=`
- Tickets: `POST /ticket/book`, `GET /ticket/{id}`

## Read the code in this order
1. `design.txt`: requirements, entities and relationships.
2. `src/main/java/com/gfg/showtime/domain/`: the entities; start with `Show`, `ShowSeat` and `Ticket`.
3. `.../resource/`: the DTOs. Each entity has a `toResource()` method that maps it to its DTO.
4. `.../service/TicketService.java`: the heart of the app (seat checks, booking, Kafka publish).
5. `.../consumer/NotificationConsumer.java` → `service/NotificationService.java`: the async email path.
6. `.../config/SecurityConfiguration.java`, `MyAuthorityProvider.java`, `service/UserAuthService.java`: security wiring.
7. `.../exception/NotFoundAdvice.java`: how not-found errors become HTTP 404.

## Revision notes
- `UserDetailsService.loadUserByUsername` must **throw** `UsernameNotFoundException`; it must never return `null`.
- Without a `@RestControllerAdvice` (or `@ResponseStatus`), a custom `NotFoundException` reaches the client as a 500.
- A bare `new ArrayList<>();` without `return` is a silent bug. The compiler accepts it, and in `searchShows` the city check did nothing until this was fixed.
- Kafka decouples booking from notification. The HTTP call returns right away, and email failures don't roll back the booking.
- Spring Boot auto-configures `JavaMailSender` from `spring.mail.*`. Never hardcode credentials in a `@Bean`.
- Boot 2.7 still uses `javax.*`; Boot 3+ moved to `jakarta.*`. That rename is the main work in an upgrade.
- springfox needs `spring.mvc.pathmatch.matching-strategy=ant_path_matcher` on Boot 2.6+.

## Status
✅ Compiles on JDK 21. Lombok was pinned to 1.18.36 because Boot 2.7's default version breaks on JDK 21.
⚠️ **Security is learning-grade, not secure.** It uses `NoOpPasswordEncoder` (plain-text passwords), CSRF is disabled, and `/**` is `permitAll()`.
🚧 Known issues:
- Spring Boot 2.7.3 is end-of-life, and springfox 3.0.0 is abandoned.
- The "top 5 movies by genre" requirement in `design.txt` is not implemented.
- Kafka must be running before you book a ticket.
- There is a login mismatch. `User.getUsername()` returns the **email**, but `UserAuthService.loadUserByUsername` looks the user up **by name** (`findByName`). Pick one and use it in both places.
- See [`../movieshark`](../movieshark), a near-copy of this project.
