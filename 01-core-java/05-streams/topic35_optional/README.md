# Topic 35 · Optional

**Difficulty:** Intermediate · **Needs:** [31 Terminal operations](../topic31_terminal_operations_and_reduce/) · **Next:** [36 Laziness and the pipeline](../topic36_laziness_and_pipeline/)

## Why it matters
`NullPointerException` is the most common crash in Java, and it happens because "no value" was represented by `null` and someone forgot to check. `Optional<T>` makes "maybe no value" part of the **type**, so the compiler reminds the caller, and it gives safe ways to supply a default or chain further steps. You met it as the result of `findFirst` and `max`. Spring Data's `findById` returns one too.

## What you'll learn
- Creating: `Optional.of`, `ofNullable`, `empty`
- Reading safely: `orElse`, `orElseGet`, `orElseThrow`, `ifPresent`, `ifPresentOrElse`
- Chaining: `map`, `flatMap`, `filter`, `or`
- Wrapping a method that returns `null` (like `Map.get`)

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic35_optional.OptionalBasics
java -ea -cp out topic35_optional.OptionalChaining      # -ea turns on its checks
```

## Key concepts
- **Treat it as a box:** an Optional holds 0 or 1 values. Think of it as a stream of at most one element.
- **`orElse` vs `orElseGet`:** `orElse(x)` always evaluates `x`. `orElseGet(() -> x)` evaluates only when it's empty, so use it for anything expensive.
- **`map` vs `flatMap`:** `map` for a function that returns a plain value, `flatMap` for one that already returns an `Optional`. `map` there would give `Optional<Optional<T>>`.
- **Creating one:** `Optional.of(null)` throws. Use `ofNullable` when the value may be null.

## Exercises
`Exercises.java` (run `java -cp out topic35_optional.Exercises`):
1. `findUser`, which wraps a `Map.get`
2. The city in upper case or "UNKNOWN", in one chain
3. The first long word
4. Parsing into an `Optional`

## Common mistakes
- A bare `get()`, which is just a `NullPointerException` under another name.
- `if (opt.isPresent()) { opt.get() ... }`: that's a null check again. Use `map` / `orElse` / `ifPresent`.
- `Optional` as a field or method parameter type. It's meant for **return values**.
- `Optional.of(maybeNull)`.

## Related topics
- [33 flatMap](../topic33_flatmap/): the same idea for streams
- [03 Spring Boot, jpa-hibernate](../../../03-spring-boot/jpa-hibernate/): `repository.findById(id)` returns an `Optional`

## Revision checklist
- [ ] I can explain `map` vs `flatMap` on an Optional.
- [ ] I know when to use `orElseGet` instead of `orElse`.
- [ ] I never call a bare `get()`.
