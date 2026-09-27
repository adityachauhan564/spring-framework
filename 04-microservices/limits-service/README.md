# Limits Service

> A microservice (port 8080) that reads its `minimum`/`maximum` limits from the **Spring Cloud Config Server**, per profile, and can reload them without a restart.

## Why it matters
This is the *client* side of central config. The service ships with safe local defaults, but the real values come from the config server, so an operator can change them in one place, for one environment, without rebuilding the service.

## What it teaches
- Binding typed config with `@ConfigurationProperties("limits-service")`
- Pulling config at startup with `spring.config.import=optional:configserver:...`
- Profile-specific config (`dev`, `qa`) served by the config server
- Local fallback values when the config server is down (`optional:`)
- Reloading config at runtime with `POST /actuator/refresh`

## Run it
Start [spring-cloud-config-server](../spring-cloud-config-server) first (optional, but it's the point of this project).
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
curl localhost:8080/limits
```

| Situation | Response |
|---|---|
| Config server running, profile `dev` (the default here) | `{"minimum":5,"maximum":995}` from `limit-service-microservices-dev.properties` |
| Config server running, `--spring.profiles.active=qa` | `{"minimum":6,"maximum":994}` |
| Config server down | `{"minimum":3,"maximum":997}` from the local `application.properties` |

**Reload without a restart:** change `maximum` in `../git-local-config-repo/limit-service-microservices-dev.properties`, then
```bash
curl -X POST localhost:8080/actuator/refresh     # ["limits-service.maximum"]
curl localhost:8080/limits                       # the new value
```

```bash
./mvnw test     # the fallback values, and that /actuator/refresh is exposed
```

## Read the code in this order
1. `src/main/resources/application.properties`: app name, config import, profile, fallbacks, refresh endpoint
2. `src/main/java/com/example/udemy/limit_service_microservices/configuration/LimitsProperties.java`: `@ConfigurationProperties`
3. `src/main/java/com/example/udemy/limit_service_microservices/bean/Limits.java`: the response record
4. `src/main/java/com/example/udemy/limit_service_microservices/controller/LimitsController.java`: `/limits`
5. `../git-local-config-repo/`: where the real values live

## Revision notes
- The config server finds files by `spring.application.name`: `limit-service-microservices` → `limit-service-microservices[-profile].properties`.
- Values from the config server **override** the local `application.properties`.
- `optional:configserver:` = start with local values if the server is unreachable. Without `optional:`, startup fails.
- `/actuator/refresh` re-reads the config and **rebinds `@ConfigurationProperties` beans**. A plain `@Value` field is *not* updated unless its bean is `@RefreshScope`. That's why `LimitsProperties` is a mutable class with setters, not a record.
- Refresh updates one instance. With many instances, Spring Cloud Bus broadcasts the refresh (not covered here).
- The config class used to be called `Configuration`, which clashes with Spring's `@Configuration`. It's now `LimitsProperties`.
- This service does **not** register with Eureka: nothing calls it.
- Test gotcha: the test sets `spring.config.import=` (empty) so it never tries to reach a config server.

## Status
✅ **Working.** 2 tests pass; the refresh was checked with the whole system running.
