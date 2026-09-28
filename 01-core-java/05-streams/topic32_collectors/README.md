# Topic 32 · Collectors

**Difficulty:** Intermediate · **Needs:** [31 Terminal operations](../topic31_terminal_operations_and_reduce/) · **Next:** [33 flatMap](../topic33_flatmap/)

## Why it matters
"Courses per category", "orders grouped by customer", "pass vs fail", "a comma-separated list of names": these are the everyday reports of business code. Collectors build them in one line, from a stream straight into a `Map`, a `Set` or a `String`. `groupingBy` is the stream version of SQL's `GROUP BY`, and after this topic most reporting code will look familiar.

## What you'll learn
- `toList`, `toSet`, `joining`
- `toMap`, and why duplicate keys need a merge function
- `groupingBy`, with a downstream collector (`counting`, `mapping`, `maxBy`) and a map type (`TreeMap::new`)
- `partitioningBy`, `averagingInt`, `summarizingInt`

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic32_collectors.CollectorsDemo
```

## Key concepts
- **`groupingBy(key)`** gives `Map<Key, List<T>>`. `groupingBy(key, TreeMap::new, counting())` gives sorted keys and counts instead of lists.
- **`partitioningBy(predicate)`** always gives exactly two keys, `false` and `true`.
- **`toMap(keyFn, valueFn)`** **throws** on a duplicate key. Pass a third argument, `(a, b) -> ...`, to merge.
- **`joining(", ", "[", "]")`** takes a separator, a prefix and a suffix.

## Exercises
`Exercises.java` (run `java -cp out topic32_collectors.Exercises`):
1. Group names by first letter
2. Pass/fail with `partitioningBy`
3. `joining`
4. Word counts with `groupingBy` + `counting`

## Common mistakes
- `toMap` without a merge function on data that has duplicate keys, which throws `IllegalStateException`.
- Expecting `groupingBy` to sort its keys. Pass `TreeMap::new`.
- `groupingBy` when you only need two groups: `partitioningBy` is clearer.

## Related topics
- [26 Maps](../../04-collections-and-generics/topic26_maps_and_hashing/): the same results built with `merge` / `computeIfAbsent`
- [38 Capstone](../topic38_streams_capstone/): collectors on real-looking data

## Revision checklist
- [ ] I can explain `groupingBy` vs `partitioningBy`.
- [ ] I know when `toMap` throws, and how to prevent it.
- [ ] I can add a downstream collector like `counting()`.
