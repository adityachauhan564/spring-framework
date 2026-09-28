# Topic 36 · Laziness and the pipeline

**Difficulty:** Intermediate · **Needs:** [34 Creating streams](../topic34_creating_and_primitive_streams/) · **Next:** [37 Higher-order functions](../topic37_higher_order_functions/)

## Why it matters
It's natural to picture a pipeline as "filter the whole list, then map the whole result, then take the first". That isn't what happens. Streams are **lazy**: nothing runs until the terminal operation, and then each element travels through the whole pipeline before the next one starts. That's why `findFirst` on a million elements can finish after reading two, and why an infinite stream is safe. It also explains the classic bug of a pipeline that "does nothing".

## What you'll learn
- Nothing runs without a terminal operation
- Elements flow one at a time through every step
- Short-circuiting: `findFirst`, `limit`, `anyMatch` stop the source early
- Infinite streams, made finite by a short-circuiting step
- `peek` for debugging, and why it's only for debugging

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic36_laziness_and_pipeline.LazyEvaluation
```
Read each printed trace and check that it matches your prediction.

## Key concepts
- **Building a pipeline does nothing:** `list.stream().filter(...).map(...)` only describes the work.
- **Vertical processing:** the terminal operation pulls elements one by one. Element 1 goes through filter and map, then element 2, and so on.
- **Short-circuiting:** a short-circuiting step stops pulling once it has its answer. The remaining elements are never touched.
- **Infinite streams:** `Stream.iterate(1, n -> n + 1)` is infinite but safe, **if** something like `limit` or `findFirst` stops it.

## Exercises
`Exercises.java` (run `java -cp out topic36_laziness_and_pipeline.Exercises`):
1. **Predict** a `peek` trace, then check it
2. The first n primes from an infinite stream
3. The first square above a limit

## Common mistakes
- Forgetting the terminal operation, so nothing happens.
- Using `peek` (or `map`) for real side effects such as saving or counting. The work may be skipped or reordered.
- An infinite stream with a non-short-circuiting terminal (`toList`, `count`), which never finishes.
- `sorted()` in the middle of an infinite stream: it needs every element, so it never ends.

## Related topics
- [42 Parallel streams](../../06-concurrency/topic42_parallel_streams/): the same pipeline across threads
- [21 Built-in functional interfaces](../../03-oop/topic21_built_in_functional_interfaces/): `Supplier`, another way to defer work

## Revision checklist
- [ ] I can explain why `Stream.iterate` without `limit` is safe to declare.
- [ ] I can predict a `peek` trace for `filter` + `findFirst`.
- [ ] I know which terminal operations short-circuit.
