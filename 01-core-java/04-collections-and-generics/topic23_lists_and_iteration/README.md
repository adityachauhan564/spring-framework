# Topic 23 · Lists and iteration

**Difficulty:** Intermediate · **Needs:** [22 Method references](../../03-oop/topic22_method_references/), [06 Arrays](../../01-java-basics/topic06_arrays/) · **Next:** [24 Generics](../topic24_generics/)

## Why it matters
An array's size is fixed when it's created, and real data isn't: users sign up, orders arrive, items get deleted. `List` is the collection you'll use more than any other, because it keeps order, grows and shrinks, and has methods for everything. The mistakes are subtle ones: removing while looping, a list that looks changeable but throws, the wrong implementation for the job.

## What you'll learn
- `List` vs `ArrayList`, and declaring with the interface type
- `add`, `get`, `set`, `remove`, `indexOf`, `contains`, `isEmpty`, and looping
- Removing safely: `removeIf` or an `Iterator`, never `remove()` inside a for-each loop
- `ArrayList` vs `LinkedList`
- `new ArrayList<>()` vs `Arrays.asList` vs `List.of`

## Run it
From `04-collections-and-generics` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic23_lists_and_iteration.ArrayListBasics         # Head First Java chapter 6
java -cp out topic23_lists_and_iteration.ArrayListOperations
java -cp out topic23_lists_and_iteration.ListImplementations
```

## Key concepts
- **Declare with the interface:** `List<String> names = new ArrayList<>();`. The rest of the code then doesn't depend on the implementation.
- **`indexOf` and `contains`:** they use `equals`, and `indexOf` returns `-1` when the item isn't there.
- **Removing while looping:** `remove()` inside a for-each throws `ConcurrentModificationException`. Use `removeIf(...)` or `iterator.remove()`.
- **`ArrayList`** is fast at `get(i)` and slow at inserting at the front. **`LinkedList`** is the opposite. `ArrayList` is the right default.
- **Three kinds of list:**

  | Made with | Can change? |
  |---|---|
  | `new ArrayList<>(...)` | fully |
  | `Arrays.asList(...)` | fixed size: `set` works, `add` / `remove` throw |
  | `List.of(...)` | never, it's immutable |

## Exercises
`Exercises.java` (run `java -cp out topic23_lists_and_iteration.Exercises`):
1. Remove duplicates while keeping the order
2. Remove the negative numbers in place
3. Swap the first and last elements

## Common mistakes
- `remove()` inside a for-each loop.
- `list.remove(1)` on a `List<Integer>` removes the element at **index** 1, not the value 1. Use `remove(Integer.valueOf(1))`.
- Calling `add` on `List.of(...)` or `Arrays.asList(...)`.
- `contains` on objects without `equals`: see [15](../../03-oop/topic15_equals_and_hashcode/).

## Related topics
- [24 Generics](../topic24_generics/): what `<String>` means
- [25 Sets](../topic25_sets/): when duplicates aren't allowed
- [29 Streams](../../05-streams/topic29_structured_vs_functional/): processing a list declaratively

## Revision checklist
- [ ] I can explain why variables are declared with the interface type.
- [ ] I can remove elements while going through a list without an exception.
- [ ] I can say which of `ArrayList`, `Arrays.asList` and `List.of` can grow.
