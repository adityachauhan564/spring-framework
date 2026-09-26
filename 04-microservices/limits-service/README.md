# Limits Service

> A microservice that reads its `minimum`/`maximum` limits from the **Spring Cloud Config Server**, per profile. From the in28minutes microservices course.

## What it teaches
- Binding typed config with `@ConfigurationProperties("limits-service")`
- Pulling external config at startup with `spring.config.import=optional:configserver:...`
- Profile-specific config (`dev`, `qa`) served by the config server
- Local fallback values when the config server is down (`optional:`)

## Run it
Start [spring-cloud-config-server](../spring-cloud-config-server) (port 8888) first. It's optional but shows the point of this project.
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run
curl localhost:8080/limits
```

| Situation | Response |
|---|---|
| Config server running, profile `dev` (the default here) | `{"minimum":5,"maximum":995}` from `limit-service-microservices-dev.properties` |
| Config server down | `{"minimum":3,"maximum":997}` from the local `application.properties` |

To try the `qa` profile, change `spring.cloud.config.profile=qa` (expect 6/994).

## Read the code in this order
1. `src/main/resources/application.properties`: app name, config-server import, profile
2. `src/main/java/com/example/udemy/limit_service_microservices/configuration/Configuration.java`: `@ConfigurationProperties`
3. `src/main/java/com/example/udemy/limit_service_microservices/bean/Limits.java`: the response bean
4. `src/main/java/com/example/udemy/limit_service_microservices/controller/LimitsController.java`: `/limits`
5. `../git-local-config-repo/`: where the real values live

## Revision notes
- The config server finds files by `spring.application.name`: `limit-service-microservices` maps to `limit-service-microservices[-profile].properties`.
- Values from the config server **override** the local `application.properties`.
- `optional:configserver:` means startup doesn't fail if the server is unreachable. Without `optional:` it would.
- `@ConfigurationProperties` binds by prefix and needs setters. It's type-safe, unlike scattering `@Value` everywhere.
- The class name `Configuration` shadows Spring's `@Configuration` annotation. Rename it (e.g. `LimitsConfiguration`) to avoid confusing imports.
- This service does **not** register with Eureka: it has no eureka-client dependency.

## Status
✅ **Working.** Compiles (Boot 4.0.2 / Spring Cloud 2025.1.0).
