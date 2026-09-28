# Topic 40 · Synchronization and race conditions

**Difficulty:** Advanced · **Needs:** [39 Threads](../topic39_threads/) · **Next:** [41 Executors and futures](../topic41_executors_and_futures/)

## Why it matters
The moment two threads touch the same data, correct-looking code starts giving wrong answers: a counter that's short, money that appears from nowhere, a flag one thread never sees change. These bugs don't happen on every run, which makes them the hardest to find. This topic covers the three classic problems, lost updates, deadlocks and visibility, and the standard tool for each.

## What you'll learn
- Race conditions: why `count++` isn't atomic
- `synchronized` methods and blocks, and `AtomicInteger`
- Deadlock, and preventing it with a fixed lock order
- `volatile` for visibility, and what it does **not** fix

## Run it
From `06-concurrency` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -ea -cp out topic40_synchronization.RaceConditionDemo    # run it a few times
java -cp out topic40_synchronization.DeadlockDemo
java -cp out topic40_synchronization.VolatileFlag
```

## Key concepts
- **Lost updates:** `count++` is three steps: read, add, write. Two threads can read the same value, so one update is lost.
- **`synchronized`:** only one thread at a time may hold an object's lock. `AtomicInteger.incrementAndGet()` gives the same safety without a lock, for a single variable.
- **Deadlock:** A waits for B while B waits for A, and neither moves again. The fix is to take locks in **one global order**, for example by id.
- **Visibility:** `volatile` makes a write visible to other threads. It doesn't make compound actions like `count++` atomic.
- **Immutable objects** ([17](../../03-oop/topic17_records_and_immutability/)) need no locks at all.

## Exercises
`Exercises.java` (run `java -cp out topic40_synchronization.Exercises`):
1. Make `HitCounter` correct under 4 threads
2. Make `Wallet.transfer` safe in both directions at once, without deadlocking

## Common mistakes
- Assuming `count++` is atomic.
- Synchronizing on different objects in different places, which protects nothing.
- Nested locks taken in different orders, which leads to deadlock.
- Using `volatile` to "fix" a counter.
- Holding a lock while doing slow work like I/O or sleeping.

## Related topics
- [41 Executors](../topic41_executors_and_futures/), [43 Concurrent collections](../topic43_completable_future_and_concurrent_collections/)
- [05 Applications, showtime](../../../05-applications/showtime/): the same lost-update problem in a database, solved with `@Version`

## Revision checklist
- [ ] I can explain `synchronized` vs `AtomicInteger`.
- [ ] I can describe a deadlock and the lock-ordering fix.
- [ ] I know what `volatile` guarantees and what it doesn't.
