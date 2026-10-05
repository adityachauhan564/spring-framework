# Spring Boot Basics: what Boot does for you

> How a Spring Boot application starts and sets itself up: auto-configuration, configuration properties, profiles, startup runners and logging, Actuator, and the executable jar. Study this before the other stage-03 projects, because they all depend on it.

**Before this:** [02-spring-foundations](../../02-spring-foundations/). There you set up everything by hand. This project shows Boot doing it for you.

## Run it
```bash
./mvnw spring-boot:run            # from this folder; Windows: mvnw.cmd spring-boot:run
```
Each topic prints a `=== topicNN ===` section at startup. Then the app keeps running (stop it with `Ctrl+C`). While it runs:
- http://localhost:8080/actuator/health shows the app's health, including a custom check.
- http://localhost:8080/actuator/info shows the description and the build info.
- http://localhost:8080/actuator/conditions is the auto-configuration report, as JSON.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod                      # the prod profile
./mvnw spring-boot:run -Dspring-boot.run.arguments=--app.greeting.style=formal
./mvnw package && java -jar target/spring-boot-basics-0.0.1-SNAPSHOT.jar     # the executable jar
./mvnw test
```

## Topics

| # | Package | What you learn |
| :- | :--- | :--- |
| 01 | `topic01_how_boot_starts` | `@SpringBootApplication` opened up; auto-configuration and the conditions report; `@ConditionalOnProperty` beans |
| 02 | `topic02_configuration_properties` | `application.yml`, `@Value` vs validated `@ConfigurationProperties`, relaxed binding, which setting wins |
| 03 | `topic03_profiles` | `application-prod.yml`, `@Profile` beans, `spring.profiles.active` |
| 04 | `topic04_runners_and_logging` | `CommandLineRunner` vs `ApplicationRunner`, `@Order`, SLF4J logging and log levels per package |
| 05 | `topic05_actuator` | health, info and metrics endpoints; a custom `HealthIndicator`; a custom metric; exposing only what is safe |
| 06 | `topic06_packaging` | the executable jar, build info, DevTools |

## Topic notes

### 01 How Boot starts
- **Why:** in stage 02 you wrote the DispatcherServlet, the view resolver and the DataSource yourself. Boot creates them for you. You need to know how it does that, so that you can change or debug it.
- **How:**
  - `@SpringBootApplication` is `@SpringBootConfiguration` + `@EnableAutoConfiguration` + `@ComponentScan` (the root package and everything below it).
  - Each auto-configuration has conditions in front of it. `@ConditionalOnClass` means "only if that library is on the classpath". `@ConditionalOnMissingBean` means "only if you haven't defined one yourself", so **your bean always wins**. Like a hostel mess that serves its own food only to students who didn't bring a tiffin.
  - `--debug` or `/actuator/conditions` shows every decision Boot made, and why.
- **Exercise:** run with `--app.greeting.style=formal` and watch the bean change.
- **Gotcha:** never name a bean `autoConfigurationReport`. Boot already registers a bean with that name, so your `@Component` with the same name is silently never created (this happened while writing this topic).

### 02 Configuration properties
- **Why:** settings change from one environment to another. If you hard-code them, you must rebuild the app for every change.
- **How:**
  - `@ConfigurationProperties(prefix = "app.shop")` puts a group of keys into a typed record that cannot be changed later.
  - `@Validated` makes a bad value fail **at startup**, with a message that names the key.
  - The override order, where the later source wins: `application.yml` < `application-<profile>.yml` < environment variables (`APP_SHOP_CURRENCY`) < command-line arguments.
- **Exercise:** set `discount-percent: 95` and read the startup error.

### 03 Profiles
- **How:** turning on `prod` also loads `application-prod.yml`, and only `@Profile("prod")` beans exist. `@Profile("!prod")` means "any profile except prod". When no profile is set, the `default` profile is active.

### 04 Runners and logging
- **How:**
  - `CommandLineRunner` gets the raw `String[]` arguments. `ApplicationRunner` gets them already parsed, with `--name=Asha` as an option.
  - Both run after startup, in `@Order` order.
  - Log with SLF4J (`log.info("x = {}", x)`), not `System.out`. You can change the level for each package with `logging.level.<package>=debug`, without touching any code.

### 05 Actuator
- **Why:** operators and platforms (load balancers, Kubernetes, monitoring tools) need to ask the app: "are you healthy, which version are you, how busy are you?"
- **How:**
  - `management.endpoints.web.exposure.include` lists what can be reached over HTTP.
  - A `HealthIndicator` bean adds your own check to `/actuator/health`.
  - A Micrometer `Counter` shows up at `/actuator/metrics/<name>`.
- **Mistake:** making `env` or `heapdump` public. They leak secrets such as passwords.

### 06 Packaging
- **How:** `./mvnw package` builds one jar with your code, every library and an embedded Tomcat inside it. So `java -jar` is the whole deployment. The `build-info` goal in `pom.xml` feeds `/actuator/info`. DevTools restarts the app when classes change, and it is left out of the jar automatically.

## Revision checklist
- [ ] The three annotations inside `@SpringBootApplication`, and why the main class sits in the root package.
- [ ] How auto-configuration decides what to create, and how to see its decisions.
- [ ] `@Value` vs `@ConfigurationProperties`; relaxed binding; which setting overrides which.
- [ ] How a profile changes both properties and beans.
- [ ] `CommandLineRunner` vs `ApplicationRunner`; why you should log instead of printing.
- [ ] Health / info / metrics, and which endpoints must never be public.
- [ ] What is inside the executable jar, and why no server needs to be installed.

## Status
✅ Working: all 6 topics print at startup, and the Actuator endpoints were checked over HTTP (in the default and `prod` profiles, from the jar and from `spring-boot:run`). 9 tests pass.
