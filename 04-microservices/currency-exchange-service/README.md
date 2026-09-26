# Currency Exchange Service

> Meant to return the exchange rate between two currencies from an H2 table (port 8000). **Only the skeleton is in this repo.**

## What it teaches (once implemented)
- A JPA entity plus Spring Data repository over H2, seeded by `data.sql`
- Registering with Eureka as `currency-exchange`
- Returning the serving instance's port (`environment`) to demonstrate load balancing
- Resilience4j (circuit breaker / retry): the dependency is present

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```
Target URL (from `Url.txt`): http://localhost:8000/currency-exchange/from/USD/to/INR

Expected response shape:
```json
{"id":10002,"from":"USD","to":"INR","conversionMultiple":91,"environment":"8000"}
```

## Read the code in this order
1. `src/main/resources/application.properties`: port, H2, config-server import, Eureka
2. `src/main/resources/data.sql`: seed rows (USD, EUR and AUD to INR)
3. `Url.txt`: target URLs and a note on the `from`/`to` SQL keyword error
4. The full reference implementation: `../../reference/in28minutes-spring-microservices-v3/03.microservices/currency-exchange-service/src/main/java/com/in28minutes/microservices/currencyexchangeservice/` (`CurrencyExchange`, `CurrencyExchangeRepository`, `CurrencyExchangeController`, `CircuitBreakerController`)

## Revision notes
- `from` and `to` are **reserved SQL words**. Map them with `@Column(name="currency_from")` and `currency_to`, which matches `data.sql`. `Url.txt` records the DDL error this avoids.
- `spring.jpa.defer-datasource-initialization=true` makes `data.sql` run **after** Hibernate creates the tables.
- Use `Environment.getProperty("local.server.port")` for the `environment` field, so you can see which instance answered through the gateway.
- Start two instances (`-Dserver.port=8001`) and call through the gateway to watch Eureka load-balancing.
- The Boot 4 H2 console needs the `spring-boot-h2console` module. It's commented out in `pom.xml`, so `/h2-console` won't work until you add it back.

## Status
📝 **Skeleton.** Only the generated `CurrencyExchangeServiceApplication` and its test, plus the properties and `data.sql`, are committed. The entity, repository and controller were never committed. **`data.sql` inserts into `CURRENCY_EXCHANGE`, which only exists once the entity is added, so the app likely fails at startup until then.** Compiles (Boot 4.0.2 / Spring Cloud 2025.1.0). Copy the four classes from the reference path above to finish it.
