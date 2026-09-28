# Topic 31 · Terminal operations and reduce

**Difficulty:** Intermediate · **Needs:** [30 Intermediate operations](../topic30_intermediate_operations/) · **Next:** [32 Collectors](../topic32_collectors/)

## Why it matters
A pipeline only produces something at its terminal operation: a count, a total, a yes/no, the first match, a list. `reduce` is the general one, "combine everything into one value", and `sum`, `max` and `count` are all special cases of it. Once you understand the identity value and the accumulator, you can reason about any aggregation, in Java streams or in the SQL `GROUP BY` queries you'll meet with JPA.

## What you'll learn
- `reduce(identity, accumulator)`, and `reduce(accumulator)`, which returns an `Optional`
- `count`, `min`, `max`
- `anyMatch` / `allMatch` / `noneMatch`, and `findFirst`, all short-circuiting
- `toList()`

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -ea -cp out topic31_terminal_operations_and_reduce.TerminalOperations     # -ea turns on its checks
```
The `steps:` line prints each reduce step, so you can see the running total.

## Key concepts
- **`reduce(0, (total, n) -> total + n)`:** start at 0, then combine the running total with each element.
- **The identity** is the value that changes nothing: 0 for `+`, 1 for `*`. It's also the result for an empty stream.
- **Empty streams:** `min`, `max`, `findFirst` and `reduce(accumulator)` return an `Optional`, because the stream might be empty.
- **Short-circuiting:** the `*Match` methods and `findFirst` stop as soon as the answer is known.
- **`toList()`** (Java 16+) returns an unmodifiable list.

## Exercises
`Exercises.java` (run `java -cp out topic31_terminal_operations_and_reduce.Exercises`):
1. The longest name with `reduce`
2. A product, with the right identity
3. `allMatch`
4. `findFirst` with a default

## Common mistakes
- The wrong identity, like `reduce(0, (a, b) -> a * b)`, which always gives 0.
- Calling `get()` on the `Optional` from `max`: use `orElse` or `orElseThrow`.
- Using `reduce` to build a list. Use `toList()` or a collector ([32](../topic32_collectors/)).

## Related topics
- [32 Collectors](../topic32_collectors/): building collections and summaries
- [34 Primitive streams](../topic34_creating_and_primitive_streams/): `sum()` and `average()` on `IntStream`
- [35 Optional](../topic35_optional/)

## Revision checklist
- [ ] I can explain why the identity is 1 for a product.
- [ ] I know which terminal operations return an `Optional`, and why.
- [ ] I can say which operations short-circuit.
