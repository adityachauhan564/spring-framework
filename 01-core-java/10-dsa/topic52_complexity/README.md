# Topic 52 · Complexity and Big-O

**Difficulty:** Intermediate · **Needs:** [23 Lists](../../04-collections-and-generics/topic23_lists_and_iteration/) · **Next:** [53 Arrays and hashing patterns](../topic53_arrays_and_hashing/)

## Why it matters
Two correct solutions can differ enormously:
- one finishes in a millisecond, the other takes an hour on the same data;
- one works for 1,000 users and falls over at 1,000,000.

Big-O is how you predict that **before** running anything: it describes how the work grows as the input grows. It's the language of every algorithm interview, and it's why the collections module stressed `HashSet.contains` (O(1)) over `List.contains` (O(n)).

## What you'll learn
- Counting steps, and reducing them to O(1), O(log n), O(n), O(n log n), O(n²)
- Why constants and smaller terms are dropped
- Time vs space complexity
- Spotting the complexity of loops, nested loops and halving loops

## Run it
From `10-dsa` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic52_complexity.ComplexityDemo
```
It counts the steps for n = 1000, 2000 and 4000, so you can watch how each column grows.

## Key concepts

| Big-O | Name | Typical code | n × 2 means |
|---|---|---|---|
| O(1) | constant | `list.get(i)`, `set.contains(x)` | the same |
| O(log n) | logarithmic | binary search, halving loops | one more step |
| O(n) | linear | one loop over the data | 2 × the work |
| O(n log n) | linearithmic | a good sort (`Arrays.sort`) | a bit over 2 × |
| O(n²) | quadratic | a loop inside a loop | 4 × the work |

- **Drop the constants:** O(2n + 5) is O(n). Big-O is about the *shape* of the growth.
- **Space:** the HashSet in TwoSum trades O(n) extra **space** for O(n) **time**, instead of O(1) space and O(n²) time.
- **Collections:** `ArrayList.get` is O(1), `LinkedList.get(i)` is O(n). `HashMap.get` is O(1) on average, `TreeMap.get` is O(log n).

## Exercises
`Exercises.java` (run `java -cp out topic52_complexity.Exercises`):
1. Classify six snippets
2. Detect a duplicate in O(n); the check fails if it's O(n²)
3. Binary search in O(log n)

## Common mistakes
- Thinking a loop inside a method called from a loop is O(n): it's O(n²).
- Forgetting the cost of library calls, like `list.contains` or `remove(0)` inside a loop.
- Binary search on unsorted data.
- `(low + high) / 2` overflowing for huge arrays. Use `(low + high) >>> 1`.

## Related topics
- [25 Sets](../../04-collections-and-generics/topic25_sets/), [26 Maps](../../04-collections-and-generics/topic26_maps_and_hashing/): O(1) lookups
- [53](../topic53_arrays_and_hashing/), [54](../topic54_linked_lists_and_two_pointers/): patterns that beat O(n²)

## Revision checklist
- [ ] I can classify a snippet as O(1), O(log n), O(n), O(n log n) or O(n²).
- [ ] I can explain the time/space trade-off in TwoSum.
- [ ] I know why binary search needs sorted data.
