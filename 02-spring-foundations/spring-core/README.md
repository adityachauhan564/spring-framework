# Spring Core: the IoC container and dependency injection

> How the Spring container (the part of Spring that makes and holds your objects) creates your objects ("beans") and connects them to each other. We start with XML, then move to annotations, then to pure Java config, and end with properties, profiles and AOP.

**Before this:** [01 Core Java](../../01-core-java/): [topic 14](../../01-core-java/03-oop/topic14_interfaces_and_dependency_injection/) (interfaces and dependency injection done by hand), [topic 09](../../01-core-java/02-objects-and-classes/topic09_static_and_final/) (static) and [topic 17](../../01-core-java/03-oop/topic17_records_and_immutability/) (records).

## Run it
From `02-spring-foundations/` (the Maven wrapper is there, so nothing needs to be installed):

```bash
./mvnw -q -pl spring-core compile exec:java -Dexec.mainClass=com.springcore.topic01_why_spring.ContainerWiringDemo
./mvnw -q -pl spring-core test          # the tests in src/test/java
```

Every topic has one `...Demo` class with a `main` method. Put its name into the command above in place of the one shown. In an IDE, right-click the Demo class and choose *Run*. The XML files are in `src/main/resources/com/springcore/<topic>/`.

## Topics (study in this order)

| # | Package | Run | What you learn |
| :- | :--- | :--- | :--- |
| 01 | `topic01_why_spring` | `ManualWiringDemo`, then `ContainerWiringDemo` | The problem Spring solves: connecting objects by hand vs letting a container do it |
| 02 | `topic02_xml_setter_injection` | `SetterInjectionDemo` | `<bean>`, `<property>`, the `p:` namespace, and when beans are created |
| 03 | `topic03_constructor_injection` | `ConstructorInjectionDemo` | `<constructor-arg>` by position/index/name/type, the `c:` namespace |
| 04 | `topic04_injecting_collections` | `CollectionsDemo` | `<list>`, `<set>`, `<map>`, `<props>`, and shared `<util:*>` collections |
| 05 | `topic05_xml_autowiring` | `XmlAutowiringDemo` | `autowire="byName"`, `byType` and `constructor`, and why two matching beans fail |
| 06 | `topic06_annotation_injection` | `AnnotationInjectionDemo` | `@Autowired` on constructor/field/setter, `@Primary`, `@Qualifier` |
| 07 | `topic07_component_scanning` | `ComponentScanningDemo` | `@Component`, `@Service`, `@Value`, and component scanning from XML or Java |
| 08 | `topic08_bean_scopes` | `BeanScopesDemo` | singleton vs prototype, the prototype-inside-singleton trap, `@Lazy` |
| 09 | `topic09_bean_lifecycle` | `BeanLifecycleDemo` | init/destroy callbacks, 3 ways to write them, and the order they run in |
| 10 | `topic10_spel` | `SpelDemo` | `#{...}` expressions: maths, method calls, bean properties, filtering |
| 11 | `topic11_java_config` | `JavaConfigDemo` | `@Configuration`, `@Bean`, `@Import`, and the singleton proxy |
| 12 | `topic12_properties_and_profiles` | `PropertiesAndProfilesDemo` | `@PropertySource`, `${...}`, `Environment`, `@Profile` |
| 13 | `topic13_aop` | `AopDemo` | `@Aspect`, pointcuts, `@Before`/`@Around`, proxies and self-invocation |

## Topic notes

### 01 Why Spring?
- **Why:** real applications have hundreds of objects that need each other. If you connect them by hand (`new A(new B(new C()))`), then changing one implementation means changing code.
- **How:** you describe the beans once, in XML, annotations or Java config. The container creates them and gives each one the objects it needs. This is Inversion of Control (IoC): the framework creates the objects, not your code. Giving an object what it needs from outside is Dependency Injection (DI). Like a hotel kitchen where the store room hands each cook the ingredients, instead of every cook going to the market.
- **Exercise:** switch `why-spring.xml` to `cardPayment` without touching any Java code.
- **Mistake:** calling `new OrderService(...)` yourself after starting a container. That object is not a bean, so it gets none of Spring's features.

### 02 XML setter injection
- **Why:** it is the simplest way to see what a container does. It builds an object, then calls its setters.
- **How:** `<property name="studentName" value="..."/>` calls `setStudentName(...)`. All singleton beans (the default: one object per bean) are made as soon as the container starts, not later when you call `getBean`.
- **Exercise:** add a fourth student using the `p:` namespace.
- **Mistake:** a property name that doesn't match any setter. Spring fails at startup with "Invalid property".

### 03 Constructor injection
- **Why:** a required dependency (an object this class cannot work without) should be impossible to forget. With a constructor, the object is complete from the start, and its fields can be `final`.
- **How:** use `<constructor-arg>` by position, `index`, `name` or `type`, or the `c:` shorthand. `name` needs the `-parameters` compiler flag. It is set in the parent pom (Spring 6.1+ no longer reads parameter names from debug info).
- **Rule of thumb:** use the constructor for required dependencies, and a setter for optional ones.
- **Exercise:** add a third constructor parameter and update all five bean styles.

### 04 Injecting collections
- **Why:** configuration often has lists of values, lookup maps and settings.
- **How:** `<list>`, `<set>`, `<map>` and `<props>` go inside one bean. `<util:list>` / `<util:map>` are beans of their own. So they can be shared with `ref`, and you can choose the exact implementation class.
- **Mistake:** expecting a `<set>` to keep duplicates, or a HashMap to keep the order.

### 05 XML autowiring
- **Why:** writing `ref="..."` for every dependency gets boring, so let Spring match them for you.
- **How:** `byName` matches the bean id to the property name. `byType` needs exactly one bean of that type. `constructor` is `byType`, done through the constructor.
- **Mistake:** a misspelled setter. The old `setAdress` silently broke `byName`, because Spring worked out the property name as "adress". It is fixed now.
- **Checklist:** which exception do you get with two beans of the same type? (`NoUniqueBeanDefinitionException`)

### 06 Annotation injection
- **Why:** it keeps the wiring next to the code that needs it, and modern Spring is written this way.
- **How:** `<context:annotation-config/>` makes Spring read `@Autowired`. When several beans match, this is the order: `@Qualifier("name")` wins, then `@Primary`, otherwise startup fails.
- **Prefer constructor injection:** the fields can be final, and the class can be tested without Spring. Field injection works, but it hides what the class depends on.
- **Exercise:** remove `primary="true"` and explain which bean fails and why.

### 07 Component scanning
- **Why:** it removes `<bean>` declarations completely.
- **How:** `@Component` (or `@Service` / `@Repository` / `@Controller`) plus `<context:component-scan>` or `new AnnotationConfigApplicationContext("package")`. The default bean name is the class name with a small first letter.
- **Mistake:** a class outside the scanned package is never found, so injecting it fails.

### 08 Bean scopes
- **Why:** some objects should be shared (settings, services), and some must be new each time (a shopping cart).
- **How:** singleton (the default) is one object per bean in the container. Prototype gives a new one on every request. A prototype injected into a singleton is fetched only **once**. Use `ObjectProvider` to get a fresh one each time.
- **Checklist:** when is a `@Lazy` singleton created?

### 09 Bean lifecycle
- **Why:** you need to open resources after the dependencies are set, and close them on shutdown.
- **How:** the order is constructor → setters → init (`init-method`, `afterPropertiesSet`, or `@PostConstruct`) → in use → destroy when the **container closes**. Prefer `@PostConstruct` / `@PreDestroy` (from the `jakarta.annotation` package).
- **Mistake:** never closing the context. Then the destroy callbacks never run. They never run for prototype beans either.

### 10 SpEL
- **Why:** it lets you work out a value from other beans or system properties while the container starts.
- **How:** `#{...}` is calculated, while `${...}` only looks up a property (topic12). Examples: `T(Math).sqrt(25)`, `pricing.basePrice * 1.18`, `prices.?[#this > 100]`, and the Elvis operator `?:` (a short "if empty, use this").

### 11 Java config
- **Why:** it is type-safe (the compiler checks it), easy to refactor, and needs no XML. Spring Boot apps are configured this way.
- **How:** in `@Configuration` + `@Bean`, the method name is the bean name. Calling `engine()` from `car()` returns the same singleton, because Spring wraps the config class in a CGLIB proxy (a generated subclass). `@Import` combines config classes.
- **Exercise:** change `@Configuration` to `@Component`. The "same Engine" check becomes false. Explain why.

### 12 Properties and profiles
- **Why:** the same code runs in development, test and production, with different settings and beans.
- **How:** `@PropertySource` loads a file. `${key:default}` and `Environment.getProperty` read values. System properties and environment variables override the file. `@Profile("dev")` beans exist only when that profile is active. Spring Boot's `application.properties` is built on exactly this.
- **Exercise:** run with `-Dapp.name=MyApp`.

### 13 AOP
- **Why:** logging, timing, security and transactions apply to many methods. Writing them once, as aspects, keeps the business code clean.
- **How:** an `@Aspect` has a pointcut (which methods) and advice (`@Before`, `@AfterReturning`, `@AfterThrowing` or `@Around`: when to run). Spring wraps matching beans in a **proxy** (a stand-in object that runs the advice and then calls the real one).
- **Mistake (self-invocation):** `this.otherMethod()` goes around the proxy, so no advice runs. That is also why `@Transactional` on a method called from the same class does nothing.

## Revision checklist
- [ ] Explain IoC and DI in one sentence each.
- [ ] Constructor vs setter injection: when to use which.
- [ ] byName vs byType vs constructor autowiring, and how `@Qualifier` / `@Primary` settle a tie.
- [ ] Singleton vs prototype, and how to get a fresh prototype inside a singleton.
- [ ] The bean lifecycle order, and why destroy callbacks need `close()`.
- [ ] `#{...}` vs `${...}`.
- [ ] Why `@Bean` methods calling each other still return singletons.
- [ ] How profiles choose beans, and what overrides a property file.
- [ ] What a proxy is, and why self-invocation skips aspects and `@Transactional`.

## Status
✅ Working: all 13 demos run, and `./mvnw -pl spring-core test` passes (15 tests covering injection, scopes, Java config, profiles and AOP).
