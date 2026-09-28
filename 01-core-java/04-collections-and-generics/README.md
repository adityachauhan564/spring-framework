# Module 04 · Collections and generics

**Difficulty:** Intermediate · **Needs:** [03 OOP](../03-oop/) · **Next:** [05 Streams](../05-streams/)

## Why this module
Almost every program stores groups of things, and picking the right container decides how easy and how fast the rest of the code is:
- a list of orders;
- a set of tags;
- a map from email to user;
- a queue of jobs.

This module covers the collections you'll use every day, the generics that make them type-safe, and how to sort your own objects. The two ideas to take away: choose a collection by **what you need to ask it**, and know **how a HashMap works** inside.

| # | Topic | You'll be able to |
|---|---|---|
| 23 | [Lists and iteration](./topic23_lists_and_iteration/) | use lists correctly, and remove while looping without errors |
| 24 | [Generics](./topic24_generics/) | write type-safe classes and methods for any type |
| 25 | [Sets](./topic25_sets/) | keep unique elements, sorted or in order |
| 26 | [Maps and hashing](./topic26_maps_and_hashing/) | do key → value lookups, count, group, and explain hashing |
| 27 | [Queues and deques](./topic27_queues_and_deques/) | model FIFO, LIFO and priority order |
| 28 | [Sorting](./topic28_sorting/) | sort your own objects by one or more keys |

**Which collection?**

| You need | Use |
|---|---|
| order, index access, duplicates allowed | `ArrayList` |
| no duplicates | `HashSet` (`LinkedHashSet` for insertion order, `TreeSet` for sorted) |
| a lookup by key | `HashMap` (`LinkedHashMap` / `TreeMap` for order) |
| first in, first out, or a stack | `ArrayDeque` |
| always the smallest or most urgent next | `PriorityQueue` |

## Compile and run
From this folder (`01-core-java/04-collections-and-generics`):
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -ea -cp out topic26_maps_and_hashing.MyHashMap
java -cp out topic26_maps_and_hashing.Exercises
```
Each topic works the same way as in [module 01](../01-java-basics/#how-to-work-through-a-topic).

## Module checklist
- [ ] I declare collections with the interface type, and know `List.of` / `Arrays.asList` / `ArrayList` apart.
- [ ] I can explain `<T extends Comparable<T>>` and `? extends Number`.
- [ ] I can pick between the Hash, LinkedHash and Tree versions of Set and Map.
- [ ] I can explain how a HashMap finds a key, and count with `merge`.
- [ ] I use `ArrayDeque` for stacks and queues.
- [ ] I can sort by several keys with a `Comparator`.
