# Currency Exchange Service

> Returns the exchange rate between two currencies from an H2 table (port 8000), registers with Eureka, and shows fault tolerance (staying up when something else breaks) with Resilience4j.

## Why it matters
This is the service that *other* services depend on. It shows what a service that gets called needs: a fixed name in the registry, several instances to share the load, and a way to tell which instance answered. Its demo endpoints also show the other side of the problem: what a service should do when **its own** dependency is failing.

## What it teaches
- A JPA entity + Spring Data repository over H2, filled by `data.sql`
- Registering with Eureka as `currency-exchange`; running 2 instances
- Returning the port of the instance that answered (`environment`), so load balancing can be seen
- Resilience4j `@Retry`, `@CircuitBreaker` and `@RateLimiter`, set up in properties
- Trace ids in every log line (Micrometer Tracing), so one request can be followed across services

## Run it
```bash
./mvnw spring-boot:run                                             # port 8000
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8001   # a second instance
```
```bash
curl localhost:8000/currency-exchange/from/USD/to/INR
# {"id":10001,"from":"USD","to":"INR","conversionMultiple":91,"environment":"8000"}
curl localhost:8000/currency-exchange/from/usd/to/inr     # same: input is upper-cased
curl -i localhost:8000/currency-exchange/from/USD/to/XYZ  # 404
```
Sample data: USD→INR 91, EUR→INR 113, AUD→INR 25. H2 console: http://localhost:8000/h2-console (JDBC URL `jdbc:h2:mem:testdb`).

### Fault tolerance demos
`/sample-api*` call a URL that fails on purpose, to act like a broken dependency.

| Endpoint | Pattern | What you see |
|---|---|---|
| `/sample-api` | `@Retry`: 5 attempts, 500 ms apart and the gap growing each time | 5 "attempt N" log lines, then `fallback-response (...)` |
| `/sample-api/circuit-breaker` | `@CircuitBreaker`: opens at 50 % failures over the last 10 calls (after at least 5), stays open 10 s | After 5 calls the log goes quiet: the fallback answers **without calling** the broken URL. `curl localhost:8000/actuator/circuitbreakers` shows `OPEN` |
| `/sample-api/rate-limited` | `@RateLimiter`: 2 calls per 10 s | `allowed`, `allowed`, then `too many requests - try again later` |

```bash
./mvnw test     # seed data + repository, the endpoint, the 404, the retry fallback (no Eureka or config server needed)
```

## Read the code in this order
1. `src/main/resources/application.properties`: port, H2, Eureka, Resilience4j settings, tracing
2. `src/main/resources/data.sql`: sample rows
3. `.../currency_exchange_service/CurrencyExchange.java`: the entity (`@Column` renames)
4. `.../currency_exchange_service/CurrencyExchangeRepository.java`: `findByFromAndTo`
5. `.../currency_exchange_service/ExchangeValue.java`: the response record (entity + `environment`)
6. `.../currency_exchange_service/CurrencyExchangeController.java`
7. `.../currency_exchange_service/CircuitBreakerController.java`: the three Resilience4j annotations
8. `src/test/.../CurrencyExchangeServiceApplicationTests.java` and `src/test/resources/application-test.properties`

## Revision notes
- `from` and `to` are **reserved SQL words** (SQL already uses them), so `create table ... (from varchar...)` fails. The entity maps them with `@Column(name="currency_from")` / `currency_to`.
- `spring.jpa.defer-datasource-initialization=true` runs `data.sql` **after** Hibernate creates the table.
- The response is a record (`ExchangeValue`), not the entity. `environment` is not a database column, and the API shape shouldn't change when the table does.
- `environment` = `local.server.port`, so you can see which instance answered.
- **Retry vs circuit breaker:** retry helps with *short* glitches. When a dependency is really down, retrying just multiplies the load on it. The circuit breaker stops calling it for a while and fails fast instead. Like the electric trip switch at home: it cuts off the power for a while, instead of letting the wire keep burning. The rate limiter protects *this* service from too many callers.
- A fallback method has the same parameters plus an exception, and returns a safe default answer.
- Test gotchas:
  - `src/test/resources/application.properties` would **replace** the main file completely, not merge with it. So the test settings are in `application-test.properties`, with `@ActiveProfiles("test")`.
  - The test profile turns off Eureka and the config import, so the tests run with nothing else started.

## Status
✅ **Working.** 4 tests pass. Checked with 2 instances behind the gateway. The course's `Url.txt` notes are now part of this README.
