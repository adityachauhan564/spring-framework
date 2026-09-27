# Spring Core: the IoC container and dependency injection

> How the Spring container creates your objects ("beans") and connects them. We start with XML, then move to annotations, then to pure Java config, and finish with properties, profiles and AOP.

**Before this:** [head-first-java](../../01-core-java/head-first-java/) topic05 (interfaces and dependency injection by hand), topic13 (static) and topic24 (records).

## Run it
From `02-spring-foundations/` (the Maven wrapper is there, nothing to install):

```bash
./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ContainerWiringDemo
./mvnw -q -pl spring-core test          # the tests in src/test/java
```

Every topic has one `...Demo` class with a `main` method; swap its name into the command above. In an IDE, right-click the Demo class and choose *Run*. The XML files live in `src/main/resources/com/springcore/<topic>/`.

## Topics (study in this order)

| # | Package | Run | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_why_spring` | `ManualWiringDemo`, then `ContainerWiringDemo` | The problem Spring solves: wiring objects by hand vs letting a container do it |
| 02 | `topic02_xml_setter_injection` | `SetterInjectionDemo` | `<bean>`, `<property>`, the `p:` namespace, when beans are created |
| 03 | `topic03_constructor_injection` | `ConstructorInjectionDemo` | `<constructor-arg>` by position/index/name/type, the `c:` namespace |
| 04 | `topic04_injecting_collections` | `CollectionsDemo` | `<list>`, `<set>`, `<map>`, `<props>`, and shared `<util:*>` collections |
| 05 | `topic05_xml_autowiring` | `XmlAutowiringDemo` | `autowire="byName"`, `byType` and `constructor`, and why two candidates fail |
| 06 | `topic06_annotation_injection` | `AnnotationInjectionDemo` | `@Autowired` on constructor/field/setter, `@Primary`, `@Qualifier` |
| 07 | `topic07_component_scanning` | `ComponentScanningDemo` | `@Component`, `@Service`, `@Value`, and component scanning from XML or Java |
| 08 | `topic08_bean_scopes` | `BeanScopesDemo` | singleton vs prototype, the prototype-in-singleton pitfall, `@Lazy` |
| 09 | `topic09_bean_lifecycle` | `BeanLifecycleDemo` | init/destroy callbacks, 3 ways to write them, and the order they run in |
| 10 | `topic10_spel` | `SpelDemo` | `#{...}` expressions: maths, method calls, bean properties, filtering |
| 11 | `topic11_java_config` | `JavaConfigDemo` | `@Configuration`, `@Bean`, `@Import`, and the singleton proxy |
| 12 | `topic12_properties_and_profiles` | `PropertiesAndProfilesDemo` | `@PropertySource`, `${...}`, `Environment`, `@Profile` |
| 13 | `topic13_aop` | `AopDemo` | `@Aspect`, pointcuts, `@Before`/`@Around`, proxies and self-invocation |

## Topic notes

### 01 Why Spring?
- **Why:** real applications have hundreds of objects that depend on each other. Wiring them by hand (`new A(new B(new C()))`) means every change of implementation is a code change.
- **How:** you describe the beans once, in XML, annotations or Java config. The container creates them and injects each one's dependencies. That's Inversion of Control (IoC): the framework creates the objects, not your code. Handing an object its dependencies from outside is Dependency Injection (DI).
- **Exercise:** switch `why-spring.xml` to `cardPayment` without touching Java code.
- **Mistake:** calling `new OrderService(...)` yourself after starting a container. That object isn't a bean and gets none of Spring's features.

### 02 XML setter injection
- **Why:** it's the simplest way to see what a container does. It builds an object, then calls its setters.
- **How:** `<property name="studentName" value="..."/>` calls `setStudentName(...)`. All singleton beans are created when the container starts, not when you call `getBean`.
- **Exercise:** add a fourth student using the `p:` namespace.
- **Mistake:** a property name that doesn't match a setter. Spring fails at startup with "Invalid property".

### 03 Constructor injection
- **Why:** a required dependency should be impossible to forget. With a constructor, the object is complete, and its fields can be `final`.
- **How:** use `<constructor-arg>` by position, `index`, `name` or `type`, or the `c:` shorthand. `name` needs the `-parameters` compiler flag, which is set in the parent pom (Spring 6.1+ no longer reads parameter names from debug info).
- **Rule of thumb:** use the constructor for required dependencies and a setter for optional ones.
- **Exercise:** add a third constructor parameter and update all five bean styles.

### 04 Injecting collections
- **Why:** configuration often includes lists of values, lookup maps and settings.
- **How:** `<list>`, `<set>`, `<map>` and `<props>` go inside one bean. `<util:list>` / `<util:map>` are beans of their own, so they can be shared with `ref`, and you can choose the implementation class.
- **Mistake:** expecting a `<set>` to keep duplicates, or a HashMap to keep order.

### 05 XML autowiring
- **Why:** writing `ref="..."` for every dependency gets tedious, so let Spring match them.
- **How:** `byName` matches the bean id to the property name, `byType` needs exactly one bean of that type, and `constructor` is `byType` through the constructor.
- **Mistake:** a misspelled setter. The old `setAdress` quietly broke `byName`, because Spring derived the property name "adress". It's fixed now.
- **Checklist:** what exception do you get with two beans of the same type? (`NoUniqueBeanDefinitionException`)

### 06 Annotation injection
- **Why:** it keeps the wiring next to the code that needs it, and it's how modern Spring is written.
- **How:** `<context:annotation-config/>` makes Spring read `@Autowired`. When several beans match, the order is: `@Qualifier("name")` wins, then `@Primary`, otherwise startup fails.
- **Prefer constructor injection:** final fields, and the class is testable without Spring. Field injection works, but hides dependencies.
- **Exercise:** remove `primary="true"` and explain which bean fails and why.

### 07 Component scanning
- **Why:** it removes `<bean>` declarations altogether.
- **How:** `@Component` (or `@Service` / `@Repository` / `@Controller`) plus `<context:component-scan>` or `new AnnotationConfigApplicationContext("package")`. The default bean name is the class name with a lower-case first letter.
- **Mistake:** a class outside the scanned package is never found, so injecting it fails.

### 08 Bean scopes
- **Why:** some objects should be shared (settings, services) and some must be new each time (a shopping cart).
- **How:** singleton (the default) is one instance per container; prototype is a new one on every request. A prototype injected into a singleton is fetched **once**. Use `ObjectProvider` to get a fresh one each time.
- **Checklist:** when is a `@Lazy` singleton created?

### 09 Bean lifecycle
- **Why:** you need to open resources after the dependencies are set, and close them on shutdown.
- **How:** the order is constructor → setters → init (`init-method`, `afterPropertiesSet`, or `@PostConstruct`) → in use → destroy when the **container closes**. Prefer `@PostConstruct` / `@PreDestroy` (the `jakarta.annotation` package).
- **Mistake:** never closing the context. Destroy callbacks then never run. They never run for prototype beans either.

### 10 SpEL
- **Why:** it lets you compute a value from other beans or system properties while the container starts.
- **How:** `#{...}` is evaluated, while `${...}` only looks up a property (topic12). Examples: `T(Math).sqrt(25)`, `pricing.basePrice * 1.18`, `prices.?[#this > 100]`, the Elvis operator `?:`.

### 11 Java config
- **Why:** it's type-safe, easy to refactor and needs no XML. Spring Boot apps are configured this way.
- **How:** in `@Configuration` + `@Bean`, the method name is the bean name. Calling `engine()` from `car()` returns the singleton, because the config class is a CGLIB proxy. `@Import` combines config classes.
- **Exercise:** change `@Configuration` to `@Component`. The "same Engine" check turns false. Explain why.

### 12 Properties and profiles
- **Why:** the same code runs in development, test and production with different settings and beans.
- **How:** `@PropertySource` loads a file. `${key:default}` and `Environment.getProperty` read values. System properties and environment variables override the file. `@Profile("dev")` beans exist only when that profile is active. Spring Boot's `application.properties` builds on exactly this.
- **Exercise:** run with `-Dapp.name=MyApp`.

### 13 AOP
- **Why:** logging, timing, security and transactions apply to many methods. Writing them once, as aspects, keeps the business code clean.
- **How:** an `@Aspect` has a pointcut (which methods) and advice (`@Before`, `@AfterReturning`, `@AfterThrowing` or `@Around`, meaning when to run). Spring wraps matching beans in a **proxy**.
- **Mistake (self-invocation):** `this.otherMethod()` bypasses the proxy, so no advice runs. That's also why `@Transactional` on a method called from the same class does nothing.

## Revision checklist
- [ ] Explain IoC and DI in one sentence each.
- [ ] Constructor vs setter injection: when to use each.
- [ ] byName vs byType vs constructor autowiring, and how `@Qualifier` / `@Primary` settle ambiguity.
- [ ] Singleton vs prototype, and how to get a fresh prototype inside a singleton.
- [ ] The bean lifecycle order, and why destroy callbacks need `close()`.
- [ ] `#{...}` vs `${...}`.
- [ ] Why `@Bean` methods calling each other still return singletons.
- [ ] How profiles select beans, and what overrides a property file.
- [ ] What a proxy is, and why self-invocation skips aspects and `@Transactional`.

## Status
✅ Working: all 13 demos run, and `./mvnw -pl spring-core test` passes (15 tests covering injection, scopes, Java config, profiles and AOP).
