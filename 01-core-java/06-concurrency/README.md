# Module 06 · Concurrency

**Difficulty:** Advanced · **Needs:** [03 OOP (lambdas)](../03-oop/topic20_lambdas_and_functional_interfaces/), [05 Streams](../05-streams/) · **Next:** [07 Java APIs](../07-java-apis/)

## Why this module
Servers handle many users at once, and slow calls shouldn't block everything else. That needs several threads, and threads that share data bring the hardest bugs in programming: results that are wrong only sometimes. This module goes from starting a thread to the three classic failures (lost updates, deadlock, invisible changes) and their fixes, then to the tools production code uses: thread pools, parallel streams, `CompletableFuture` and concurrent collections.

| # | Topic | You'll be able to |
|---|---|---|
| 39 | [Threads](./topic39_threads/) | run work at the same time, and wait for it |
| 40 | [Synchronization and race conditions](./topic40_synchronization/) | keep shared data correct, and avoid deadlock |
| 41 | [Executors and futures](./topic41_executors_and_futures/) | run tasks on a thread pool and collect their results |
| 42 | [Parallel streams](./topic42_parallel_streams/) | know when `parallel()` helps and when it hurts |
| 43 | [CompletableFuture and concurrent collections](./topic43_completable_future_and_concurrent_collections/) | combine async results, and share collections safely |

## Compile and run
From this folder (`01-core-java/06-concurrency`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -ea -cp out topic40_synchronization.RaceConditionDemo
java -cp out topic40_synchronization.Exercises
```
Run the demos more than once: thread timing, and so the output, changes between runs. That's the point.

## Module checklist
- [ ] I call `start()`, not `run()`, and `join()` before reading results.
- [ ] I can explain why `count++` loses updates, and fix it with `synchronized` or `AtomicInteger`.
- [ ] I can describe a deadlock and prevent it with lock ordering.
- [ ] I use a thread pool, submit everything before calling `get()`, and shut the pool down.
- [ ] I can name the conditions for `parallel()`.
- [ ] I can combine two async results with `thenCombine`, and use `ConcurrentHashMap.merge`.
