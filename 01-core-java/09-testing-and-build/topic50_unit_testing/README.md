# Topic 50 · Unit testing with JUnit 5

**Difficulty:** Intermediate · **Needs:** [08 Classes and objects](../../02-objects-and-classes/topic08_classes_and_objects/) (you can start this early) · **Next:** [51 Build tools: Maven](../topic51_maven/)

## Why it matters
Every `Exercises.java` so far checked itself with `check(...)` in a `main` method. That works for one file, but a real project has hundreds of classes. It needs one command that runs **all** the checks and fails the build if any breaks. That's what unit tests are: small, automatic proofs that each piece works, re-run after every change, so a bug you introduce today is caught today rather than by a user next month. Every project in stages 02–06 has them.

## What you'll learn
- `@Test`, and assertions: `assertEquals`, `assertTrue` / `assertFalse`, `assertThrows`, `assertAll`
- Arrange / Act / Assert, and naming tests after behaviour
- `@BeforeEach` for a fresh setup per test
- `@ParameterizedTest` with `@CsvSource` / `@ValueSource`
- Testing boundaries and error cases, not just the happy path

## Where the code is
Tests need the JUnit library, so this module is a Maven project (see [topic 51](../topic51_maven/)):

| File | Shows |
|---|---|
| `src/main/java/topic50_unit_testing/MyMaths.java` | the code under test |
| `src/test/java/topic50_unit_testing/MyMathsTest.java` | a first test, Arrange / Act / Assert |
| `src/main/java/.../ShoppingCart.java` + `src/test/java/.../ShoppingCartTest.java` | `@BeforeEach`, `assertThrows`, `assertAll`, a parameterized test |

## Run it
From `09-testing-and-build`:
```bash
./mvnw test                          # every test (Windows: mvnw.cmd test)
./mvnw test -Dtest=ShoppingCartTest  # one test class
```
The report ends with `Tests run: ..., Failures: 0`. Break `ShoppingCart.total()` on purpose, run it again, and read the failure message.

## Key concepts
- **What to assert:** a test **asserts**. `assertEquals(expected, actual)` puts the expected value first, which matters for the failure message.
- **Isolation:** JUnit creates a **new** test object for every test method, and `@BeforeEach` runs before each one. Tests must not depend on each other's order.
- **Exceptions:** `assertThrows(Type.class, () -> code)` passes only if the code throws, and returns the exception so you can check its message.
- **Many cases:** `@ParameterizedTest` runs one test for many inputs. Boundaries (exactly 1000, exactly 8 characters) deserve their own case.
- **Decimals:** compare `double`s with a delta: `assertEquals(20, total, 0.001)`.

## Exercises
`src/test/java/topic50_unit_testing/ExercisesTest.java`: write the tests for `PasswordValidator`. Remove one `@Disabled` at a time and run `./mvnw test -Dtest=ExercisesTest`.
1. A strong password is valid
2. Each broken rule makes it invalid
3. The 8-character boundary
4. `null` is rejected

Solutions are in `src/test/java/topic50_unit_testing/solutions/`.

## Common mistakes
- Tests with no assertions, or `println` instead of assertions.
- `assertEquals(actual, expected)` the wrong way round, which gives a confusing message.
- Testing only the happy path.
- Tests that share state, so they pass alone and fail together.
- `assertEquals` on doubles without a delta.

## Related topics
- [51 Maven](../topic51_maven/): what `./mvnw test` does
- [03 Spring Boot](../../../03-spring-boot/): MockMvc and `@DataJpaTest` build on exactly this

## Revision checklist
- [ ] I can say what makes a test fail.
- [ ] I can test that code throws, with `assertThrows`.
- [ ] I can write a parameterized test, and I test the boundaries.
