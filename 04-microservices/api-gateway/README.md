# API Gateway

> A Spring Cloud Gateway (port 8765): the single entry point that routes requests to the microservices through Eureka, and logs every request.

## Why it matters
Without a gateway, every client has to know every service's address, and every service has to repeat the same cross-cutting work (logging, auth, rate limits). The gateway gives clients **one address**, hides how the services are split, and is the one place for those shared concerns.

## What it teaches
- Declaring routes in Java with `RouteLocatorBuilder`
- `lb://service-name` URIs: discovery plus load balancing through Eureka
- Route filters: `addRequestHeader`, `addRequestParameter` and `rewritePath`
- A `GlobalFilter` that runs for every request (`LoggingFilter`)
- Starting the trace that follows a request through every service

## Run it
Start [naming-server](../naming-server) and the services first (or use `../start-all.sh`), then:
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run

curl localhost:8765/get                                                   # proxied to httpbin.org: shows the added header and param
curl localhost:8765/currency-exchange/from/USD/to/INR                     # run twice: 8000, then 8001
curl localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10   # rewritten to the Feign endpoint
```

| Route id | Path | Target |
|---|---|---|
| `httpbin-demo` | `/get` | `http://httpbin.org:80` (adds `MyHeader` and `Param`) |
| `currency-exchange` | `/currency-exchange/**` | `lb://currency-exchange` |
| `currency-conversion` | `/currency-conversion/**` | `lb://currency-conversion-service` |
| `currency-conversion-feign` | `/currency-conversion-feign/**` | `lb://currency-conversion-service` |
| `currency-conversion-new` | `/currency-conversion-new/**` | rewritten to `/currency-conversion-feign/**` on `lb://currency-conversion-service` |

```bash
./mvnw test     # every route id points at the right target (Eureka disabled)
```

## Read the code in this order
1. `src/main/resources/application.properties`: port, Eureka, tracing, the commented discovery locator
2. `src/main/java/com/udemy/microservices/api_gateway/ApiGatewayConfiguration.java`: all the routes
3. `src/main/java/com/udemy/microservices/api_gateway/LoggingFilter.java`: global filter
4. `src/test/java/com/udemy/microservices/api_gateway/ApiGatewayApplicationTests.java`

## Revision notes
- This gateway is **reactive** (WebFlux, `spring-cloud-starter-gateway-server-webflux`), so filters return `Mono<Void>`.
- `lb://` needs a discovery client (Eureka) on the classpath; the name is the target's `spring.application.name` (case doesn't matter).
- Two ways to route: explicit Java routes (used here), or `spring.cloud.gateway.server.webflux.discovery.locator.enabled=true` for automatic `/{service-id}/**` routes (commented out in the properties).
- `rewritePath` with a named group `(?<segment>.*)` → `${segment}` renames a public path without changing the backend.
- Give every route an **id**: it names the route in logs and `/actuator/gateway`, and lets the test check routes by name.
- Reactive code switches threads, so the trace id isn't in the log context automatically: `spring.reactor.context-propagation=auto` fixes that for `LoggingFilter`'s log lines.
- **Known behaviour:** when every exchange instance has crashed, the gateway returns **500**, not 503. Eureka still lists the dead instances (see the naming-server README), so the gateway tries one, gets "connection refused", and reports it as an internal error. Once Eureka drops them, you get 503 "no instances available". The conversion service handles the same situation itself and answers 503. Adding a gateway `CircuitBreaker` filter with a fallback is the usual fix.

## Status
✅ **Working.** 1 test passes; all routes were checked with the whole system running.
