# Topic 54 · Linked lists and two pointers

**Difficulty:** Intermediate · **Needs:** [19 Nested classes](../../03-oop/topic19_nested_and_anonymous_classes/), [52 Complexity](../topic52_complexity/) · **Next:** [02 Spring Foundations](../../../02-spring-foundations/)

## Why it matters
A linked list is the simplest data structure made of **references**: each node points to the next. Working with one trains the exact skill that trips people up everywhere else, following and re-pointing references without losing part of the structure. The "fast and slow pointer" trick solves a whole family of problems (cycles, middles, n-th from the end) in one pass and constant memory, and interviewers love it.

## What you'll learn
- Nodes, `next` references, and walking a list
- Unlinking a node (`previous.next = current.next`) and the dummy-node trick
- **Fast and slow pointers:** Floyd's cycle detection, and the middle of a list
- Comparing nodes by identity (`==`) vs by value

## Run it
From `10-dsa` (compile first: `javac -d out $(find . -name "*.java")`), with `-ea` so the self-checks run:
```bash
java -ea -cp out topic54_linked_lists_and_two_pointers.RemoveDuplicates     # Cracking the Coding Interview 2.1
java -ea -cp out topic54_linked_lists_and_two_pointers.LinkedListCycle      # LeetCode 141
java -ea -cp out topic54_linked_lists_and_two_pointers.MiddleOfLinkedList   # LeetCode 876
```
`ListNode.of(1, 2, 3)` builds a test list in one line.

## Key concepts
- **Removing a node:** point the node **before** it past it. A dummy node in front of the head means the first node needs no special case.
- **Remove duplicates:** remember the values seen in a HashSet, for O(n) time and O(n) space. Without a set it's O(n²).
- **Cycle (Floyd):** slow moves 1, fast moves 2. If there's a cycle they meet, because fast gains one step each move. If not, fast reaches `null`. O(1) memory.
- **Middle:** the same two pointers. When fast runs off the end, slow is halfway; for an even count it stops at the second middle.
- **Identity:** compare nodes with `==`, since two different nodes may hold the same value.

## Exercises
`Exercises.java` (run `java -cp out topic54_linked_lists_and_two_pointers.Exercises`):
1. Reverse a list
2. Remove the n-th node from the end in one pass
3. Merge two sorted lists

## Common mistakes
- Losing the rest of the list by overwriting `next` before saving it (in reversing).
- `fast.next.next` without checking `fast.next != null`, which gives a `NullPointerException`.
- Forgetting the empty-list and one-node cases.
- Moving `previous` past a node you just removed.

## Related topics
- [19 Nested classes](../../03-oop/topic19_nested_and_anonymous_classes/): `LinkedStack` built from nodes
- [27 Queues](../../04-collections-and-generics/topic27_queues_and_deques/): queues and stacks built on the same idea
- [48 JVM memory](../../08-advanced-java/topic48_jvm_memory/): references on the heap

## Revision checklist
- [ ] I can remove a node from a linked list, including the first one.
- [ ] I can explain why Floyd's fast and slow pointers must meet in a cycle.
- [ ] I can reverse a list without losing nodes.
