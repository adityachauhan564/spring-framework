# 05 — Real-World Backend Applications

> Three complete applications that combine what stages 01-03 taught: REST, JPA relationships, validation, error handling, security, transactions, caching, messaging, and one app built with no framework at all.

**Before this:** [03-spring-boot](../03-spring-boot). Stage [04-microservices](../04-microservices) doesn't depend on this one, and the other way round. Each app here is a single service.

## Why this stage
Tutorials show one feature at a time. Real applications have to combine them, and handle what goes wrong:
- a book that can't be deleted, because someone has borrowed it;
- two customers booking the same seat;
- an email sent for a booking that was then rolled back (cancelled before it was saved), which must not happen;
- data that has to survive a restart.

Each project here is small enough to read in an afternoon, and shows one more layer than the one before.

## Run everything (nothing to install except JDK 21)
Each project is its own build with its own wrapper, and each one starts on in-memory H2 with sample data:

| # | Project | Build | Run | Test |
|---|---|---|---|---|
| 1 | [digital-library](./digital-library) | Gradle | `./gradlew bootRun` | `./gradlew test` (17) |
| 2 | [showtime](./showtime) | Maven | `./mvnw spring-boot:run` | `./mvnw test` (17) |
| 3 | [irctc-ticket-booking](./irctc-ticket-booking) | Gradle | `./gradlew run -q --console=plain` | `./gradlew test` (6) |

Optional real infrastructure, through Docker and a Spring profile:
- **Redis** for digital-library (`redis` profile);
- **Kafka** and **Mailpit** for showtime (`kafka,mail` profiles);
- **MySQL** for both (`mysql` profile, with the username and password from `DB_USERNAME` / `DB_PASSWORD`).

Each README has the exact commands.

## What each project adds

| | digital-library | showtime | irctc-ticket-booking |
|---|---|---|---|
| Domain | authors, books, members, lending | movies, theatres, shows, seats, tickets | trains, seats, bookings |
| Data | JPA: `@OneToMany`, `@ManyToMany` + join table | JPA, a richer model, DTO records | JSON files + Jackson |
| Rules | 409 when a book is still issued | `@Transactional` booking, `@Version` against double booking | checked by hand, no transaction |
| Security | none | BCrypt, roles, HTTP Basic, owner-only tickets | BCrypt by hand |
| Beyond the DB | `@Cacheable`: in memory or Redis; Redis data structures | after-commit events, Kafka, email (Mailpit) | classpath resources, safe file writes |
| Tests | Mockito, `@DataJpaTest`, MockMvc, cache | MockMvc + security, many bookings at once, `@EmbeddedKafka` | JUnit 5 `@TempDir` |

## Suggested study order
1. **digital-library**: the cleanest layered REST + JPA example. Learn relationships, error handling and caching here.
2. **showtime**: the same layers on a richer domain, plus security, transactions, safe booking when many people book at once, and Kafka.
3. **irctc-ticket-booking**: build the same ideas again without Spring, to see what the framework was doing for you.

`movieshark` was removed: it was an older copy of showtime with a login bug.

## Quick revision checklist
- [ ] Which side owns a JPA relationship, and where does the foreign key or join table end up?
- [ ] Why do Author → Book → Author loops break JSON and `toString`, and what are two ways to stop them?
- [ ] Why does a lazy collection need an open transaction, and what does `open-in-view=false` change?
- [ ] `@Cacheable` / `@CachePut` / `@CacheEvict`: which keys must match, and why do the annotations belong on the service?
- [ ] Why is JSON a safer Redis format than JDK serialization?
- [ ] 401 vs 403. Why must the booking user come from the login, not from the request body?
- [ ] Why BCrypt, and why must `loadUserByUsername` throw instead of returning `null`?
- [ ] How does `@Version` stop two people from booking the same seat? How is that different from a pessimistic lock?
- [ ] Why send the notification from a `@TransactionalEventListener` and not inside the booking?
- [ ] What does Kafka add over calling the email code directly (async, durable, consumer groups)?
- [ ] Why read shipped data from the classpath, but write to a separate folder?
- [ ] What goes wrong without a transaction when one action writes two files?
