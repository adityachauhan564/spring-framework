# Naming Server (Eureka)

> The service registry (port 8761). Microservices register here and find each other by name.

## Why it matters
In a real system, instances start, stop and move, and there may be several of each. If service A has `http://10.0.0.7:8000` hard-coded for service B, every change to B breaks A. With a registry, B announces "I'm `currency-exchange`, at this address", and A asks for `currency-exchange` by name.

## What it teaches
- `@EnableEurekaServer`
- Service discovery: callers use a **service name** instead of host:port
- Why the registry itself doesn't register with anything

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test                     # starts the server on a random port and checks the registry is empty
```
Dashboard: http://localhost:8761. Instances appear as the other services start. The raw registry: `curl localhost:8761/eureka/apps` (XML; add `-H "Accept: application/json"` for JSON).

## Read the code in this order
1. `src/main/java/com/udemy/microservices/naming_server/NamingServerApplication.java`: `@EnableEurekaServer`
2. `src/main/resources/application.properties`: port and the standalone-server flags
3. `src/test/java/com/udemy/microservices/naming_server/NamingServerApplicationTests.java`

## Revision notes
- `eureka.client.register-with-eureka=false` and `fetch-registry=false`: a standalone server shouldn't register with itself.
- Clients point at it with `eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka`.
- `eureka.instance.prefer-ip-address=true` registers the IP instead of the hostname, which helps on laptops and in containers.
- Registration isn't instant: clients register, then send a heartbeat every 30 s, and callers refresh their cached copy of the registry every 30 s. Expect up to about a minute before a new instance gets traffic.
- The same delay works the other way: a crashed instance stays listed until its lease expires (90 s without heartbeats), and on a small local setup Eureka's *self-preservation* mode can keep it even longer. Callers may keep trying it meanwhile.
- The dashboard shows names in uppercase (`CURRENCY-EXCHANGE`), but lookups ignore case: `lb://currency-exchange` works.

## Status
✅ **Working.** 2 tests pass.
