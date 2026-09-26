# DSA & Java 8 Interview Practice

> DSA patterns (Cracking the Coding Interview + LeetCode-style problems) and Java 8 lambdas / Stream API notes.

## What it teaches
- Kadane's algorithm (maximum subarray sum) and maximum product subarray
- Linked lists: building a list by hand, removing duplicates with a `HashSet`
- Fast & slow pointers (problem statements only so far)
- Lambdas, functional interfaces, `Runnable` threads with lambdas
- Stream API: `filter` + `collect` on lists

## Run it
No build file. There are two source roots: `src/` (Java 8 topics) and `cracking-the-coding-interview_DSA_Patterns/` (DSA). From this folder:

```bash
javac -d out $(find src cracking-the-coding-interview_DSA_Patterns -name "*.java")
java -ea -cp out kadane.MaximumSubarray          # -ea turns on the assert checks
java -cp out Chapter_1_Arrays_And_Strings.sub_array
java -cp out Chapter_2_Linked_Lists.Main
java -cp out Lambda_Expression.Lambda
java -cp out Lambda_Expression.ThreadDemo
java -cp out StreamAPI.StreamMain1
```

In Eclipse, add both folders as source folders (*Build Path > Use as Source Folder*).

## Read the code in this order
1. `cracking-the-coding-interview_DSA_Patterns/kadane/MaximumSubarray.java` - Kadane, O(n) time / O(1) space, with asserts
2. `cracking-the-coding-interview_DSA_Patterns/Chapter_1_Arrays_And_Strings/sub_array.java` - max product subarray (track max **and** min)
3. `cracking-the-coding-interview_DSA_Patterns/Chapter_2_Linked_Lists/LinkedListNode.java`, `RemoveDupes.java`, `Main.java`
4. `cracking-the-coding-interview_DSA_Patterns/Fast_And_Slow_Pointers/LinkedList_Cycle.java` - problem statement (LeetCode 141)
5. `src/Lambda_Expression/MyInterface.java`, `Lambda.java` - functional interface -> impl class -> anonymous class -> lambda
6. `src/Lambda_Expression/ThreadDemo.java` - `Runnable` as a lambda
7. `src/StreamAPI/StreamMain1.java` - `List.of` vs `ArrayList` vs `Arrays.asList`, then `filter`/`collect`

## Revision notes
- Kadane: `current = max(arr[i], current + arr[i])`, `best = max(best, current)`. Start both at `arr[0]` so all-negative arrays work.
- Max product subarray: a negative number swaps max and min, so keep both (`temp` holds the old max).
- Remove duplicates from an unsorted list: `HashSet` gives O(n) time / O(n) space; without extra space it is O(n^2) with two pointers.
- Linked list cycle (Floyd): slow moves 1, fast moves 2; if they meet there is a cycle. O(1) memory.
- A functional interface has exactly one abstract method; `@FunctionalInterface` makes the compiler enforce it.
- `List.of(...)` is immutable, `Arrays.asList(...)` is fixed-size (set allowed, add/remove not), `new ArrayList<>()` is fully mutable.
- Two threads started with `start()` interleave; calling `run()` directly would run on the main thread.

## Status
🚧 Partial.
- ✅ `kadane/MaximumSubarray` (fixed, asserts pass), `sub_array`, `RemoveDupes`, all `src/` examples.
- 📝 Practice stubs (marked `// TODO: not implemented yet`): `Chapter_1_Arrays_And_Strings/sortColours.java`, `Fast_And_Slow_Pointers/LinkedList_Cycle.java`, `Fast_And_Slow_Pointers/MiddleOfLinkedList.java`.
- `Chapter_2_Linked_Lists/Main.printList` uses `println`, so each node prints on its own line.
- There is no Sliding Window section yet.
