# Topic 24 · Generics

**Difficulty:** Intermediate · **Needs:** [23 Lists and iteration](../topic23_lists_and_iteration/) · **Next:** [25 Sets](../topic25_sets/)

## Why it matters
`List<String>` has been everywhere since topic 23. The `<String>` is generics: it tells the compiler what the list holds, so putting an `Integer` in is a compile error rather than a `ClassCastException` at runtime. Generics also let you write **one** class or method, like a `Box`, a `Pair` or a `max`, that works for any type while staying type-safe. Every collection and most of Spring's API use them.

## What you'll learn
- Generic classes (`Box<T>`, `Pair<K, V>`) and generic methods (`<T> T firstOrDefault(...)`)
- Bounded types: `<T extends Comparable<T>>`
- Wildcards: `List<? extends Number>`
- Why raw types (`List` without `<>`) are dangerous

## Run it
From `04-collections-and-generics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic24_generics.GenericsDemo
```

## Key concepts
- **Type parameters:** a type parameter is a placeholder that the caller fills in. `Box<String>` means "a Box where T is String", so `get()` returns a `String` with no cast.
- **Generic methods:** `<T>` goes **before** the return type.
- **Bounded types:** `<T extends Comparable<T>>` means "T can be compared to T", which is what allows `item.compareTo(best)`.
- **Wildcards:** `List<? extends Number>` accepts `List<Integer>`, `List<Double>` and so on, for **reading** as `Number`. A `List<Number>` parameter would not accept a `List<Integer>`.
- **Type erasure:** generics are checked at compile time and erased at runtime. That's why you can't write `new T()`.

## Exercises
`Exercises.java` (run `java -cp out topic24_generics.Exercises`):
1. A generic `swap` for any array
2. `Pair.swap()`, which returns `Pair<B, A>`
3. `countGreaterThan` with a bounded type
4. `maxValue` with a wildcard

## Common mistakes
- Raw types (`List list = new ArrayList();`): the compiler can no longer check anything.
- Expecting `List<Integer>` to be a `List<Number>`. It isn't; use `? extends Number`.
- Using primitives: it's `List<Integer>`, never `List<int>`.

## Related topics
- [23 Lists](../topic23_lists_and_iteration/), [26 Maps](../topic26_maps_and_hashing/): generic collections
- [28 Sorting](../topic28_sorting/): `Comparable<T>` and `Comparator<T>`

## Revision checklist
- [ ] I can explain what `<T extends Comparable<T>>` means.
- [ ] I can write a generic method with `<T>` in the right place.
- [ ] I know why `List<Integer>` isn't accepted where `List<Number>` is expected.
