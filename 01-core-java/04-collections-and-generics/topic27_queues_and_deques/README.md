# Topic 27 · Queues and deques

**Difficulty:** Intermediate · **Needs:** [24 Generics](../topic24_generics/) · **Next:** [28 Sorting](../topic28_sorting/)

## Why it matters
Some problems care about **order of arrival** rather than position:
- a print queue: first come, first served;
- an undo history: the last thing done is undone first;
- a to-do list where the most urgent task always comes out next.

Queues, stacks and priority queues model exactly these. You'll meet them again in algorithms (breadth-first search, bracket matching) and in systems, like the task queue behind a thread pool or the Kafka topic in [showtime](../../../05-applications/showtime/).

## What you'll learn
- Queue (FIFO): `offer`, `poll`, `peek`
- A stack (LIFO) with `ArrayDeque`: `push`, `pop`
- `PriorityQueue`, which always hands out the smallest element first
- `poll` vs `remove` on an empty queue

## Run it
From `04-collections-and-generics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic27_queues_and_deques.QueuesAndDeques
```

## Key concepts
- **FIFO:** first in, first out, like a line at a shop. **LIFO:** last in, first out, like a pile of plates.
- **`ArrayDeque`** is both: a queue (`offer` / `poll`) and a stack (`push` / `pop`), with both ends open (`offerFirst`, `pollLast`, ...). Use it instead of the old `Stack` class.
- **`PriorityQueue`** is a heap. `poll()` always returns the smallest element (or the first by your `Comparator`), whatever the insertion order. Printing it does **not** show sorted order.
- **Empty queues:** `poll` and `peek` return `null`, while `remove` and `element` throw.

## Exercises
`Exercises.java` (run `java -cp out topic27_queues_and_deques.Exercises`):
1. Balanced brackets with a stack
2. The k smallest values with a `PriorityQueue`
3. A print queue where urgent jobs jump to the front

## Common mistakes
- Using the old `Stack` class (synchronized and legacy) or `LinkedList` where `ArrayDeque` fits.
- Expecting `System.out.println(priorityQueue)` to be sorted.
- Calling `remove()` on an empty queue when `poll()` was meant.

## Related topics
- [41 Executors](../../06-concurrency/topic41_executors_and_futures/): a thread pool is a queue of tasks
- [54 Linked lists](../../10-dsa/topic54_linked_lists_and_two_pointers/): how a queue can be built from nodes

## Revision checklist
- [ ] I can give a real-world use for a stack and for a queue.
- [ ] I use `ArrayDeque` for both.
- [ ] I know what `poll` returns on an empty queue.
