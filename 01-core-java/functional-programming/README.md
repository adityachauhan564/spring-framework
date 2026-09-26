# Functional Programming with Java

> First steps from structured loops to Streams, lambdas, method references and `Optional` (in28minutes "Functional Programming with Java", by Ranga).

## What it teaches
- Structured (loop) vs functional (stream) style for the same task
- `stream().forEach(System.out::println)` - method references
- `filter` with lambdas and predicates
- `Optional` returned by `findFirst()`

## Run it
No build file - plain `javac`/`java` from this folder:

```bash
javac -d out $(find src -name "*.java")
java -cp out programming.FP01Structured
java -cp out programming.FP01Functional
java -cp out programming.FP01Exercise
java -cp out programming.PlayingWithOptional
```

## Read the code in this order
1. `src/programming/FP01Structured.java` - the "how": a `for` loop
2. `src/programming/FP01Functional.java` - the "what": `stream()`, `filter`, method reference
3. `src/programming/FP01Exercise.java` - exercises on a list of course names
4. `src/programming/PlayingWithOptional.java` - `Optional` from a stream search

## Revision notes
- Structured code says *how* to loop; functional code says *what* you want (`filter(e -> e % 2 == 0)`).
- `System.out::println` is a method reference - shorthand for `x -> System.out.println(x)`.
- A lambda can only target a functional interface (one abstract method), e.g. `Predicate<T>`.
- Streams are lazy: nothing runs until a terminal operation like `forEach` or `collect`.
- `findFirst()` returns `Optional<T>` because the stream may be empty.
- Never call `optional.get()` blindly - use `orElse`, `orElseThrow`, or `ifPresent`. `PlayingWithOptional` calls `get()` directly; that only works because `"banana"` exists.
- `length() >= 4` means "at least 4", not "more than 4" - watch boundary wording in interviews.

## Status
✅ Working.
- `FP01Exercise.printAllEvenInFunctional` is named "Even" but prints **odd** numbers, and its call in `main` is commented out - a good exercise to fix.
