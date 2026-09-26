# MovieShark: Movie Ticket Booking Backend (ShowTime variant)

> A near-copy of [`../showtime`](../showtime) with the same package (`com.gfg.showtime`), the same endpoints and the same Kafka + email flow. It is kept as a second practice run of the GeeksforGeeks JBDL booking project.

## How it differs from ShowTime
| | ShowTime | MovieShark |
| --- | --- | --- |
| Database | `showtime` | `movieshark` |
| `User.email` | `unique = true`; `getUsername()` returns the **email** | not unique; `getUsername()` returns the **name** |
| Swagger (springfox) | yes | no |
| `docker-compose.yml` for Kafka | yes | no; reuse `../showtime/docker-compose.yml` |
| Postman collection | `Showtime.postman_collection.json` | `MovieShark.postman_collection.json` (same requests) |

Everything else is the same (domain, services, security, design). For the concepts and the recommended reading order, see the [ShowTime README](../showtime/README.md). This file only covers what differs.

## What it teaches
- The same material as ShowTime: JPA booking domain, DTO mapping, Kafka-driven notifications, Spring Security filter chain
- A useful exercise: diff the two projects and ask *why* making the login name the email (unique) is the safer choice

## Run it
Prerequisites: JDK 17+, MySQL on `localhost:3306`, Kafka on `localhost:9092`.
```bash
(cd ../showtime && docker compose up -d)   # starts Kafka + Zookeeper
export DB_PASSWORD=...                      # DB_USERNAME defaults to root; MAIL_USERNAME / MAIL_PASSWORD optional
../../03-spring-boot/restful-web-services/mvnw spring-boot:run   # or: mvn spring-boot:run
```
- The app runs on `http://localhost:8080`. Import `MovieShark.postman_collection.json` and run the requests in order: addUser → addTheater → addMovie → addShow → addReview → bookTicket.

## Read the code in this order
1. `design.txt`: the same brief as ShowTime.
2. `src/main/java/com/gfg/showtime/domain/User.java`: compare it with ShowTime's version.
3. `src/main/java/com/gfg/showtime/service/TicketService.java`: the booking and Kafka publish.
4. `src/main/java/com/gfg/showtime/config/SecurityConfiguration.java`

## Revision notes
- Whatever `getUsername()` returns is what Spring Security uses as the login. If it isn't unique, two users can collide.
- Two projects in one repo sharing the package `com.gfg.showtime` is confusing. Give each project its own base package.
- All the fixes applied to ShowTime (the `searchShows` return, 404 advice, `loadUserByUsername` throwing, Lombok for JDK 21, env-var credentials) were applied here too.

## Status
✅ Compiles on JDK 21.
⚠️ **Security is not secure.** It uses `NoOpPasswordEncoder`, CSRF is disabled, and every route is `permitAll()`.
🚧 Spring Boot 2.7.3 is end-of-life. This is a duplicate of ShowTime; consider merging the differences into ShowTime and removing this folder.
