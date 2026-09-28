# Module 05 · Streams

**Difficulty:** Intermediate · **Needs:** [04 Collections](../04-collections-and-generics/), [03 OOP topics 20-22](../03-oop/topic20_lambdas_and_functional_interfaces/) · **Next:** [06 Concurrency](../06-concurrency/)

## Why this module
Module 04 stored data. This one processes it declaratively: you describe **what** result you want, like "the distinct even numbers, squared and sorted", and the Stream API does the looping. Streams, `Optional` and collectors are everywhere in modern Java and Spring code. The module goes from the first `filter` to laziness and higher-order functions, and ends with a capstone that combines everything.

| # | Topic | You'll be able to |
|---|---|---|
| 29 | [Structured vs functional](./topic29_structured_vs_functional/) | see why streams exist, and turn a loop into a pipeline |
| 30 | [Intermediate operations](./topic30_intermediate_operations/) | transform a stream step by step |
| 31 | [Terminal operations and reduce](./topic31_terminal_operations_and_reduce/) | produce a single result |
| 32 | [Collectors](./topic32_collectors/) | build maps, groups and summaries |
| 33 | [flatMap](./topic33_flatmap/) | flatten nested data |
| 34 | [Creating streams and primitive streams](./topic34_creating_and_primitive_streams/) | stream from any source, and avoid boxing |
| 35 | [Optional](./topic35_optional/) | handle "maybe no value" without null |
| 36 | [Laziness and the pipeline](./topic36_laziness_and_pipeline/) | know how a pipeline really runs |
| 37 | [Higher-order functions](./topic37_higher_order_functions/) | build behaviour from parameters |
| 38 | [Streams capstone](./topic38_streams_capstone/) | answer real questions with pipelines |

Most of this module comes from the in28minutes *Functional Programming with Java* course.

## Compile and run
From this folder (`01-core-java/05-streams`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -cp out topic36_laziness_and_pipeline.LazyEvaluation
java -cp out topic36_laziness_and_pipeline.Exercises
```
Each topic works the same way as in [module 01](../01-java-basics/#how-to-work-through-a-topic).

## Module checklist
- [ ] I can name the three parts of a pipeline, and build results with `toList()` / collectors, not `forEach`.
- [ ] I can explain `map` vs `flatMap`, and `filter` vs `takeWhile`.
- [ ] I choose the right identity for `reduce`.
- [ ] I can use `groupingBy` with a downstream collector, and know when `toMap` throws.
- [ ] I never call a bare `Optional.get()`, and can chain with `map` / `flatMap` / `orElse`.
- [ ] I can predict how a lazy pipeline runs, and make an infinite stream finite.
