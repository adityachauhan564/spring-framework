# Topic 26 · Maps and hashing

**Difficulty:** Intermediate · **Needs:** [25 Sets](../topic25_sets/) · **Next:** [27 Queues and deques](../topic27_queues_and_deques/)

## Why it matters
Look up a price by product code, a user by email, a count by word. A `Map` connects keys to values and finds any key almost instantly, however many there are. It's one of the most-used types in any Java codebase, and "how does a HashMap work inside?" is one of the most-asked interview questions. `MyHashMap` answers it in 100 lines you can read.

## What you'll learn
- `put`, `get`, `getOrDefault`, `containsKey`, `putIfAbsent`, `remove`, and iterating with `entrySet`
- Counting with `merge`, and grouping with `computeIfAbsent`
- `HashMap` vs `LinkedHashMap` vs `TreeMap`, plus TreeMap extras like `floorEntry`
- **Inside a HashMap:** buckets, `hashCode`, `equals`, and collisions resolved by chaining

## Run it
From `04-collections-and-generics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic26_maps_and_hashing.HashMapBasics
java -cp out topic26_maps_and_hashing.MapsDemo
java -ea -cp out topic26_maps_and_hashing.MyHashMap      # -ea turns on its self-checks
```

## Key concepts
- **Keys:** a key appears once. Putting it again replaces the value. A missing key gives `null` from `get`, so use `getOrDefault`.
- **Counting:** `map.merge(key, 1, Integer::sum)` is the one-line way to count.
- **Grouping:** `computeIfAbsent(key, k -> new ArrayList<>()).add(x)` groups items into lists.
- **The three Maps:** the same trio as Sets. Hash has no order, LinkedHash keeps insertion order, and Tree keeps keys sorted.
- **Inside:**
  1. `hashCode()` picks a bucket in an array.
  2. `equals()` finds the key among the entries in that bucket.
  3. Keys that share a bucket (a collision) are chained in a small list.
  
  That's why keys need correct [`equals`/`hashCode`](../../03-oop/topic15_equals_and_hashcode/).

## Exercises
`Exercises.java` (run `java -cp out topic26_maps_and_hashing.Exercises`):
1. Character frequency, sorted
2. The first unique character (which Map keeps order?)
3. Group words by length

A bigger one on your own: add resizing to `MyHashMap`. When `size > 0.75 * buckets.length`, double the array and re-insert every entry.

## Common mistakes
- A mutable key whose `hashCode` changes after it was put in, so it can never be found again.
- `null` checks everywhere instead of `getOrDefault` or `merge`.
- Iterating a `HashMap` and expecting insertion order.
- `map.remove(...)` inside a for-each over the map. Use `iterator.remove()` or `removeIf` on `entrySet()`.

## Related topics
- [15 equals and hashCode](../../03-oop/topic15_equals_and_hashcode/): the contract keys rely on
- [32 Collectors](../../05-streams/topic32_collectors/): `groupingBy` and `toMap` build maps from streams
- [35 Optional](../../05-streams/topic35_optional/): an explicit "maybe a value" instead of `null`

## Revision checklist
- [ ] I can explain how `get` finds a key: bucket, then `equals`.
- [ ] I can count with `merge` and group with `computeIfAbsent`.
- [ ] I can pick between HashMap, LinkedHashMap and TreeMap.
