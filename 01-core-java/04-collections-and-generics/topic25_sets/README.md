# Topic 25 · Sets

**Difficulty:** Intermediate · **Needs:** [15 equals and hashCode](../../03-oop/topic15_equals_and_hashcode/), [24 Generics](../topic24_generics/) · **Next:** [26 Maps and hashing](../topic26_maps_and_hashing/)

## Why it matters
"Has this user already voted?", "which tags do these two posts share?", "remove the duplicates". A `List` answers these slowly: `contains` checks every element. A `Set` holds each value **once**, and answers "is it there?" almost instantly. Choosing between the three main Sets is also the first time you choose a collection by its performance and ordering, which is exactly the choice you'll make for Maps next.

## What you'll learn
- `HashSet` (fastest, no order), `LinkedHashSet` (insertion order), `TreeSet` (sorted)
- `add` returning `false` for a duplicate
- Set operations: union (`addAll`), intersection (`retainAll`), difference (`removeAll`)

## Run it
From `04-collections-and-generics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic25_sets.SetsDemo
```

## Key concepts
- **Duplicates:** a Set never holds two elements that are `equals`. For your own classes, that means [`equals` and `hashCode`](../../03-oop/topic15_equals_and_hashcode/) must be right.
- **The three Sets:** `HashSet` has O(1) `add` and `contains` and no guaranteed order. `LinkedHashSet` remembers insertion order. `TreeSet` keeps its elements sorted (O(log n)), and they must be `Comparable` or you pass a `Comparator`.
- **Set operations:** they change the set they're called on, so copy first if you need the original.
- **`Set.of(...)`** is immutable, like `List.of`.

## Exercises
`Exercises.java` (run `java -cp out topic25_sets.Exercises`):
1. Detect a duplicate in one pass
2. The words common to two sentences, sorted
3. The distinct items in first-seen order

## Common mistakes
- Expecting a `HashSet` to keep order.
- Putting objects without `equals`/`hashCode` in a HashSet, so "duplicates" stay.
- Changing a field used by `hashCode` while the object is inside a set.
- `retainAll` on the original set when you still need it.

## Related topics
- [26 Maps and hashing](../topic26_maps_and_hashing/): the same three flavours, for key → value
- [53 Arrays and hashing patterns](../../10-dsa/topic53_arrays_and_hashing/): Sets in interview problems

## Revision checklist
- [ ] I can say which Set keeps elements sorted, and which keeps insertion order.
- [ ] I know what `add` returns for a duplicate.
- [ ] I can compute a union and an intersection.
