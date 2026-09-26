# Naming Server (Eureka)

> The service registry (port 8761). Microservices register here and look each other up by name.

## What it teaches
- `@EnableEurekaServer`
- Service discovery: callers use a **service name** instead of hardcoded host:port
- Why the registry itself doesn't register with anything

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
```
Dashboard: http://localhost:8761. Instances appear here once the other services start.

## Read the code in this order
1. `src/main/java/com/udemy/microservices/naming_server/NamingServerApplication.java`: `@EnableEurekaServer`
2. `src/main/resources/application.properties`: port and the standalone-server flags

## Revision notes
- `eureka.client.register-with-eureka=false` and `fetch-registry=false`: a standalone server shouldn't register with itself.
- Clients point at it with `eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka`.
- `eureka.instance.prefer-ip-address=true` registers the IP instead of the hostname, which helps on laptops and in containers.
- The gateway's `lb://SERVICE-NAME` URIs are resolved through this registry and load-balanced across instances.
- Registered names are uppercase in the dashboard (`CURRENCY-EXCHANGE`) and come from `spring.application.name`.

## Status
✅ **Working.** Compiles (Boot 4.0.2 / Spring Cloud 2025.1.0).
