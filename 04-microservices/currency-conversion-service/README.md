# Currency Conversion Service

> Converts a quantity between currencies (port 8100) by calling currency-exchange-service, in two ways: **OpenFeign** and **RestClient**.

## Why it matters
Microservices constantly call each other. That call has to find the target (no hard-coded host), spread the load across its instances, and cope when it's down. This service shows the declarative way (Feign: write an interface) and the explicit way (RestClient: build the request), both going through Eureka.

## What it teaches
- A Feign client: `@EnableFeignClients` + `@FeignClient(name="currency-exchange")`
- A load-balanced `RestClient` (the replacement for the course's `RestTemplate`)
- Turning downstream failures into clean HTTP errors: 404 and 503 `ProblemDetail`
- Trace ids passed along to the exchange service

## Run it
Needs [naming-server](../naming-server) and [currency-exchange-service](../currency-exchange-service) running (or use `../start-all.sh`).
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run

curl localhost:8100/currency-conversion/from/USD/to/INR/quantity/10        # RestClient
curl localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10  # Feign
# {"id":10001,"from":"USD","to":"INR","quantity":10,"conversionMultiple":91,
#  "totalCalculatedAmount":910,"environment":"8000 feign"}
```
Call it a few times: `environment` switches between 8000 and 8001 when two exchange instances run. Unknown pair → 404; exchange service down → 503.

```bash
./mvnw test     # the Feign proxy is mocked: the calculation, the 404 and the 503 (nothing else needs to run)
```

## Read the code in this order
1. `src/main/resources/application.properties`: port 8100, config import, Eureka, tracing
2. `.../currency_conversion_service/CurrencyConversionServiceApplication.java`: `@EnableFeignClients`
3. `.../currency_conversion_service/ExchangeValue.java` and `CurrencyConversion.java`: the records
4. `.../currency_conversion_service/CurrencyExchangeProxy.java`: the Feign interface
5. `.../currency_conversion_service/RestClientConfig.java`: the load-balanced RestClient
6. `.../currency_conversion_service/CurrencyConversionController.java`: both endpoints
7. `.../currency_conversion_service/ConversionExceptionHandler.java`: 404 / 503

## Revision notes
- **Feign vs RestClient:** Feign = an interface with Spring MVC annotations, Spring writes the HTTP code; easy to mock in tests. RestClient = you build every request, which gives full control. `RestTemplate` still works but is in maintenance mode.
- The Feign `name` (and the RestClient base URL `http://currency-exchange`) must match the target's `spring.application.name`. It is looked up in Eureka, not DNS.
- Both are load-balanced by Spring Cloud LoadBalancer: Feign automatically, RestClient through the `LoadBalancerInterceptor`.
- **Gotcha (cost me a debugging session):** don't declare a `@LoadBalanced RestClient.Builder` **bean**. The Eureka client uses the application's `RestClient.Builder` bean for its own calls to `localhost:8761`; a load-balanced one tries to look up "localhost" as a service name, and the service never registers. `RestClientConfig` adds the interceptor to one `RestClient` instead.
- This service has its **own copy** of `ExchangeValue`. Services share an HTTP contract, not Java classes, so each can be released on its own.
- Tracing across Feign needs `feign-micrometer`; RestClient built from Boot's builder is traced automatically.
- Boot 4: `RestClient.Builder` comes from `spring-boot-starter-restclient`.

## Status
✅ **Working.** 3 tests pass; both call styles were checked with 2 exchange instances, directly and through the gateway.
