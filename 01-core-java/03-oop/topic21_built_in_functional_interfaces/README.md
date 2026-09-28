# Topic 21 · Built-in functional interfaces

**Difficulty:** Intermediate · **Needs:** [20 Lambdas](../topic20_lambdas_and_functional_interfaces/) · **Next:** [22 Method references](../topic22_method_references/)

## Why it matters
In topic 20 you wrote your own `Calculator` and `TextCheck` interfaces. Java already ships one for every common "shape" of function in `java.util.function`, and the whole JDK uses them: streams take a `Predicate` in `filter` and a `Function` in `map`, `Optional.orElseGet` takes a `Supplier`. Knowing the handful of shapes makes every API signature readable, and lets you build bigger functions out of small ones.

## What you'll learn
- The main shapes:

  | Interface | Shape | Method |
  |---|---|---|
  | `Predicate<T>` | T → boolean | `test` |
  | `Function<T,R>` | T → R | `apply` |
  | `Consumer<T>` | T → nothing | `accept` |
  | `Supplier<T>` | nothing → T | `get` |
  | `BiFunction<T,U,R>` | (T, U) → R | `apply` |
  | `UnaryOperator<T>` | T → T | `apply` |
  | `BinaryOperator<T>` | (T, T) → T | `apply` |

- Composing them: `and` / `or` / `negate` on predicates, `andThen` / `compose` on functions
- Why a `Supplier` delays work until it's needed

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic21_built_in_functional_interfaces.BuiltInFunctionalInterfaces
```

## Key concepts
- **Pick by shape:** what goes in, and what comes out.
- **Composition:** `isSpring.and(isShort)` builds a new predicate from two existing ones. `f.andThen(g)` means "f, then g", and `f.compose(g)` means "g, then f".
- **Laziness:** a `Supplier` is called only when the value is needed. That's the difference between `orElse(expensive())` and `orElseGet(() -> expensive())`.
- **Primitive versions:** `IntPredicate`, `ToIntFunction` and friends exist to avoid boxing.

## Exercises
`Exercises.java` (run `java -cp out topic21_built_in_functional_interfaces.Exercises`):
1. Combine two predicates with `or`
2. Chain two functions with `andThen`
3. A `BinaryOperator` for the maximum
4. `orElseGet`, which calls its `Supplier` only when needed

## Common mistakes
- Mixing up `andThen` and `compose`: check the order on a small example.
- Writing your own interface when a built-in one fits.
- Calling an expensive method eagerly where a `Supplier` would delay it.

## Related topics
- [30 Intermediate operations](../../05-streams/topic30_intermediate_operations/): `filter(Predicate)`, `map(Function)`
- [37 Higher-order functions](../../05-streams/topic37_higher_order_functions/): functions that take and return functions

## Revision checklist
- [ ] I can name the shape and method of each interface in the table.
- [ ] I can predict `f.andThen(g)` vs `f.compose(g)`.
- [ ] I know when a `Supplier` is better than a plain value.
