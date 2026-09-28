# Topic 41 · Executors and futures

**Difficulty:** Advanced · **Needs:** [40 Synchronization](../topic40_synchronization/) · **Next:** [42 Parallel streams](../topic42_parallel_streams/)

## Why it matters
Creating a new `Thread` for every task is expensive, and doing it for 10,000 requests can bring a server down. Real code submits tasks to a **thread pool**: a fixed number of threads that take tasks from a queue. It also needs task **results**, not just side effects. `ExecutorService`, `Callable` and `Future` are the standard way to do both, and they're what web servers and Spring's `@Async` use underneath.

## What you'll learn
- `Executors.newFixedThreadPool(n)` and submitting tasks
- `Callable<T>` (returns a value, may throw) vs `Runnable`
- `Future.get()`: waiting for a result
- Shutting a pool down, with try-with-resources in Java 19+

## Run it
From `06-concurrency` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic41_executors_and_futures.ExecutorServiceDemo
```
Watch the thread names: 3 pool threads share 5 tasks.

## Key concepts
- **Pool size:** a pool of n threads runs at most n tasks at once, and the rest wait in its queue.
- **`submit`:** `submit(callable)` returns a `Future` at once. `future.get()` blocks until the result is ready, and rethrows the task's exception as an `ExecutionException`.
- **Submit, then wait:** submit **all** tasks first, then call `get()` on each. Calling `get()` straight after each `submit` makes the work sequential again.
- **Shut down:** always shut the pool down, or its threads keep the program alive. `try (ExecutorService pool = ...)` does it for you.

## Exercises
`Exercises.java` (run `java -cp out topic41_executors_and_futures.Exercises`): download 4 "pages" in parallel and total their sizes. The check fails if it isn't really parallel.

## Common mistakes
- Never shutting the pool down, so the program doesn't exit.
- `get()` inside the submit loop, which serialises the work.
- One huge pool for everything, or a new pool per request.
- Ignoring the `ExecutionException` that `get()` throws, and losing the task's error.

## Related topics
- [43 CompletableFuture](../topic43_completable_future_and_concurrent_collections/): combining results without blocking
- [27 Queues](../../04-collections-and-generics/topic27_queues_and_deques/): the task queue inside a pool

## Revision checklist
- [ ] I can explain why to use a pool instead of `new Thread`.
- [ ] I know what `Future.get()` does, and where to call it.
- [ ] I always shut a pool down.
