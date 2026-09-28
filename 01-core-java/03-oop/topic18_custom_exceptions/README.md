# Topic 18 · Custom exceptions

**Difficulty:** Intermediate · **Needs:** [10 Exception basics](../../02-objects-and-classes/topic10_exception_basics/), [12 Inheritance](../topic12_inheritance_and_polymorphism/) · **Next:** [19 Nested and anonymous classes](../topic19_nested_and_anonymous_classes/)

## Why it matters
`IllegalArgumentException` says *something* was wrong. `InsufficientBalanceException` with a `getShortBy()` says exactly what, and lets the caller react: offer a top-up of the missing amount. Your own exception types make failures part of your API. Wrapping a low-level error while keeping its cause turns a confusing `NumberFormatException` deep inside a library into "bad port: eighty", without losing the original trace.

## What you'll learn
- Extending `Exception` (checked) or `RuntimeException` (unchecked), and how to choose
- Passing a message to `super(...)`, and carrying extra data in fields
- Exception chaining: `new XException(message, cause)` and `getCause()`
- Catching several specific exceptions, with `finally`

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic18_custom_exceptions.CustomExceptionDemo
```
Read `InsufficientBalanceException`, then `InvalidAmountException`, then `Wallet`, then the demo.

## Key concepts
- **Checked vs unchecked:**
  - **checked** (`extends Exception`) is for conditions a caller can reasonably handle, like not enough balance;
  - **unchecked** (`extends RuntimeException`) is for programming mistakes, like a negative amount.
- **Messages:** `super("Insufficient balance: short by " + shortBy)` sets the text `getMessage()` returns.
- **Extra data:** a field with a getter lets the handler use details (`getShortBy()`), not just parse the text.
- **Chaining:** `super(message, cause)` keeps the original exception, and the stack trace prints both ("Caused by: ...").

## Exercises
`Exercises.java` (run `java -cp out topic18_custom_exceptions.Exercises`):
1. A checked `InvalidAgeException` that carries the age
2. `validateAge`, which throws it
3. `parsePort`, which wraps `NumberFormatException` in a `ConfigException` and keeps the cause

## Common mistakes
- `throw new Exception("error")`: too vague to catch specifically.
- Wrapping without the cause (`new ConfigException(msg)`), which loses the original stack trace.
- Making everything checked, so callers end up with `throws` on every method, or empty catches.
- One exception class per error message. Group them by what the caller does about them.

## Related topics
- [10 Exception basics](../../02-objects-and-classes/topic10_exception_basics/): `try`/`catch`/`finally`
- [03 Spring Boot, restful-web-services](../../../03-spring-boot/restful-web-services/): mapping exceptions to HTTP status codes

## Revision checklist
- [ ] I can decide between checked and unchecked for a new exception.
- [ ] I can write an exception that carries data.
- [ ] I keep the cause when wrapping an exception.
