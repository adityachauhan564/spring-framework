# Topic 39 · Threads

**Difficulty:** Advanced · **Needs:** [20 Lambdas](../../03-oop/topic20_lambdas_and_functional_interfaces/) · **Next:** [40 Synchronization](../topic40_synchronization/)

## Why it matters
Until now your programs did one thing at a time. A web server serves many users at once, and a program downloading ten files shouldn't wait for each in turn. Threads are Java's unit of "at the same time". This topic is the foundation of the module: how to start work on another thread, how to wait for it, and why the order of output from different threads is never guaranteed.

## What you'll learn
- `Runnable`, and creating a `Thread` from it (a lambda works)
- `start()` vs `run()`
- `join()` to wait for a thread, and thread names

## Run it
From `06-concurrency` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic39_threads.ThreadBasics
```
Run it a few times: the order of the `worker-1` and `worker-2` lines changes.

## Key concepts
- **`Runnable`:** a task with no result, `() -> { ... }`, handed to `new Thread(task, name)`.
- **`start()` vs `run()`:** `start()` creates a **new** thread that runs the task. Calling `run()` just runs it on the current thread, like any method.
- **`join()`:** blocks until that thread has finished. Without it, `main` may read results that aren't written yet.
- **Scheduling:** the operating system decides when each thread runs, so interleaving is unpredictable and different on every run.
- **Isolation:** threads that each write their **own** data need no protection. Shared data does (next topic).

## Exercises
`Exercises.java` (run `java -cp out topic39_threads.Exercises`):
1. Run a task on a named thread and wait for it
2. Square an array with one thread per element, and join them all

## Common mistakes
- Calling `run()` instead of `start()`, which runs on the same thread.
- Starting a thread and immediately joining it inside the loop: that runs them one after another. Start all, then join all.
- Expecting output from different threads in a fixed order.

## Related topics
- [40 Synchronization](../topic40_synchronization/): when threads share data
- [41 Executors](../topic41_executors_and_futures/): how production code runs tasks instead
- [46 Sockets](../../07-java-apis/topic46_sockets/): a thread per client

## Revision checklist
- [ ] I can explain `start()` vs `run()`.
- [ ] I know what `join()` does, and when I need it.
- [ ] I know why thread output order varies.
