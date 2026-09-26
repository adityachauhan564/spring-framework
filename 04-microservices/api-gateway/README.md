# API Gateway

> A Spring Cloud Gateway (port 8765) that routes requests to the microservices through Eureka and logs every request.

## What it teaches
- Declaring routes in Java with `RouteLocatorBuilder`
- `lb://SERVICE-NAME` URIs: discovery plus client-side load balancing through Eureka
- Route filters: `addRequestHeader`, `addRequestParameter` and `rewritePath`
- A `GlobalFilter` that runs for every request (`LoggingFilter`)

## Run it
Start [naming-server](../naming-server) first, then:
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run

curl localhost:8765/get                                     # proxied to httpbin.org, shows the added header and param
curl localhost:8765/currency-exchange/from/USD/to/INR       # -> CURRENCY-EXCHANGE (see its Status)
```

| Route path | Target |
|---|---|
| `/get` | `http://httpbin.org:80` (adds `MyHeader` and `Param`) |
| `/currency-exchange/**` | `lb://CURRENCY-EXCHANGE` |
| `/currency-conversion/**` | `lb://CURRENCY-CONVERSION-SERVICE` |
| `/currency-conversion-feign/**` | `lb://CURRENCY-CONVERSION-SERVICE` |
| `/currency-conversion-new/**` | rewritten to `/currency-conversion-feign/**` on `lb://CURRENCY-CONVERSION-SERVICE` |

## Read the code in this order
1. `src/main/resources/application.properties`: port and Eureka URL
2. `src/main/java/com/udemy/microservices/api_gateway/ApiGatewayConfiguration.java`: all the routes
3. `src/main/java/com/udemy/microservices/api_gateway/LoggingFilter.java`: global filter

## Revision notes
- This gateway is **reactive** (WebFlux, `spring-cloud-starter-gateway-server-webflux`), so filters return `Mono<Void>`.
- `lb://` needs a discovery client (Eureka) on the classpath. The name must match the registered `spring.application.name`.
- There are two ways to route: explicit Java routes (used here) or `spring.cloud.gateway.discovery.locator.enabled=true` for automatic `/{service-id}/**` routes. The latter is commented out here.
- `rewritePath` with a named group `(?<segment>.*)` → `${segment}` lets you rename a public path without changing the backend.
- Use a `GlobalFilter` for cross-cutting concerns such as logging, auth or correlation IDs.

## Status
✅ **Working.** Compiles (Boot 4.0.2 / Spring Cloud 2025.1.0). The currency routes only return data once the currency services have controllers; see their READMEs.
