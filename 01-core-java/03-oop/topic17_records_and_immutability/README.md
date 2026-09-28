# Topic 17 · Records and immutability

**Difficulty:** Intermediate · **Needs:** [15 equals and hashCode](../topic15_equals_and_hashcode/) · **Next:** [18 Custom exceptions](../topic18_custom_exceptions/)

## Why it matters
Topic 15 needed about 25 lines to give a class a correct `equals`, `hashCode` and `toString`. For a class that only carries data, a **record** writes all of that from one line. Records are also immutable, and immutability matters well beyond saving typing. An object that can't change can be shared freely, with other methods, other threads, or as a map key, without anyone breaking it behind your back. The one trap is a `final` field pointing at something mutable, like an array.

## What you'll learn
- Declaring a record, its generated members, and its accessors (`x()`, not `getX()`)
- Compact constructors for validation, and methods that return a new record
- The rules for an immutable class: `final` class, `private final` fields, no setters, defensive copies

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic17_records_and_immutability.RecordsDemo
java -cp out topic17_records_and_immutability.ImmutableClass
```

## Key concepts
- **What a record generates:** `record Point(int x, int y)` gives `private final` fields, a constructor, the accessors `x()` and `y()`, and `equals`/`hashCode`/`toString` over all the fields.
- **Compact constructors:** `Point { if (x < 0) throw ...; }` validates, and the assignments happen after it.
- **Changing a value:** you don't. Return a new object instead: `p.moveBy(1, 1)`.
- **`final` on a field** means the reference can't change. It says nothing about the object it points to.
- **Defensive copies:** a mutable field (an array, a list) needs a copy in the constructor **and** in the getter.

## Exercises
`Exercises.java` (run `java -cp out topic17_records_and_immutability.Exercises`):
1. Turn `Employee` into a record that rejects a blank name
2. An immutable `Money` whose `plus` returns a new object
3. A `Playlist` with defensive copies

## Common mistakes
- Exposing a mutable array or list from an "immutable" class.
- Calling `getX()` on a record: the accessor is `x()`.
- Using a record for something with an identity that changes over time, like a bank account. Records are for values.

## Related topics
- [15 equals and hashCode](../topic15_equals_and_hashcode/): what records generate
- [23 Lists and iteration](../../04-collections-and-generics/topic23_lists_and_iteration/): immutable lists (`List.of`)
- [40 Synchronization](../../06-concurrency/topic40_synchronization/): why immutable objects need no locks

## Revision checklist
- [ ] I can list what a record generates for me.
- [ ] I can make a class immutable, including a mutable field.
- [ ] I know why `final` alone doesn't make an array field safe.
