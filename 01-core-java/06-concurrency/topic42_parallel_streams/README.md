# Topic 42 · Parallel streams

**Difficulty:** Advanced · **Needs:** [40 Synchronization](../topic40_synchronization/), [36 Laziness](../../05-streams/topic36_laziness_and_pipeline/) · **Next:** [43 CompletableFuture and concurrent collections](../topic43_completable_future_and_concurrent_collections/)

## Why it matters
Adding `.parallel()` to a stream spreads the work over every CPU core, in one word. It's tempting to add it everywhere, and it's often wrong: for small or I/O-bound work it's **slower**, and with shared mutable state it gives wrong answers. This topic puts module 05's streams together with module 06's threads, so you know exactly when the one word helps.

## What you'll learn
- `parallel()` / `parallelStream()`, and timing sequential vs parallel
- `forEach` (any order) vs `forEachOrdered` (encounter order)
- The shared-state bug, and why collectors or `toList()` are safe

## Run it
From `06-concurrency` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -ea -cp out topic42_parallel_streams.ParallelStreams
```
Timings vary per run and per machine. Change `N` to `1_000` and compare.

## Key concepts
- **Where it runs:** parallel streams use the common fork-join pool, one thread per core by default.
- **When it helps:** only when **all** of these are true:
  1. **lots** of data;
  2. **CPU-heavy** work per element;
  3. **independent** elements, with no shared mutable state;
  4. a source that splits easily (arrays, ranges, `ArrayList`).
- **Build results with the stream:** `toList()` and collectors combine partial results safely. Never `add` to a shared list from inside `forEach`.
- **Order:** `forEach` may run in any order. `forEachOrdered` and `toList()` keep the encounter order.

## Exercises
`Exercises.java` (run `java -cp out topic42_parallel_streams.Exercises`):
1. Count primes in parallel
2. Fix a buggy parallel pipeline that writes to a shared list

## Common mistakes
- Mutating a shared list or counter from a parallel stream.
- `parallel()` on tiny inputs, or on work that waits on I/O. Use an executor ([41](../topic41_executors_and_futures/)) instead.
- Expecting `forEach` output in order.

## Related topics
- [36 Laziness](../../05-streams/topic36_laziness_and_pipeline/): the same pipeline, one thread
- [41 Executors](../topic41_executors_and_futures/): explicit control over threads

## Revision checklist
- [ ] I can give the conditions for using `parallel()`.
- [ ] I can explain why the shared `ArrayList` version is a bug.
- [ ] I know `forEach` vs `forEachOrdered`.
