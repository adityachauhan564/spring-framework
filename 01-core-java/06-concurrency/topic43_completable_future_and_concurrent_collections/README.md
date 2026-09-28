# Topic 43 · CompletableFuture and concurrent collections

**Difficulty:** Advanced · **Needs:** [41 Executors and futures](../topic41_executors_and_futures/) · **Next:** [44 File I/O](../../07-java-apis/topic44_file_io/)

## Why it matters
A page that needs a price from one service and a discount from another shouldn't wait for them one after the other, and shouldn't block a thread while it waits. `CompletableFuture` describes async work as a pipeline: start both, combine the results, recover from failure. It's the style behind non-blocking web clients and the calls between microservices in stage 04. Once several threads share a map or a list, you also need collections built for it.

## What you'll learn
- `supplyAsync`, `thenApply`, `thenCombine`, `exceptionally`, `join`
- Running independent calls at the same time and combining the results
- `ConcurrentHashMap`, whose `merge` and `computeIfAbsent` are atomic, and the other concurrent collections

## Run it
From `06-concurrency` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic43_completable_future_and_concurrent_collections.CompletableFutureDemo
java -cp out topic43_completable_future_and_concurrent_collections.ConcurrentCollections
```

## Key concepts
- **Starting work:** `supplyAsync(supplier)` starts at once on a pool thread and returns a `CompletableFuture<T>`.
- **Chaining:**
  - `thenApply(f)` transforms the result when it arrives;
  - `thenCombine(other, f)` waits for **both** and combines them;
  - `thenCompose(f)` chains another async step.
- **Errors:** `exceptionally(e -> fallback)` recovers from a failure anywhere earlier in the chain.
- **Waiting:** `join()` waits for the result. Call it as **late** as possible, at the very end.
- **Concurrent collections:** `ConcurrentHashMap` is safe for concurrent use, and its compound operations are atomic. Others include `CopyOnWriteArrayList` (for mostly reads) and `BlockingQueue` (producer/consumer).

## Exercises
`Exercises.java` (run `java -cp out topic43_completable_future_and_concurrent_collections.Exercises`):
1. Two price lookups at the same time; the check fails if they don't overlap
2. A fallback for a failed future
3. A concurrent word count

## Common mistakes
- Calling `join()` or `get()` too early, which makes the code blocking and sequential again.
- `if (!map.containsKey(k)) map.put(k, v)` on a `ConcurrentHashMap`: two separate steps, so not atomic. Use `putIfAbsent` or `computeIfAbsent`.
- Swallowing exceptions in async code: without `exceptionally` or `handle`, failures disappear.

## Related topics
- [41 Executors](../topic41_executors_and_futures/): `Future`, the blocking ancestor
- [04 Microservices](../../../04-microservices/): services calling each other

## Revision checklist
- [ ] I can explain Future vs CompletableFuture.
- [ ] I can combine two async results without blocking.
- [ ] I know which `ConcurrentHashMap` operations are atomic.
