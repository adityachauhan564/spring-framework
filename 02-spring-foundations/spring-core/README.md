# Spring Core (IoC & DI)

> How the Spring container creates and wires beans - XML config first, then annotations, then pure Java config.

## What it teaches
- IoC container: `ClassPathXmlApplicationContext` and `AnnotationConfigApplicationContext`
- Setter injection (`p:` namespace), constructor injection (`<constructor-arg>`), collections (`list`/`set`/`map`)
- Autowiring: XML `autowire="byType"` / `"constructor"` and `@Autowired`
- Bean lifecycle: `init-method`, `InitializingBean`/`DisposableBean`, `@PostConstruct`/`@PreDestroy`
- Stereotypes (`@Component`), `@Value`, `@Scope("prototype")`, standalone `<util:list>`/`<util:map>`/`<util:properties>`
- SpEL (`#{22+11}`, `#{T(java.lang.Math).sqrt(25)}`) and `@Configuration` + `@Bean`

## Run it
From this folder (no wrapper here; use `mvn` or any sibling project's `mvnw -f`):

```bash
mvn -q compile dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes;$(cat cp.txt)" com.springcore.App
java -cp "target/classes;$(cat cp.txt)" com.springcore.lifecycle.Test
```

Use `:` instead of `;` on macOS/Linux. Each topic has its own `Test` class: `c_injection`, `collections`, `auto.wire`, `auto.wire.annotation`, `lifecycle`, `spel`, `standalone.collections`, `stereotype`, plus `javaconfig.DemoMain`.
The XML files live in `src/main/resources/` under the same package path the code loads them from.

## Read the code in this order
1. `src/main/resources/config.xml` + `src/main/java/com/springcore/App.java` - first beans, setter injection
2. `c_injection/` - constructor injection (`ci_config.xml`)
3. `collections/` - injecting `List`, `Set`, `Map`
4. `auto/wire/` then `auto/wire/annotation/` - autowiring by XML, then `@Autowired`
5. `lifecycle/` - `Sarbat` (XML init/destroy), `Mattha` (interfaces), `Using_Annotation_example` (annotations)
6. `standalone/collections/` and `stereotype/` - `<util:*>` beans, `@Component`, `@Value`, prototype scope
7. `spel/` - SpEL expressions in `@Value`
8. `javaconfig/` - `@Configuration` + `@Bean`, no XML at all

## Revision notes
- IoC = the container, not your code, creates objects; DI = the container hands them their dependencies.
- Constructor injection suits mandatory dependencies; setter injection suits optional ones.
- `autowire="byType"` fails if two beans of the same type exist - use `byName` or `@Qualifier`.
- Singleton (default) = one instance per container; prototype = new instance per `getBean` (the `stereotype` demo prints different hash codes).
- Destroy callbacks run only on container close - hence `context.registerShutdownHook()` in `lifecycle/Test`. They never run for prototype beans.
- `@PostConstruct`/`@PreDestroy` need annotation processing: `<context:annotation-config/>` or component scanning.
- In `@Configuration` classes, calling `getSamosa()` from another `@Bean` method returns the same singleton (CGLIB proxy).
- `@Value("#{temp}")` injects another bean by name via SpEL.

## Status
✅ Working - the demos above were run from a plain Maven build in this cleanup.
- `lifecycle` uses `javax.annotation` (`javax.annotation-api` dependency). It works on Spring 6.2, but new code should use `jakarta.annotation`.
- `src/test/java/com/springcore/AppTest.java` is the archetype placeholder test (JUnit 3.8).
