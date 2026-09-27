# DSA & Java 8 Interview Practice

> DSA patterns (Cracking the Coding Interview + LeetCode-style problems) and Java 8 lambdas / Stream API notes.

## What it teaches
- Kadane's algorithm (maximum subarray sum) and maximum product subarray
- Linked lists: building a list by hand, removing duplicates with a `HashSet`
- Fast & slow pointers: detecting a cycle (Floyd) and finding the middle in one pass
- Dutch National Flag: sorting 0s, 1s and 2s in one pass with three pointers
- Lambdas, functional interfaces, `Runnable` threads with lambdas
- Stream API: `filter` + `collect` on lists

## Run it
No build file. There are two source roots: `src/` (Java 8 topics) and `cracking-the-coding-interview_DSA_Patterns/` (DSA). From this folder:

```bash
javac -d out $(find src cracking-the-coding-interview_DSA_Patterns -name "*.java")
java -ea -cp out kadane.MaximumSubarray          # -ea turns on the assert checks
java -cp out chapter_1_arrays_and_strings.sub_array
java -ea -cp out chapter_1_arrays_and_strings.SortColours
java -ea -cp out fast_and_slow_pointers.LinkedListCycle
java -ea -cp out fast_and_slow_pointers.MiddleOfLinkedList
java -cp out chapter_2_linked_lists.Main
java -cp out lambda_expression.Lambda
java -cp out lambda_expression.ThreadDemo
java -cp out streamapi.StreamMain1
```

In Eclipse, add both folders as source folders (*Build Path > Use as Source Folder*).

## Read the code in this order
1. `cracking-the-coding-interview_DSA_Patterns/kadane/MaximumSubarray.java` - Kadane, O(n) time / O(1) space, with asserts
2. `cracking-the-coding-interview_DSA_Patterns/chapter_1_arrays_and_strings/sub_array.java` - max product subarray (track max **and** min)
3. `cracking-the-coding-interview_DSA_Patterns/chapter_1_arrays_and_strings/SortColours.java` - Dutch National Flag (LeetCode 75)
4. `cracking-the-coding-interview_DSA_Patterns/chapter_2_linked_lists/LinkedListNode.java`, `RemoveDupes.java`, `Main.java`
5. `cracking-the-coding-interview_DSA_Patterns/fast_and_slow_pointers/ListNode.java`, `LinkedListCycle.java` (LeetCode 141), `MiddleOfLinkedList.java` (LeetCode 876)
6. `src/lambda_expression/MyInterface.java`, `Lambda.java` - functional interface -> impl class -> anonymous class -> lambda
7. `src/lambda_expression/ThreadDemo.java` - `Runnable` as a lambda
8. `src/streamapi/StreamMain1.java` - `List.of` vs `ArrayList` vs `Arrays.asList`, then `filter`/`collect`

## Revision notes
- Kadane: `current = max(arr[i], current + arr[i])`, `best = max(best, current)`. Start both at `arr[0]` so all-negative arrays work.
- Max product subarray: a negative number swaps max and min, so keep both (`temp` holds the old max).
- Remove duplicates from an unsorted list: `HashSet` gives O(n) time / O(n) space; without extra space it is O(n^2) with two pointers.
- Linked list cycle (Floyd): slow moves 1, fast moves 2; if they meet there is a cycle. O(1) memory. Compare nodes with `==` (the same object), not their values.
- Middle of a list: the same two pointers; when fast runs off the end, slow is halfway. With an even count it stops at the second middle.
- Sort colours: `low`/`mid`/`high` split the array into 0s, 1s, unseen and 2s. After swapping a 2 to `high`, don't move `mid`: the value swapped in hasn't been checked yet.
- A functional interface has exactly one abstract method; `@FunctionalInterface` makes the compiler enforce it.
- `List.of(...)` is immutable, `Arrays.asList(...)` is fixed-size (set allowed, add/remove not), `new ArrayList<>()` is fully mutable.
- Two threads started with `start()` interleave; calling `run()` directly would run on the main thread.

## Status
✅ Working.
- `kadane/MaximumSubarray`, `SortColours`, `LinkedListCycle` and `MiddleOfLinkedList` run with `assert` self-checks that pass (`java -ea`).
- `sub_array`, `RemoveDupes` and all the `src/` examples run.
- The three former practice stubs are solved. `sortColours.java` and `LinkedList_Cycle.java` were renamed to Java class-naming style.
- `chapter_2_linked_lists/Main.printList` uses `println`, so each node prints on its own line.
- There is no Sliding Window section yet.
