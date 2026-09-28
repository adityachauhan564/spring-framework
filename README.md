# 🚀 Java, Spring & Full-Stack Learning Workspace

My hands-on learning path, from **Core Java and DSA**, through **Spring internals** and **Spring Boot REST APIs**, to **Spring Cloud microservices** and a **full-stack Angular + Spring Boot** app.

The folders are numbered in the order to study them. Each numbered folder has a README with a study order and a revision checklist. Each project has a README that explains **why it matters, how to run it, the order to read the code, revision notes and its status**. The topic-based projects in stages 01-03 put the *why* in each topic.

Every Spring project runs with **only JDK 21** (plus Node for the Angular app): databases default to in-memory H2 with sample data, and MySQL, Redis, Kafka and mail are optional extras.

```mermaid
flowchart LR
    A["01 Core Java<br/>54 topics in 10 modules: basics · OOP<br/>Collections · Streams · Concurrency · JUnit · DSA"] --> B["02 Spring Foundations<br/>IoC/DI · AOP · JDBC · ORM<br/>Transactions · MVC"]
    B --> C["03 Spring Boot<br/>Auto-config · Actuator · REST<br/>Validation · Security · Spring Data JPA"]
    C --> D["04 Microservices<br/>Config · Eureka · Feign · Gateway<br/>Resilience4j · Tracing"]
    C --> E["05 Applications<br/>Digital Library · ShowTime · IRCTC<br/>Security · Kafka · Redis"]
    C --> F["06 Full-Stack<br/>Angular + Spring Boot<br/>CORS · Signals · Forms"]
```

---

## 📂 Repository map

| Stage | Folder | Projects |
| :--- | :--- | :--- |
| 1 | [`01-core-java`](./01-core-java) | 54 topics in order: [01 Java basics](./01-core-java/01-java-basics) · [02 Objects and classes](./01-core-java/02-objects-and-classes) · [03 OOP](./01-core-java/03-oop) · [04 Collections and generics](./01-core-java/04-collections-and-generics) · [05 Streams](./01-core-java/05-streams) · [06 Concurrency](./01-core-java/06-concurrency) · [07 Java APIs](./01-core-java/07-java-apis) · [08 Advanced Java](./01-core-java/08-advanced-java) · [09 Testing and build](./01-core-java/09-testing-and-build) · [10 DSA](./01-core-java/10-dsa) |
| 2 | [`02-spring-foundations`](./02-spring-foundations) | [spring-core](./02-spring-foundations/spring-core) · [spring-jdbc](./02-spring-foundations/spring-jdbc) · [spring-orm](./02-spring-foundations/spring-orm) · [spring-mvc](./02-spring-foundations/spring-mvc) |
| 3 | [`03-spring-boot`](./03-spring-boot) | [spring-boot-basics](./03-spring-boot/spring-boot-basics) · [restful-web-services](./03-spring-boot/restful-web-services) · [jpa-hibernate](./03-spring-boot/jpa-hibernate) · [rest-first-books-api](./03-spring-boot/rest-first-books-api) |
| 4 | [`04-microservices`](./04-microservices) | [spring-cloud-config-server](./04-microservices/spring-cloud-config-server) · [naming-server](./04-microservices/naming-server) · [limits-service](./04-microservices/limits-service) · [currency-exchange-service](./04-microservices/currency-exchange-service) · [currency-conversion-service](./04-microservices/currency-conversion-service) · [api-gateway](./04-microservices/api-gateway) |
| 5 | [`05-applications`](./05-applications) | [digital-library](./05-applications/digital-library) · [showtime](./05-applications/showtime) · [irctc-ticket-booking](./05-applications/irctc-ticket-booking) |
| 6 | [`06-fullstack-angular`](./06-fullstack-angular) | [product-service-backend](./06-fullstack-angular/product-service-backend) · [product-inventory-frontend](./06-fullstack-angular/product-inventory-frontend) |
| — | [`reference`](./reference) | [in28minutes-spring-microservices-v3](./reference/in28minutes-spring-microservices-v3): the course's own code, kept for comparison (not my work) |

### Status at a glance
- ✅ **Everything compiles.** Every Maven and Gradle project builds on JDK 21, and the Angular app builds and its tests pass.
- ✅ **Verified running:**
  - `01-core-java`: all 54 topics. Every example and exercise solution runs, and every unsolved `Exercises.java` fails as it should. Module 09's Maven build passes 17 JUnit tests.
  - `02-spring-foundations`: all 4 projects (30 topics) run with no database or server installed, and `./mvnw verify` passes 43 tests; the MVC app was checked over real HTTP on Jetty
  - `03-spring-boot`: all 4 projects run with no database or server installed, and `./mvnw verify` passes 37 tests; every API was checked over real HTTP
  - `04-microservices`: all 6 services start with one script (`start-all.sh` / `.ps1`), and `./mvnw verify` passes 14 tests; config refresh, load balancing, Feign and RestClient calls, gateway routes, Resilience4j and tracing were checked end to end
  - `05-applications`: all 3 projects run with no database or broker installed (40 tests); digital-library was checked with Redis, and showtime with Kafka and Mailpit, in Docker
  - `06-fullstack-angular`: the API and the Angular app work together with no database installed (6 + 12 tests); checked in a headless browser across origins

---

## ⚡ Getting started

### Prerequisites
- **JDK 21**: every project targets it.
- **Maven**: use the `./mvnw` wrapper included with the projects. Stages 02, 03 and 04 also have one wrapper at the stage root that builds and tests all their projects (`./mvnw verify`).
- **Gradle**: use the included `./gradlew` (digital-library and irctc-ticket-booking, Gradle 9.1).
- **MySQL 8** is optional: the Spring projects default to in-memory H2, and their READMEs show how to switch to MySQL where it is supported. **Docker** is needed only for the optional Redis (digital-library) and Kafka/Mailpit (showtime) modes.
- **Node 20+** for the Angular app.

### Credentials: environment variables, never in git
Projects that need a database read it from environment variables:

```bash
# macOS / Linux / Git Bash
export DB_USERNAME=root DB_PASSWORD=your-password
# PowerShell
$env:DB_USERNAME="root"; $env:DB_PASSWORD="your-password"
```

Mail for showtime with a real SMTP server (optional): `MAIL_USERNAME`, `MAIL_PASSWORD`. Locally, Mailpit needs neither.

### Run a Spring Boot project
```bash
cd 03-spring-boot/restful-web-services
./mvnw spring-boot:run          # Windows: .\mvnw.cmd spring-boot:run

cd 05-applications/digital-library
./gradlew bootRun               # the Gradle projects: Windows gradlew.bat bootRun
```
Optional profiles switch on the real infrastructure, e.g. `-Dspring-boot.run.profiles=mysql` (Maven) or `--args='--spring.profiles.active=redis'` (Gradle). Each README lists its profiles.

### Run the microservices
```bash
cd 04-microservices
./start-all.sh                  # Windows PowerShell: .\start-all.ps1
./stop-all.sh
```
It builds all six services, starts them in order (config server → naming server → services → gateway) and waits until they're ready. [`04-microservices/README.md`](./04-microservices/README.md) has a walkthrough of every pattern.

### Run the full-stack app
```bash
cd 06-fullstack-angular/product-service-backend && ./mvnw spring-boot:run      # API on :8080
cd 06-fullstack-angular/product-inventory-frontend && npm ci && npx ng serve   # UI on :4200
```
Then open http://localhost:4200. [`06-fullstack-angular/README.md`](./06-fullstack-angular/README.md) explains how the two halves talk (CORS).

### Run the Core Java topics (no build file)
```bash
cd 01-core-java/01-java-basics                      # any module folder
javac -d out $(find . -name "*.java")               # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out topic04_control_flow.LoopTypes          # any topicNN_<name>.<Class>
java -cp out topic04_control_flow.Exercises          # then solve the exercises
```
Module `09-testing-and-build` is the exception: it's a Maven project (`./mvnw test`).

### Opening in Eclipse / IntelliJ
IDE files (`.project`, `.classpath`, `.settings/`, `.idea/`) are no longer tracked.
- **Maven/Gradle projects:** import them as existing Maven/Gradle projects.
- **Core Java modules:** create a Java project on each module folder (for example `01-core-java/03-oop`), with the folder itself as the source folder. Import `09-testing-and-build` as a Maven project.

---

## 🧠 How to revise quickly
1. Open a stage's README and work through its **Quick revision checklist**.
2. For any item you can't explain, open that project's README and read its **Revision notes**.
3. Then follow **Read the code in this order**, and run the project to see it work.

New to Java? Start with [01 Core Java](./01-core-java): 54 topics in study order, each with a why-first README, runnable examples, exercises with solutions, and a revision checklist.

---

## 🙏 Credits
- Course material: in28minutes (Spring Boot, JPA, Microservices, Functional Programming), Head First Java, GeeksforGeeks JBDL (digital-library, showtime), and other courses named in each project README.
- [`reference/in28minutes-spring-microservices-v3`](./reference/in28minutes-spring-microservices-v3) is a copy of [in28minutes/spring-microservices-v3](https://github.com/in28minutes/spring-microservices-v3).

## 👨‍💻 Author
**Aditya R. Chauhan**. GitHub: [@adityachauhan564](https://github.com/adityachauhan564)
