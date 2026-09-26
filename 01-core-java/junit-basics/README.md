# JUnit 5 Basics

> A first JUnit 5 (Jupiter) test for a tiny `MyMaths.calsum` method (in28minutes "JUnit in 5 steps").

## What it teaches
- Separating production code (`src/`) from test code (`test/`)
- Writing a test with `@Test` and `assertEquals(expected, actual)`

## Run it
No build file. You need the JUnit 5 standalone console jar
(`junit-platform-console-standalone-<version>.jar` from Maven Central). From this folder:

```bash
javac -d out src/com/junit/MyMaths.java
javac -d out -cp "out;junit-platform-console-standalone.jar" test/com/junit/MyMathsTest.java
java -jar junit-platform-console-standalone.jar execute -cp out --select-class com.junit.MyMathsTest
```

Use `:` instead of `;` in the classpath on macOS/Linux.
In Eclipse: create a Java project on this folder, mark `test/` as a source folder, then *Build Path > Add Libraries > JUnit 5* (the `.classpath` file is no longer tracked in git).

## Read the code in this order
1. `src/com/junit/MyMaths.java` - the code under test
2. `test/com/junit/MyMathsTest.java` - the test

## Revision notes
- Arrange / Act / Assert: build input, call the method, assert the result.
- `assertEquals(expected, actual)` - expected comes first; the order affects the failure message.
- A test should assert, not `println` - printing proves nothing when run in CI.
- JUnit 5 test classes and methods can be package-private (no `public` needed).
- Good next tests: empty array (sum 0), negative numbers, a single element.

## Status
✅ Working - one test, `assertEquals` only. No lifecycle annotations (`@BeforeEach`, `@AfterAll`, ...) or other assertions yet; the method is still just called `test()`.
