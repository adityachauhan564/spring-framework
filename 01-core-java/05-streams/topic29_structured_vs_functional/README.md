# Topic 29 · Structured vs functional

**Difficulty:** Intermediate · **Needs:** [23 Lists](../../04-collections-and-generics/topic23_lists_and_iteration/), [20 Lambdas](../../03-oop/topic20_lambdas_and_functional_interfaces/) · **Next:** [30 Intermediate operations](../topic30_intermediate_operations/)

## Why it matters
A loop says **how**: create a result list, loop, check, add, return. A stream says **what**: "the even numbers". For one filter the difference is small. For "the top 3 courses by students, among those rated over 90, grouped by category", a loop grows into nested ifs and temporary lists, while a stream stays one readable pipeline. Modern Java code, Spring included, is full of streams, so you need to read and write both styles.

## What you'll learn
- The imperative ("how") style vs the declarative ("what") style
- `stream()`, `filter(...)`, `forEach(...)`, `toList()`, `count()`
- How a lambda plugs into `filter`

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic29_structured_vs_functional.StructuredApproach
java -cp out topic29_structured_vs_functional.FunctionalApproach
java -cp out topic29_structured_vs_functional.CourseFilterExercises     # in28minutes FP01 examples
```
Compare the two approach files: the same two tasks, solved both ways.

## Key concepts
- **A pipeline has three parts:** a **source** (`list.stream()`), then **intermediate** steps (`filter`, `map`, ...), then **one terminal** step (`toList`, `count`, `forEach`).
- **The source isn't changed:** a stream produces a new result.
- **`filter(predicate)`** keeps the elements for which the lambda returns `true`.
- **`forEach` is for side effects** like printing. To *build* a result, use `toList()` or `collect(...)`.

## Exercises
`Exercises.java` (run `java -cp out topic29_structured_vs_functional.Exercises`): rewrite three loops as one pipeline each.

## Common mistakes
- Building a list with `forEach(x -> result.add(x))` instead of `toList()`.
- Forgetting the terminal step, so nothing happens at all (see [36](../topic36_laziness_and_pipeline/)).
- Using a stream where a plain loop is clearer, for example when you need to break out early with complex logic.

## Related topics
- [30 Intermediate operations](../topic30_intermediate_operations/), [31 Terminal operations](../topic31_terminal_operations_and_reduce/)
- [21 Built-in functional interfaces](../../03-oop/topic21_built_in_functional_interfaces/): `Predicate` is what `filter` takes

## Revision checklist
- [ ] I can say in one sentence what functional code describes, compared to a loop.
- [ ] I can name the three parts of a pipeline.
- [ ] I use `toList()`, not `forEach` with `add`, to build a result.
