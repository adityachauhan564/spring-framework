# Topic 38 · Streams capstone

**Difficulty:** Intermediate · **Needs:** topics [30](../topic30_intermediate_operations/)–[37](../topic37_higher_order_functions/) · **Next:** [39 Threads](../../06-concurrency/topic39_threads/)

## Why it matters
Each stream topic taught one operation. Real questions combine them:
- "the top 3 courses by students, with score as a tie-breaker";
- "the best course in each category";
- "a clean, de-duplicated list of phone numbers from messy input".

This capstone answers questions like these on realistic data, and it's the best check that the module has clicked. If you can solve its exercises without looking back, you can read the stream code in any Spring project.

## What you'll learn
- Choosing the right operations for a question, and in the right order
- Multi-level sorting, `max` with a comparator, `mapToInt` + `sum` / `average`
- `groupingBy` with `mapping`, `counting` and `maxBy`
- A cleaning pipeline where the **order of the steps matters**

## Run it
From `05-streams` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -ea -cp out topic38_streams_capstone.CourseAnalysis       # in28minutes FP03-style questions
java -ea -cp out topic38_streams_capstone.PhoneNumberCleaner   # an interview-style cleaning pipeline
```
Read `Course` first, then `CourseAnalysis`. Each numbered block answers one question with one pipeline.

## Key concepts
- **Start from the result's shape:** a number means a terminal like `sum` or `count`; a `Map` means `groupingBy`; a single item means `max` or `findFirst`.
- **Order of steps:** filter early (less work), and drop nulls **before** calling methods on the elements.
- **Streaming a map:** `groupingBy` then `.entrySet().stream()` lets you continue processing the groups (exercise 1).
- **Readability:** keep one idea per line, and give a complex comparator or predicate a name.

## Exercises
`Exercises.java` (run `java -cp out topic38_streams_capstone.Exercises`):
1. The category with the most students
2. The average score per category
3. The names of the popular courses, sorted

## Common mistakes
- One giant pipeline nobody can read. Split out named comparators and helper methods.
- `trim()` before filtering out nulls (a `NullPointerException`).
- Sorting before filtering: correct, but it sorts data you're about to throw away.

## Related topics
- [32 Collectors](../topic32_collectors/), [35 Optional](../topic35_optional/)
- [03 Spring Boot](../../../03-spring-boot/): streams over data loaded from a database

## Revision checklist
- [ ] I can answer three course questions without looking.
- [ ] I can explain why the order of the steps matters in `PhoneNumberCleaner`.
- [ ] I can post-process a `groupingBy` result with `entrySet().stream()`.
