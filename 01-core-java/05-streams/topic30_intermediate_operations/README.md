# Topic 30 · Intermediate operations

**Difficulty:** Intermediate · **Needs:** [29 Structured vs functional](../topic29_structured_vs_functional/) · **Next:** [31 Terminal operations and reduce](../topic31_terminal_operations_and_reduce/)

## Why it matters
Real questions take several steps: keep some items, transform them, drop duplicates, sort, take the first few. Intermediate operations are those steps. Each returns a new stream, so they chain into a pipeline that reads top to bottom like the question itself. Pagination, "top N" lists and cleaning input are all built from these few operations.

## What you'll learn
- `map`, `filter`, `distinct`, `sorted`, `sorted(comparator)`
- `limit` and `skip` (pagination)
- `takeWhile` and `dropWhile`, which stop or start at the first element that fails

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic30_intermediate_operations.IntermediateOperations
```

## Key concepts
- **`map` vs `filter`:** `map(f)` transforms each element and keeps the count. `filter(p)` keeps some elements and doesn't change them.
- **`distinct`** uses `equals`.
- **`sorted`:** `sorted()` uses the natural order. `sorted(Comparator...)` takes any order from [topic 28](../../04-collections-and-generics/topic28_sorting/). Sorting is stable, so ties keep their order.
- **Pagination:** `skip(n).limit(m)` gives items n+1 to n+m.
- **`takeWhile` vs `filter`:** `takeWhile(p)` stops at the **first** failure. `filter(p)` checks **every** element.
- **The source is unchanged:** each step creates a new stream, and the source list is never modified.

## Exercises
`Exercises.java` (run `java -cp out topic30_intermediate_operations.Exercises`):
1. The squares of the distinct odd numbers, largest first
2. The two longest names
3. The lines before the first blank one
4. Page 2 of a list

## Common mistakes
- Expecting the source list to change.
- Using `filter` where `takeWhile` was meant, or the reverse.
- `sorted().limit(3)` for a "top 3" when the order should be descending: pass `Comparator.reverseOrder()`.

## Related topics
- [28 Sorting](../../04-collections-and-generics/topic28_sorting/): building comparators
- [36 Laziness](../topic36_laziness_and_pipeline/): how these steps actually run

## Revision checklist
- [ ] I can explain `takeWhile` vs `filter`.
- [ ] I can paginate with `skip` and `limit`.
- [ ] I know that intermediate operations never change the source.
