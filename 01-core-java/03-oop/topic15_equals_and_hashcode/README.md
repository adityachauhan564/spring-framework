# Topic 15 · Object methods: equals, hashCode, toString

**Difficulty:** Intermediate · **Needs:** [12 Inheritance and polymorphism](../topic12_inheritance_and_polymorphism/) · **Next:** [16 Enums](../topic16_enums/)

## Why it matters
Every class inherits `equals`, `hashCode` and `toString` from `Object`, and the inherited versions are rarely what you want. By default two `Book` objects with the same ISBN are "different", so a `HashSet` keeps duplicates and `list.contains(...)` says no. Getting the `equals`/`hashCode` pair right is what makes your objects work in collections. Getting it half right is a classic, hard-to-find bug.

## What you'll learn
- What the default `equals` means (the same object) and how to make it mean the same data
- The `equals`/`hashCode` contract, and why HashSet and HashMap depend on it
- Writing both with `instanceof` and `Objects.hash`, plus a readable `toString`

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -ea -cp out topic15_equals_and_hashcode.EqualsAndHashCode    # -ea turns on its assert checks
```

## Key concepts
- **The contract:** if `a.equals(b)`, then `a.hashCode() == b.hashCode()`. The reverse isn't required: different objects may share a hash.
- **Why the pair matters:** a `HashSet`/`HashMap` uses `hashCode()` to pick a bucket, then `equals()` inside it. With `equals` but no `hashCode`, equal objects land in different buckets and are never compared.
- **Writing `equals`:**
  1. `this == other`
  2. `instanceof` (this also handles `null`)
  3. compare the fields that define identity
- **Writing `hashCode`:** `Objects.hash(...)` over **the same fields** as `equals`.

## Exercises
`Exercises.java` (run `java -cp out topic15_equals_and_hashcode.Exercises`):
1. `Book.equals`, based on the ISBN only
2. A matching `hashCode`, checked with a `HashSet`
3. `toString`

## Common mistakes
- Overriding only one of the two.
- Using different fields in `equals` and `hashCode`.
- `equals(Book other)` instead of `equals(Object other)`, which overloads instead of overriding. `@Override` catches it.
- Using a mutable field in `hashCode` and changing it while the object is in a `HashSet`.

## Related topics
- [17 Records](../topic17_records_and_immutability/): records generate all three for you
- [25 Sets](../../04-collections-and-generics/topic25_sets/), [26 Maps and hashing](../../04-collections-and-generics/topic26_maps_and_hashing/): where the contract is used

## Revision checklist
- [ ] I can state the `equals`/`hashCode` contract in one sentence.
- [ ] I can explain what happens in a `HashSet` when only `equals` is overridden.
- [ ] I can write a correct `equals` that handles `null` and other types.
