# Spring Boot Basics: what Boot does for you

> How a Spring Boot application starts and configures itself: auto-configuration, configuration properties, profiles, startup runners and logging, Actuator, and the executable jar. Study this before the other stage-03 projects, since they all rely on it.

**Before this:** [02-spring-foundations](../../02-spring-foundations/). You configured everything by hand there; this project shows Boot doing it for you.

## Run it
```bash
./mvnw spring-boot:run            # from this folder; Windows: mvnw.cmd spring-boot:run
```
Each topic prints a `=== topicNN ===` section at startup. The app then keeps running (stop it with `Ctrl+C`). While it runs:
- http://localhost:8080/actuator/health shows the app's health, including a custom check.
- http://localhost:8080/actuator/info shows the description and build info.
- http://localhost:8080/actuator/conditions is the auto-configuration report as JSON.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod                      # the prod profile
./mvnw spring-boot:run -Dspring-boot.run.arguments=--app.greeting.style=formal
./mvnw package && java -jar target/spring-boot-basics-0.0.1-SNAPSHOT.jar     # the executable jar
./mvnw test
```

## Topics

| # | Package | What you learn |
| :- | :--- | :--- |
| 01 | `topic01_how_boot_starts` | `@SpringBootApplication` taken apart; auto-configuration and the conditions report; `@ConditionalOnProperty` beans |
| 02 | `topic02_configuration_properties` | `application.yml`, `@Value` vs validated `@ConfigurationProperties`, relaxed binding, override order |
| 03 | `topic03_profiles` | `application-prod.yml`, `@Profile` beans, `spring.profiles.active` |
| 04 | `topic04_runners_and_logging` | `CommandLineRunner` vs `ApplicationRunner`, `@Order`, SLF4J logging and per-package levels |
| 05 | `topic05_actuator` | health, info and metrics endpoints; a custom `HealthIndicator`; a custom metric; safe exposure |
| 06 | `topic06_packaging` | the executable jar, build info, DevTools |

## Topic notes

### 01 How Boot starts
- **Why:** in stage 02 you wrote the DispatcherServlet, the view resolver and the DataSource yourself. Boot creates them for you, and you need to know how, so that you can change or debug it.
- **How:**
  - `@SpringBootApplication` is `@SpringBootConfiguration` + `@EnableAutoConfiguration` + `@ComponentScan` (the root package and below).
  - Each auto-configuration is guarded by conditions. `@ConditionalOnClass` means "only if that library is on the classpath". `@ConditionalOnMissingBean` means "only if you haven't defined one", so **your bean always wins**.
  - `--debug` or `/actuator/conditions` shows every decision Boot made and why.
- **Exercise:** run with `--app.greeting.style=formal` and watch the bean change.
- **Gotcha:** never name a bean `autoConfigurationReport`. Boot already registers one with that name, and your `@Component` with the same name is silently never created (it happened while writing this topic).

### 02 Configuration properties
- **Why:** settings change per environment. Hard-coding them means rebuilding the app for every change.
- **How:**
  - `@ConfigurationProperties(prefix = "app.shop")` binds a group of keys into a typed, immutable record.
  - `@Validated` makes a bad value fail **at startup**, with a message naming the key.
  - The override order, where later sources win: `application.yml` < `application-<profile>.yml` < environment variables (`APP_SHOP_CURRENCY`) < command-line arguments.
- **Exercise:** set `discount-percent: 95` and read the startup error.

### 03 Profiles
- **How:** activating `prod` also loads `application-prod.yml`, and only `@Profile("prod")` beans exist. `@Profile("!prod")` means "any profile except prod". With no profile set, the `default` profile is active.

### 04 Runners and logging
- **How:**
  - `CommandLineRunner` gets the raw `String[]` arguments. `ApplicationRunner` gets them parsed, with `--name=Asha` as an option.
  - Both run after startup, in `@Order` order.
  - Log with SLF4J (`log.info("x = {}", x)`), not `System.out`. You can change the level per package with `logging.level.<package>=debug`, without touching code.

### 05 Actuator
- **Why:** operators and platforms (load balancers, Kubernetes, monitoring) need to ask the app "are you healthy, what version are you, how busy are you?"
- **How:**
  - `management.endpoints.web.exposure.include` lists what's reachable over HTTP.
  - A `HealthIndicator` bean adds your own check to `/actuator/health`.
  - A Micrometer `Counter` shows up at `/actuator/metrics/<name>`.
- **Mistake:** exposing `env` or `heapdump` publicly. They leak secrets.

### 06 Packaging
- **How:** `./mvnw package` builds one jar that contains your code, every library and an embedded Tomcat, so `java -jar` is the whole deployment. The `build-info` goal in `pom.xml` feeds `/actuator/info`. DevTools restarts the app when classes change, and it's automatically left out of the jar.

## Revision checklist
- [ ] The three annotations inside `@SpringBootApplication`, and why the main class sits in the root package.
- [ ] How auto-configuration decides what to create, and how to see its decisions.
- [ ] `@Value` vs `@ConfigurationProperties`; relaxed binding; what overrides what.
- [ ] How a profile changes both properties and beans.
- [ ] `CommandLineRunner` vs `ApplicationRunner`; why to log instead of printing.
- [ ] Health / info / metrics, and which endpoints must never be public.
- [ ] What's inside the executable jar, and why no server needs installing.

## Status
✅ Working: all 6 topics print at startup, and the Actuator endpoints were checked over HTTP (in the default and `prod` profiles, from the jar and from `spring-boot:run`). 9 tests pass.
