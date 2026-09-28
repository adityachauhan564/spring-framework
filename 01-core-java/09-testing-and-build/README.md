# Module 09 · Testing and build

**Difficulty:** Intermediate · **Needs:** [02 Objects and classes](../02-objects-and-classes/) (topic 50 can start early) · **Next:** [02 Spring Foundations](../../02-spring-foundations/)

## Why this module
Two skills separate practice code from project code:
- **automated tests** that prove the code works, and keep proving it after every change;
- **a build tool** that fetches libraries, runs those tests and packages the result with one command.

This module is the bridge to the Spring stages, where every project is built this way. Unlike the other modules it's a small Maven project, because JUnit is a library, and managing libraries is exactly what Maven is for.

| # | Topic | You'll be able to |
|---|---|---|
| 50 | [Unit testing with JUnit 5](./topic50_unit_testing/) | prove your code works with tests that run in one command |
| 51 | [Build tools: Maven](./topic51_maven/) | read a `pom.xml`, run the lifecycle, and package a jar |

## Run it
From this folder (`01-core-java/09-testing-and-build`). Only JDK 21 is needed: the wrapper downloads Maven.
```bash
./mvnw test          # all tests            (Windows: mvnw.cmd test)
./mvnw package       # tests + a runnable jar in target/
java -jar target/testing-and-build-1.0.0.jar
```
In an IDE, import the folder as a Maven project; it then finds the source folders and JUnit by itself.

```
09-testing-and-build/
  pom.xml, mvnw, mvnw.cmd, .mvn/     the build (topic 51)
  src/main/java/topic50_unit_testing  code under test          src/main/java/topic51_maven   App
  src/test/java/topic50_unit_testing  tests + ExercisesTest + solutions/
  topic50_unit_testing/README.md      topic51_maven/README.md
```

## Module checklist
- [ ] I write tests that assert, cover the error cases and the boundaries, and don't depend on each other.
- [ ] I can use `assertThrows`, `@BeforeEach` and `@ParameterizedTest`.
- [ ] I can read a `pom.xml`: coordinates, dependencies with scopes, plugins.
- [ ] I know what `./mvnw package` runs, and why a failing test stops it.
