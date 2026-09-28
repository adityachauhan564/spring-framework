# Topic 37 · Higher-order functions

**Difficulty:** Intermediate · **Needs:** [21 Built-in functional interfaces](../../03-oop/topic21_built_in_functional_interfaces/) · **Next:** [38 Streams capstone](../topic38_streams_capstone/)

## Why it matters
`filter` and `map` are higher-order functions: they take a function as an argument. Writing your own that **return** functions lets you build behaviour from parameters. `longerThan(3)` returns a ready-made predicate, and `discount(10)` returns a ready-made price calculator. You can also add behaviour around a function, like logging or retrying, without changing it. That's the idea behind Spring's AOP and its interceptors.

## What you'll learn
- Methods that return functions ("function factories")
- Methods that take a function and return an improved one (wrapping, `logged`)
- Currying: `a -> b -> a + b`
- Combining a list of functions with `reduce(Function.identity(), Function::andThen)`

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic37_higher_order_functions.HigherOrderFunctions
```

## Key concepts
- **Function factories:** a returned lambda **captures** the parameters it was built from, so every call makes a new, independent function.
- **Wrapping:** `logged(name, f)` returns a function that calls `f` and adds something around it.
- **`Function.identity()`** is the function that returns its input unchanged: the "zero" when you combine functions.
- **Currying:** `adder().apply(5)` returns a function still waiting for the second number.

## Exercises
`Exercises.java` (run `java -cp out topic37_higher_order_functions.Exercises`):
1. `timesTable(n)`
2. A `between(min, max)` predicate factory
3. `twice(f)`
4. `pipeline(steps)`, which combines a list of functions

## Common mistakes
- Over-abstracting: a plain method is often clearer than a function that returns a function that returns a function.
- Capturing a variable that changes later: it must be effectively final.
- `compose` where `andThen` was meant, which gives the wrong order.

## Related topics
- [21 Built-in functional interfaces](../../03-oop/topic21_built_in_functional_interfaces/): `andThen` / `compose`
- [49 Design patterns](../../08-advanced-java/topic49_design_patterns/): Strategy, behaviour passed in
- [02 Spring Foundations, spring-core AOP](../../../02-spring-foundations/spring-core/): wrapping behaviour around methods

## Revision checklist
- [ ] I can give an example of a function factory.
- [ ] I can wrap a function to add logging.
- [ ] I know what `Function.identity()` is for.
