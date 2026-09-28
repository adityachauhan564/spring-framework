# Topic 34 · Creating streams and primitive streams

**Difficulty:** Intermediate · **Needs:** [31 Terminal operations](../topic31_terminal_operations_and_reduce/) · **Next:** [35 Optional](../topic35_optional/)

## Why it matters
Not all data starts in a `List`. You'll need a stream from an array, from a few loose values, from a range of numbers, or generated on the fly. And a `Stream<Integer>` boxes every number into an object, which is slow and lacks `sum()`. `IntStream` and friends work on raw numbers and add the maths you expect. These are the pieces you use to turn "loop from 1 to n" into a pipeline.

## What you'll learn
- Sources: `list.stream()`, `Stream.of`, `Arrays.stream`, `Stream.iterate`, `Stream.generate`
- `IntStream.range` / `rangeClosed`, and `sum`, `average`, `summaryStatistics`
- `mapToInt` from objects to ints, and `boxed()` back
- Why a stream can be used only once

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic34_creating_and_primitive_streams.CreatingStreams
```

## Key concepts
- **Ranges:** `range(1, 5)` is 1 to 4 (the end is excluded). `rangeClosed(1, 5)` is 1 to 5.
- **Infinite streams:** `Stream.iterate(seed, next)` and `generate(supplier)` are **infinite**, so add `limit(n)` or use the 3-argument `iterate(seed, condition, next)`.
- **Primitive streams:** `IntStream`, `LongStream` and `DoubleStream` avoid boxing and have `sum()`, `average()` (an `OptionalDouble`), `max()`...
- **Converting:** `mapToInt(String::length)` goes from objects to ints, and `boxed()` goes back to `Stream<Integer>`, for example to call `toList()`.
- **Single use:** a stream is consumed by its terminal operation. Using it again throws `IllegalStateException`.

## Exercises
`Exercises.java` (run `java -cp out topic34_creating_and_primitive_streams.Exercises`):
1. Powers of 2 with `iterate`
2. The sum of multiples with `IntStream`
3. The average length with `mapToInt`
4. The max of an `int[]` with `Arrays.stream`

## Common mistakes
- `Stream.iterate(1, n -> n * 2).toList()` without `limit`, which runs forever.
- `range` when you meant `rangeClosed`.
- Keeping a stream in a variable and using it twice.
- `Stream.of(intArray)`: that's a stream of **one** array. Use `Arrays.stream(intArray)`.

## Related topics
- [36 Laziness](../topic36_laziness_and_pipeline/): why an infinite stream is safe to declare
- [52 Complexity](../../10-dsa/topic52_complexity/): the cost of boxing

## Revision checklist
- [ ] I can say `range` vs `rangeClosed`.
- [ ] I know how to make an infinite stream finite.
- [ ] I can move between `Stream<Integer>` and `IntStream`.
