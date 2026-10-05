# Naming Server (Eureka)

> The service registry (port 8761). Microservices register here, and find each other by name.

## Why it matters
In a real system, instances start, stop and move, and there may be several of each. If service A has `http://10.0.0.7:8000` hard-coded for service B, every change to B breaks A. With a registry, B announces "I'm `currency-exchange`, at this address", and A asks for `currency-exchange` by name. Like a phone contact list: you call "Mummy", not a number you must remember.

## What it teaches
- `@EnableEurekaServer`
- Service discovery: callers use a **service name** instead of host:port
- Why the registry itself doesn't register anywhere

## Run it
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
./mvnw test                     # starts the server on a random port and checks the registry is empty
```
Dashboard: http://localhost:8761. Instances show up as the other services start. The raw registry: `curl localhost:8761/eureka/apps` (XML; add `-H "Accept: application/json"` for JSON).

## Read the code in this order
1. `src/main/java/com/udemy/microservices/naming_server/NamingServerApplication.java`: `@EnableEurekaServer`
2. `src/main/resources/application.properties`: port and the flags for a standalone server
3. `src/test/java/com/udemy/microservices/naming_server/NamingServerApplicationTests.java`

## Revision notes
- `eureka.client.register-with-eureka=false` and `fetch-registry=false`: a standalone server (the only one) shouldn't register with itself.
- Clients point at it with `eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka`.
- `eureka.instance.prefer-ip-address=true` registers the IP address instead of the hostname. This helps on laptops and in containers.
- Registration is not instant. Clients register, then send a heartbeat ("I'm still alive") every 30 s, and callers refresh their saved copy of the registry every 30 s. Expect up to about a minute before a new instance gets any traffic.
- The same delay works the other way too. A crashed instance stays listed until its lease expires (90 s without heartbeats), and on a small local setup Eureka's *self-preservation* mode can keep it even longer. Callers may keep trying it in the meantime.
- The dashboard shows names in capital letters (`CURRENCY-EXCHANGE`), but lookups ignore case: `lb://currency-exchange` works.

## Status
✅ **Working.** 2 tests pass.
