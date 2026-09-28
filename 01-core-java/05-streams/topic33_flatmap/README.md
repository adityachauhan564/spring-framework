# Topic 33 · flatMap

**Difficulty:** Intermediate · **Needs:** [31 Terminal operations](../topic31_terminal_operations_and_reduce/) · **Next:** [34 Creating streams and primitive streams](../topic34_creating_and_primitive_streams/)

## Why it matters
Data is often nested: orders that contain items, sentences that contain words, users that have roles. `map` keeps the nesting, so you end up with a stream of lists. `flatMap` turns each element into **several** and flattens the result into one stream. It's the tool for "all items across all orders" or "every combination of size and colour", and the same idea reappears in `Optional.flatMap` in the next topics.

## What you'll learn
- `map` vs `flatMap`
- Flattening a list of lists
- Splitting text into words across many sentences
- Building all combinations of two lists

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic33_flatmap.FlatMapDemo
```

## Key concepts
- **`map`** is one-to-one: `x -> value`. **`flatMap`** is one-to-many: `x -> stream of values`, then everything is joined into one stream.
- **Making the inner stream:** `List::stream` for a list, `Arrays.stream(array)` for an array, `text.chars()` for characters.
- **Combinations:** a nested `flatMap` / `map` builds all pairs (a cartesian product).

## Exercises
`Exercises.java` (run `java -cp out topic33_flatmap.Exercises`):
1. The sum over a list of lists
2. The distinct words of many sentences
3. All size and colour combinations

## Common mistakes
- Using `map` and ending up with a `Stream<List<T>>` or `Stream<String[]>`.
- Returning a `List` instead of a `Stream` from the lambda given to `flatMap`: add `.stream()`.

## Related topics
- [35 Optional](../topic35_optional/): `Optional.flatMap` avoids an `Optional<Optional<T>>`
- [38 Capstone](../topic38_streams_capstone/)

## Revision checklist
- [ ] I can explain `flatMap` in one line.
- [ ] I can flatten a `List<List<Integer>>`.
- [ ] I can build all combinations of two lists.
