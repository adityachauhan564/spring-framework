# Topic 48 · JVM memory and garbage collection

**Difficulty:** Advanced · **Needs:** [08 Classes and objects](../../02-objects-and-classes/topic08_classes_and_objects/), [39 Threads](../../06-concurrency/topic39_threads/) · **Next:** [49 Design patterns](../topic49_design_patterns/)

## Why it matters
"Java manages memory for you" is only half true. You don't free memory by hand, but you still decide what stays **reachable**. Knowing where data lives explains several things at once:
- pass-by-value (topic 07);
- why deep recursion crashes with `StackOverflowError`;
- why a static cache that only grows eventually crashes a server with `OutOfMemoryError`.

It's also a favourite interview topic.

## What you'll learn
- The stack (one per thread: call frames and local variables) vs the heap (shared by all threads, objects)
- References: several variables, one object
- Garbage collection: which objects are eligible
- `StackOverflowError` vs `OutOfMemoryError`
- How leaks happen in Java, and how to avoid them

## Run it
From `08-advanced-java` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic48_jvm_memory.StackVsHeap
java -cp out topic48_jvm_memory.LeakDemo
```

## Key concepts
- **The stack:** each method call pushes a frame (parameters and local variables), and returning pops it. Each thread has a small, fixed-size stack, so runaway recursion gives `StackOverflowError`.
- **The heap:** `new` creates objects on the heap, and a variable of an object type holds only a **reference** to one.
- **Eligible for garbage collection:** an object is eligible when nothing reachable refers to it any more. Reachable means reachable from local variables, static fields, or running threads.
- **Leaks:** a *leak* in Java is an object that's still referenced but never used again. The usual suspects are static collections, caches without a size limit, and listeners that are never removed.
- **`System.gc()`** is only a request. Never rely on it in real code.

## Exercises
`Exercises.java` (run `java -cp out topic48_jvm_memory.Exercises`):
1. Replace a recursion that overflows with a loop
2. A size-limited LRU cache (`LinkedHashMap` + `removeEldestEntry`)
3. Fix a leaking event bus

## Common mistakes
- "Java has no memory leaks."
- Deep recursion on big inputs: use a loop, or an explicit stack ([27](../../04-collections-and-generics/topic27_queues_and_deques/)).
- Unbounded caches and static lists.
- Calling `System.gc()` to "fix" memory problems.

## Related topics
- [07 Methods](../../01-java-basics/topic07_methods/): pass-by-value, explained by the stack
- [40 Synchronization](../../06-concurrency/topic40_synchronization/): threads share the heap, not the stack

## Revision checklist
- [ ] I can say what makes an object eligible for GC.
- [ ] I can explain `StackOverflowError` vs `OutOfMemoryError`.
- [ ] I can name three ways a Java program leaks memory.
