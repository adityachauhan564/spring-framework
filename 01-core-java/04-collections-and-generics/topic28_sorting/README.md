# Topic 28 · Sorting: Comparable and Comparator

**Difficulty:** Intermediate · **Needs:** [22 Method references](../../03-oop/topic22_method_references/), [24 Generics](../topic24_generics/) · **Next:** [29 Structured vs functional](../../05-streams/topic29_structured_vs_functional/)

## Why it matters
Java can sort numbers and strings on its own, but how should it sort students? By name, by marks, by marks and then name? `Comparable` gives a class its one natural order. A `Comparator` describes any other order from outside the class, and since Java 8 you build it by composing pieces (`comparing`, `reversed`, `thenComparing`). Leaderboards, tables and reports all need exactly this.

## What you'll learn
- `compareTo` / `compare`: negative, zero or positive
- `Comparable` (the natural order, inside the class) vs `Comparator` (extra orders, outside it)
- `Comparator.comparing`, `comparingInt`, `reversed`, `thenComparing`
- `list.sort(...)` and `Collections.min` / `max`

## Run it
From `04-collections-and-generics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic28_sorting.SortingObjects
```

## Key concepts
- **The result:** `compare(a, b)` is **< 0** if a comes first, **0** if they're equal, **> 0** if b comes first.
- **`Comparable<T>`:** one order, written inside the class. `list.sort(null)` and `TreeSet` use it.
- **`Comparator<T>`:** as many orders as you like, with no change to the class. `Comparator.comparingInt(Student::getMarks).reversed().thenComparing(Student::getName)`.
- **Reversing one part:** `reversed()` reverses **everything built so far**. To reverse only the tie-breaker, reverse that comparator on its own before passing it to `thenComparing`.
- **Comparing numbers:** use `Integer.compare(a, b)`. `a - b` overflows for large values.

## Exercises
`Exercises.java` (run `java -cp out topic28_sorting.Exercises`):
1. Sort by length, then alphabetically
2. Sort by department, then salary descending
3. A natural order for `Version` (1.2 < 1.10 < 2.0)

## Common mistakes
- `return a.marks - b.marks;`, which overflows for large numbers.
- `.reversed()` at the end of a chain when only one key should be reversed.
- Comparing versions or numbers stored as text: `"1.10" < "1.2"` alphabetically.
- A `compareTo` that disagrees with `equals`, which confuses `TreeSet` / `TreeMap`.

## Related topics
- [22 Method references](../../03-oop/topic22_method_references/): `Student::getMarks`
- [30 Intermediate operations](../../05-streams/topic30_intermediate_operations/): `sorted(comparator)` in a stream

## Revision checklist
- [ ] I can explain Comparable vs Comparator.
- [ ] I can build a two-level sort with `thenComparing`.
- [ ] I know why `a - b` is a bad comparison.
