# Head First Java - Hands On

> Core Java, one topic at a time: examples from *Head First Java* plus common interview questions.
> Every package is one topic, numbered in study order. Every file opens with a header comment: **Topic, Key idea, Run, Try this**.

## Run it
There's no build file. Run these with plain `javac`/`java` from this folder (Git Bash, macOS or Linux):

```bash
javac -d out $(find src -name "*.java")
java -ea -cp out topic02_classes_and_objects.GameLauncher     # -ea turns on the assert self-checks
```

To run every topic at once:

```bash
for c in topic01_loops_and_conditions.BottleSong topic02_classes_and_objects.GameLauncher \
         topic02_classes_and_objects.Song topic02_classes_and_objects.Employee \
         topic03_arraylist.ArrayListBasics topic03_arraylist.ArrayListOperations \
         topic04_abstract_classes.AbstractClassDemo topic05_interfaces_and_dependency_injection.Main \
         topic06_exceptions.CheckedExceptionDemo topic06_exceptions.UncheckedExceptionDemo \
         topic07_hashmap_and_optional.HashMapAndOptional topic07_hashmap_and_optional.MyHashMap \
         topic08_streams.PhoneNumberCleaner topic09_interview_problems.TwoSum; do
  echo "== $c"; java -ea -cp out "$c"
done
```

In Eclipse: *File > New > Java Project*, untick "use default location" and point it at this folder. Right-click any file with a `main` method and choose *Run As > Java Application*.

## Topics (study in this order)

| # | Package (`src/`) | Read these files in order | What you learn |
| :- | :--- | :--- | :--- |
| 1 | `topic01_loops_and_conditions` | `BottleSong` | `while` loop, `if/else`, a small helper method |
| 2 | `topic02_classes_and_objects` | `GameLauncher` → `GuessGame` → `Player`, then `Song`, `Employee` | Objects have state + behaviour; instance vs local variables; `this`; a good class template |
| 3 | `topic03_arraylist` | `ArrayListBasics` → `ArrayListOperations` | `List` vs `ArrayList`, add/get/set/remove/indexOf, safe removal with `removeIf` |
| 4 | `topic04_abstract_classes` | `Printer` → `ConsolePrinter` → `AbstractClassDemo` | Abstract methods, shared code, polymorphism |
| 5 | `topic05_interfaces_and_dependency_injection` | `PaymentService` → `UPIPayment` / `CreditCardPayment` → `OrderService` → `Main` | Program to an interface, constructor injection (what Spring automates later) |
| 6 | `topic06_exceptions` | `CheckedExceptionDemo` → `UncheckedExceptionDemo` | Checked vs unchecked, try-with-resources, prevent rather than catch |
| 7 | `topic07_hashmap_and_optional` | `HashMapAndOptional` → `MyHashMap` | Map basics, `null` keys, `Optional`; how a HashMap works inside (buckets + chaining) |
| 8 | `topic08_streams` | `PhoneNumberCleaner` | `filter` / `map` / `distinct` / `collect`; why the order of steps matters |
| 9 | `topic09_interview_problems` | `TwoSum` | Brute force O(n²) vs HashSet O(n) |

## Revision checklist
**1. Loops and conditions**
- [ ] A `while` loop checks its condition *before* each pass, so it can run zero times.
- [ ] Choose the singular or plural word from the current number each time. Don't store it once and forget to update it.

**2. Classes and objects**
- [ ] A class is the blueprint and an object is one instance built from it. Each object has its own copy of the instance variables.
- [ ] A variable declared inside `main` is local. It is not a field, so methods can't see it.
- [ ] `this.title = title` means the field on the left is set from the parameter on the right.
- [ ] A good class template: `private` fields, a constructor, getters, and `toString()`. Mark fields `final` when they never change.

**3. ArrayList**
- [ ] Declare as `List<String> list = new ArrayList<>();`, using the interface type on the left.
- [ ] `indexOf` returns `-1` when the item isn't found. `contains` compares with `equals` (for plain objects, that means "same object").
- [ ] Calling `remove()` inside a for-each loop throws `ConcurrentModificationException`. Use `removeIf` instead.

**4. Abstract classes**
- [ ] You can't create one with `new`. It can have both abstract methods and normal methods.
- [ ] In `Printer p = new ConsolePrinter();`, the variable's type decides what you *can* call and the object's type decides *which* code runs.

**5. Interfaces and dependency injection**
- [ ] `OrderService` only knows `PaymentService`, so you can add a new payment type without changing `OrderService`.
- [ ] Constructor injection means the dependency is passed in, not created inside with `new`.

**6. Exceptions**
- [ ] Checked (`IOException`): you must catch it or declare `throws`. Unchecked (`NullPointerException`): the compiler doesn't force you.
- [ ] Put the most specific `catch` first (`FileNotFoundException` before `IOException`).
- [ ] try-with-resources closes the reader for you, even when an error happens.
- [ ] Prevent an NPE with a null check. Catching it hides the bug.

**7. HashMap and Optional**
- [ ] `put` with an existing key replaces the value. HashMap allows one `null` key. Iteration order is not guaranteed.
- [ ] `getOrDefault` and `Optional.ofNullable(...).orElse(...)` / `ifPresentOrElse(...)` avoid null checks. Don't call a bare `get()` on an Optional.
- [ ] Inside a HashMap: `hashCode()` picks the bucket, `equals()` finds the key in that bucket, and collisions are chained in a list.
- [ ] Gotcha: `01` is an octal literal (`010` == 8).

**8. Streams**
- [ ] Intermediate steps (`filter`, `map`, `distinct`) are lazy. Nothing runs until a terminal step (`collect`).
- [ ] Order matters: drop nulls before `trim()`, and strip non-digits before checking the length.

**9. Interview problems**
- [ ] TwoSum: for each `x`, ask "have I already seen `target - x`?" That's one pass with a HashSet, O(n).
- [ ] Always test the edge cases: no pair, and a number paired with itself.

## Status
✅ Working. Everything compiles, and every `main` runs. `MyHashMap`, `PhoneNumberCleaner` and `TwoSum` check themselves with asserts when run with `-ea`.
