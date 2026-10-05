# Limits Service

> A microservice (port 8080) that reads its `minimum`/`maximum` limits from the **Spring Cloud Config Server**, for each profile, and can reload them without a restart.

## Why it matters
This is the *client* side of central config. The service comes with safe local defaults, but the real values come from the config server. So an operator can change them in one place, for one environment, without rebuilding the service. Like a school notice board: the principal changes the timetable in one place, and every class reads the new one.

## What it teaches
- Putting config into a typed class with `@ConfigurationProperties("limits-service")`
- Fetching config at startup with `spring.config.import=optional:configserver:...`
- Config for each profile (`dev`, `qa`), served by the config server
- Local fallback values when the config server is down (`optional:`)
- Reloading config while the app runs, with `POST /actuator/refresh`

## Run it
Start [spring-cloud-config-server](../spring-cloud-config-server) first (optional, but it is the whole point of this project).
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
5. `../git-local-config-repo/`: where the real values are kept

## Revision notes
- The config server finds files by `spring.application.name`: `limit-service-microservices` → `limit-service-microservices[-profile].properties`.
- Values from the config server **override** the local `application.properties`.
- `optional:configserver:` = start with the local values if the server can't be reached. Without `optional:`, startup fails.
- `/actuator/refresh` reads the config again and **rebinds `@ConfigurationProperties` beans** (fills them with the new values). A plain `@Value` field is *not* updated unless its bean is `@RefreshScope`. That is why `LimitsProperties` is a class with setters that can change, not a record.
- Refresh updates only one instance (one running copy of the service). With many instances, Spring Cloud Bus sends the refresh to all of them (not covered here).
- The config class used to be called `Configuration`, which clashes with Spring's `@Configuration`. It is now `LimitsProperties`.
- This service does **not** register with Eureka, because nothing calls it.
- Test gotcha: the test sets `spring.config.import=` (empty), so it never tries to reach a config server.

## Status
✅ **Working.** 2 tests pass. The refresh was checked with the whole system running.
