# Topic 53 · Arrays and hashing patterns

**Difficulty:** Intermediate · **Needs:** [26 Maps and hashing](../../04-collections-and-generics/topic26_maps_and_hashing/), [52 Complexity](../topic52_complexity/) · **Next:** [54 Linked lists and two pointers](../topic54_linked_lists_and_two_pointers/)

## Why it matters
Most array interview problems have an obvious O(n²) answer (try every pair) and a clever O(n) one. The clever ones reuse a handful of patterns: "remember what you've seen" in a HashSet or HashMap, "carry the best-so-far" as in Kadane, and "a few pointers moving through the array". Learn the patterns, not the individual problems, and new problems start to look familiar.

## What you'll learn
- **Hash lookup:** TwoSum in O(n) instead of O(n²)
- **Running best:** Kadane's maximum subarray, and the max-product variant that tracks max **and** min
- **Pointers in place:** the Dutch national flag (sort 0s, 1s and 2s in one pass)

## Run it
From `10-dsa` (compile first: `javac -d out $(find . -name "*.java")`), with `-ea` so the self-checks run:
```bash
java -ea -cp out topic53_arrays_and_hashing.TwoSum                   # brute force vs HashSet
java -ea -cp out topic53_arrays_and_hashing.MaximumSubarray          # Kadane (LeetCode 53)
java -ea -cp out topic53_arrays_and_hashing.MaximumProductSubarray   # LeetCode 152
java -ea -cp out topic53_arrays_and_hashing.SortColours              # Dutch national flag (LeetCode 75)
```

## Key concepts
- **TwoSum:** for each `x`, ask "have I already seen `target - x`?" That's one pass and a HashSet: O(n) time, O(n) space.
- **Kadane:** at each index, either extend the previous subarray or start fresh: `current = max(x, current + x)`, and keep `best`. Start both at `arr[0]` so an all-negative array works.
- **Max product:** a negative number swaps the largest and the smallest product, so keep both. A zero resets the run.
- **Dutch national flag:** `low` / `mid` / `high` split the array into 0s, 1s, unseen and 2s. After swapping a 2 to `high`, don't move `mid`, because that value hasn't been checked yet.

## Exercises
`Exercises.java` (run `java -cp out topic53_arrays_and_hashing.Exercises`):
1. Move the zeros to the end in place (two pointers)
2. Count groups of anagrams (a hash key)
3. The longest consecutive run in O(n) (a HashSet)

## Common mistakes
- Pairing a number with itself in TwoSum: check before adding it to the set.
- Starting Kadane at 0, which returns 0 for all-negative input.
- Forgetting that `minHere * negative` can become the new maximum.
- Moving `mid` after swapping with `high`.

## Related topics
- [25 Sets](../../04-collections-and-generics/topic25_sets/), [26 Maps](../../04-collections-and-generics/topic26_maps_and_hashing/)
- [06 Arrays](../../01-java-basics/topic06_arrays/)

## Revision checklist
- [ ] I can solve TwoSum in O(n), and explain the space cost.
- [ ] I can write Kadane from memory, and say why max product needs both max and min.
- [ ] I can explain the three pointers of the Dutch national flag.
