# Functional Programming with Java

> From loops to lambdas, streams, collectors and `Optional`, one topic at a time. Based on the in28minutes course "Functional Programming with Java" (by Ranga), extended with the topics you need for real code and interviews.
> Every package is one topic, numbered in study order. Every file opens with a header comment: **Topic, Key idea, Run, Try this**.

**Before this:** [head-first-java](../head-first-java/) topics 01-24 (classes, interfaces, generics, collections).

## Run it
There's no build file. Use plain `javac`/`java` from this folder (Git Bash, macOS or Linux):

```bash
javac -d out $(find src -name "*.java")
java -ea -cp out topic07_terminal_operations_and_reduce.TerminalOperations   # -ea turns on the asserts
```

To run every program at once:

```bash
for f in $(grep -rl "static void main" src | sort); do
  c=$(echo "${f#src/}" | sed 's|\.java$||; s|/|.|g')
  echo "== $c"; java -ea -cp out "$c"
done
```

## Topics (study in this order)

| # | Package (`src/`) | Files | What you learn |
| :- | :--- | :--- | :--- |
| 1 | `topic01_structured_vs_functional` | `StructuredApproach` → `FunctionalApproach` | The same task as a loop ("how") and as a stream ("what") |
| 2 | `topic02_lambdas_and_functional_interfaces` | `LambdaSyntax` | `@FunctionalInterface`, lambda syntax forms, effectively final |
| 3 | `topic03_filter_exercises` | `CourseFilterExercises` | `filter` + `forEach` exercises on numbers and course names |
| 4 | `topic04_built_in_functional_interfaces` | `BuiltInFunctionalInterfaces` | `Predicate`, `Function`, `Consumer`, `Supplier`, `BiFunction`, operators, composing |
| 5 | `topic05_method_references` | `MethodReferences` | The 4 kinds of `::` and the lambda each one replaces |
| 6 | `topic06_intermediate_operations` | `IntermediateOperations` | `map`, `distinct`, `sorted`, `limit`, `skip`, `takeWhile`, `dropWhile` |
| 7 | `topic07_terminal_operations_and_reduce` | `TerminalOperations` | `reduce` step by step, `count`/`min`/`max`, `anyMatch`/`allMatch`/`noneMatch` |
| 8 | `topic08_collectors` | `CollectorsDemo` | `toList`/`toSet`/`toMap`, `joining`, `groupingBy`, `partitioningBy`, summaries |
| 9 | `topic09_flatmap` | `FlatMapDemo` | `map` vs `flatMap`, flattening nested lists, combinations |
| 10 | `topic10_creating_and_primitive_streams` | `CreatingStreams` | `Stream.of`/`iterate`/`generate`, `IntStream`, `mapToInt`, `boxed`, single use |
| 11 | `topic11_optional` | `OptionalBasics` → `OptionalChaining` | Reading an Optional safely; `map`/`flatMap`/`filter`/`or` chains |
| 12 | `topic12_laziness_and_pipeline` | `LazyEvaluation` | Why nothing runs without a terminal operation; one element at a time; short-circuiting |
| 13 | `topic13_higher_order_functions` | `HigherOrderFunctions` | Functions that return functions, wrapping functions, currying |
| 14 | `topic14_parallel_streams` | `ParallelStreams` | When `parallel()` helps, ordering, why shared mutable state breaks |
| 15 | `topic15_capstone_course_analysis` | `Course` → `CourseAnalysis` | All of the above answering real questions about a course list |

## Revision checklist
**1-3. Basics**
- [ ] Structured code says *how* to loop. Functional code says *what* you want: `filter(n -> n % 2 == 0)`.
- [ ] A lambda can only target a functional interface, meaning an interface with exactly one abstract method.
- [ ] A local variable used inside a lambda must be effectively final: it's never reassigned.
- [ ] Read boundaries carefully: `length() >= 4` means "at least 4", not "more than 4".

**4. Built-in functional interfaces**
- [ ] Predicate (T → boolean), Function (T → R), Consumer (T → nothing), Supplier (nothing → T), BinaryOperator ((T, T) → T).
- [ ] Combine them: `p.and(q)`, `p.negate()`. `f.andThen(g)` runs f first, while `f.compose(g)` runs g first.

**5. Method references**
- [ ] The four kinds are `Class::static`, `object::method`, `Class::instanceMethod` (the element is the receiver) and `Class::new`.

**6-7. Intermediate and terminal operations**
- [ ] Intermediate operations return a new stream and never change the source list. Terminal operations end the stream.
- [ ] `reduce(identity, accumulator)`: the identity is 0 for a sum and 1 for a product.
- [ ] `min`, `max` and `findFirst` return an `Optional`, because the stream might be empty.
- [ ] `takeWhile` stops at the first element that fails, while `filter` checks every element.

**8. Collectors**
- [ ] `toMap` throws on a duplicate key unless you pass a merge function.
- [ ] `groupingBy(key)` makes buckets and `partitioningBy(condition)` makes exactly two (true and false). Add a second collector for `counting()`, `mapping()` or `maxBy()`.
- [ ] `stream.toList()` gives an unmodifiable list. `Collectors.toList()` gives a list you can change.

**9-10. flatMap and creating streams**
- [ ] `map` produces one output per element. `flatMap` produces a stream per element and merges them into one.
- [ ] `IntStream.range(a, b)` excludes `b`; `rangeClosed` includes it.
- [ ] Use `mapToInt(...).sum()` instead of boxing everything, and `boxed()` to go back to `List<Integer>`.
- [ ] A stream can only be used once. Using it again throws `IllegalStateException`.

**11. Optional**
- [ ] Never call a bare `get()`. Use `orElse`, `orElseGet` (lazy), `orElseThrow`, `ifPresent` or `ifPresentOrElse`.
- [ ] `Optional.of(null)` throws, so use `ofNullable` when the value may be null.
- [ ] Use `map` when the step returns a plain value and `flatMap` when it already returns an Optional.

**12-14. Laziness, higher-order functions, parallel streams**
- [ ] Nothing runs until a terminal operation. Each element passes through all the steps before the next element starts.
- [ ] `findFirst`, `limit` and `anyMatch` short-circuit, which makes infinite streams safe.
- [ ] A method can return a lambda (a function factory), so behaviour can be built from parameters.
- [ ] Use `parallel()` only for large, CPU-heavy, independent work. Never modify shared state from inside it; let the stream collect the result.

## Status
✅ Working. All 15 topics compile on JDK 21 and every `main` runs. `TerminalOperations`, `OptionalChaining`, `ParallelStreams` and `CourseAnalysis` check themselves with asserts when run with `-ea`.
