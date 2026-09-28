# Topic 51 · Build tools: Maven

**Difficulty:** Intermediate · **Needs:** [50 Unit testing](../topic50_unit_testing/) · **Next:** [02 Spring Foundations](../../../02-spring-foundations/) (or the [10 DSA](../../10-dsa/) side track)

## Why it matters
So far you compiled with `javac` and ran with `java`, adding files by hand. That stops working quickly:
- a project needs libraries (JUnit here; Spring, Hibernate and a database driver soon), each with its own dependencies;
- tests must run before every release;
- the result must be packaged into one file.

A build tool does all of this from one description. Every Spring project from stage 02 on is a Maven project, so after this topic their `pom.xml` and `./mvnw` stop being magic.

## What you'll learn
- The standard layout: `src/main/java`, `src/test/java`, `target/`
- `pom.xml`: coordinates, properties, dependencies (and scopes), plugins
- The lifecycle: `compile` → `test` → `package` → `install`
- The Maven Wrapper (`mvnw`), and why projects ship it
- Running a single test, and a packaged jar

## Where the code is
This module **is** the example: `09-testing-and-build/pom.xml`, `mvnw`, and `src/main/java/topic51_maven/App.java`.

## Run it
From `09-testing-and-build`:
```bash
./mvnw package                                  # compile, run all tests, build the jar
java -jar target/testing-and-build-1.0.0.jar    # run it
./mvnw clean                                    # delete target/
./mvnw dependency:tree                          # which libraries, and which libraries THEY need
```

## Key concepts
- **Convention over configuration:** code goes in `src/main/java` and tests in `src/test/java`. Then the pom only describes what's *different*.
- **Coordinates:** `groupId:artifactId:version` names this project, and every dependency, uniquely. Maven downloads dependencies from **Maven Central** into `~/.m2/repository`.
- **Scopes:** `test` means available to tests only, never in the jar. The default `compile` scope means needed everywhere.
- **The lifecycle:** running a phase runs **every phase before it**. `package` also compiles and runs the tests, and a failing test stops the build.
- **The wrapper:** `mvnw` / `mvnw.cmd` downloads the exact Maven version the project expects. Nobody needs Maven installed, and everyone uses the same version.
- **Build output:** `target/` is output. It's regenerated, and never committed.

## Exercises
Do these in `09-testing-and-build`, and check each against the answer below it.
1. Run `./mvnw package` and find the jar. What happens to the build if one test fails?
   *Answer: the build stops at the `test` phase with `BUILD FAILURE`, and no jar is made.*
2. Run only `MyMathsTest`.
   *Answer: `./mvnw test -Dtest=MyMathsTest`.*
3. Add a dependency, for example `org.apache.commons:commons-lang3:3.17.0` in `<dependencies>`, and use `StringUtils.capitalize("maven")` in `App`. Rebuild. Why does the jar now fail with `NoClassDefFoundError` when run with `java -jar`?
   *Answer: a plain jar contains only this project's classes. The library is on the classpath at build time but not inside the jar. Spring Boot's plugin builds a "fat" jar that contains everything.*
4. Add `record Point(int x, int y) { }` to `App.java`, then change `maven.compiler.release` to `11` and build again. What happens?
   *Answer: the compile fails, because records need Java 16+ ("records are not supported in -source 11"). The property decides which language version the code may use, whatever JDK runs the build.*

## Common mistakes
- Committing `target/`.
- Editing the version of one JUnit module by hand instead of using the BOM, which leads to mismatched versions.
- Putting test libraries in the default scope, so they end up in production.
- Running `mvn` (a local install, any version) instead of `./mvnw`.

## Related topics
- [50 Unit testing](../topic50_unit_testing/): what the `test` phase runs
- [02 Spring Foundations](../../../02-spring-foundations/): a parent pom with several modules
- [03 Spring Boot](../../../03-spring-boot/): `spring-boot-starter-*` dependencies and the Boot plugin

## Revision checklist
- [ ] I can say what `mvn package` produces, and which phases it runs.
- [ ] I know what the `test` scope means.
- [ ] I can explain why projects ship `mvnw`.
