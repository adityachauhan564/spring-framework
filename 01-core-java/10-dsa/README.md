# Module 10 · Data structures and algorithms (side track)

**Difficulty:** Intermediate · **Needs:** [04 Collections and generics](../04-collections-and-generics/) · **Next:** back to the main path, or [02 Spring Foundations](../../02-spring-foundations/)

## Why this module
This is a side track: nothing in the Spring stages depends on it, and you can start it any time after module 04. It trains two skills interviews test and good code needs: estimating how an algorithm scales (Big-O), and knowing the handful of patterns that turn an O(n²) solution into an O(n) one. The problems come from *Cracking the Coding Interview* and LeetCode.

| # | Topic | You'll be able to |
|---|---|---|
| 52 | [Complexity and Big-O](./topic52_complexity/) | compare solutions by how they scale |
| 53 | [Arrays and hashing patterns](./topic53_arrays_and_hashing/) | use HashSet lookups, Kadane and the Dutch national flag |
| 54 | [Linked lists and two pointers](./topic54_linked_lists_and_two_pointers/) | manipulate references, and use fast and slow pointers |

## Compile and run
From this folder (`01-core-java/10-dsa`), with `-ea` so the solutions check themselves:
```bash
javac -d out $(find . -name "*.java")            # PowerShell: javac -d out (Get-ChildItem -Recurse -Filter *.java).FullName
java -ea -cp out topic53_arrays_and_hashing.MaximumSubarray
java -cp out topic54_linked_lists_and_two_pointers.Exercises
```

**How to practise:** read the problem at the top of each file, close it, and solve it yourself before reading the solution. Then do the topic's `Exercises.java`.

## Module checklist
- [ ] I can classify code as O(1), O(log n), O(n), O(n log n) or O(n²), and explain time vs space.
- [ ] I can solve TwoSum in O(n), write Kadane, and explain the three pointers of the Dutch flag.
- [ ] I can remove, reverse and merge linked lists, and detect a cycle in O(1) memory.
