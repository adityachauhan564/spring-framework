# Currency Conversion Service

> Meant to convert a quantity between currencies by calling currency-exchange-service through a Feign client (port 8100). **Only the skeleton is in this repo.**

## What it teaches (once implemented)
- Calling another microservice with **OpenFeign** (`@FeignClient`) instead of `RestTemplate`
- Discovering the target through Eureka, so there's no hardcoded URL
- Composing responses: `totalCalculatedAmount = quantity × conversionMultiple`

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```
Target URLs (the course's paths; `Url.txt` in the exchange service lists this one with port 8000, but this service runs on **8100**):
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10 (RestTemplate version)
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10 (Feign version)
- Through the gateway: http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

## Read the code in this order
1. `src/main/resources/application.properties`: port 8100, config-server import, Eureka
2. `pom.xml`: `spring-cloud-starter-openfeign` and the eureka client
3. Reference entity and Feign proxy: `../../reference/in28minutes-spring-microservices-v3/03.microservices/currency-conversion-service/src/main/java/com/in28minutes/microservices/currencyconversionservice/` (`CurrencyConversion`, `CurrencyExchangeProxy`)
4. Reference controller: `../../reference/in28minutes-spring-microservices-v3/04.docker/currency-conversion-service/src/main/java/com/in28minutes/microservices/currencyconversionservice/CurrencyConversionController.java`

## Revision notes
- Feign needs `@EnableFeignClients` on the application class plus an interface annotated `@FeignClient(name="currency-exchange")`.
- The Feign `name` must match the target's `spring.application.name` (`currency-exchange`) for Eureka lookup.
- Feign interface methods reuse Spring MVC annotations (`@GetMapping`, `@PathVariable`), and the path must match the target controller.
- With Eureka on the classpath, Feign load-balances across all registered `currency-exchange` instances.
- Compared with `RestTemplate`: Feign is declarative, removes the URL-building boilerplate and is easier to mock.

## Status
📝 **Skeleton.** Only the generated `CurrencyConversionServiceApplication` and its test, plus the properties, are committed. The `CurrencyConversion` bean, `CurrencyExchangeProxy` Feign client and controller were never committed, and `@EnableFeignClients` isn't added yet. Compiles (Boot 4.0.2 / Spring Cloud 2025.1.0). Note that the reference `03.microservices` copy has no controller file; use the `04.docker` one.
