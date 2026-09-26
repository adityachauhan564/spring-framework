# Head First Java - Hands On

> Core Java, one topic at a time: examples from *Head First Java* plus common interview questions.
> Every package is one topic, numbered in study order. Every file opens with a header comment: **Topic, Key idea, Run, Try this**.

## Run it
There's no build file. Run these with plain `javac`/`java` from this folder (Git Bash, macOS or Linux):

```bash
javac -d out $(find src -name "*.java")
java -ea -cp out topic02_classes_and_objects.GameLauncher     # -ea turns on the assert self-checks
```

To run every program at once (finds each file with a `main` method):

```bash
for f in $(grep -rl "static void main" src | sort); do
  c=$(echo "${f#src/}" | sed 's|\.java$||; s|/|.|g')
  echo "== $c"; java -ea -cp out "$c"
done
```

In Eclipse: *File > New > Java Project*, untick "use default location" and point it at this folder. Right-click any file with a `main` method and choose *Run As > Java Application*.

## Topics

Topics 01-09 come from the book chapters. Topics 10-24 fill in the core Java fundamentals the book examples skip.

**Beginner path** (each step only uses things taught before it):
01 → 10 → 11 → 12 → 13 → 02 → 03 → 14 → 15 → 04 → 05 → 16 → 07 → 19 → 20 → 17 → 18 → 06 → 21 → 08 → 23 → 22 → 24 → 09

Lambdas, method references and streams in more depth are in [functional-programming](../functional-programming/) and [dsa-interview-practice](../dsa-interview-practice/).

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
| 10 | `topic10_data_types_and_operators` | `DataTypes` → `Operators` | 8 primitives, casting, overflow, wrappers/autoboxing, `/` and `%`, `++`, short-circuit, ternary |
| 11 | `topic11_strings` | `StringBasics` → `StringBuilderDemo` | String methods, immutability, String pool, `==` vs `equals`, StringBuilder |
| 12 | `topic12_arrays` | `ArraysDemo` → `TwoDimensionalArrays` | Fixed size, defaults, `Arrays` helpers, reference aliasing, 2D and jagged arrays |
| 13 | `topic13_methods_and_static` | `MethodsDemo` → `StaticVsInstance` | Overloading, varargs, recursion, pass-by-value; `static` vs instance, constants |
| 14 | `topic14_encapsulation_and_access_modifiers` | `BankAccount` → `EncapsulationDemo` | Private state behind validating methods; the 4 access levels |
| 15 | `topic15_inheritance_and_polymorphism` | `Animal` → `Dog` / `Cat` → `PolymorphismDemo` | `extends`, `super`, `@Override`, dynamic dispatch, `instanceof` patterns, casting |
| 16 | `topic16_equals_and_hashcode` | `EqualsAndHashCode` | Why a HashSet sees duplicates until both methods are overridden |
| 17 | `topic17_enums` | `EnumsDemo` | Enum constants, `values`/`valueOf`, enums with fields and methods, exhaustive switch |
| 18 | `topic18_generics` | `GenericsDemo` | Generic classes and methods, `<T extends Comparable<T>>`, `? extends` wildcards |
| 19 | `topic19_collections_framework` | `SetsDemo` → `QueuesAndDeques` → `MapsDemo` | Choosing a Set/Map, FIFO vs LIFO, PriorityQueue, `merge()` counting, Iterator removal |
| 20 | `topic20_comparable_and_comparator` | `SortingObjects` | Natural order vs extra orders, `comparing`/`reversed`/`thenComparing` |
| 21 | `topic21_custom_exceptions` | `InsufficientBalanceException` → `InvalidAmountException` → `Wallet` → `CustomExceptionDemo` | Your own checked and unchecked exceptions, `finally` |
| 22 | `topic22_multithreading` | `ThreadBasics` → `RaceConditionDemo` → `ExecutorServiceDemo` | `start`/`join`, race conditions, `synchronized`, `AtomicInteger`, thread pools, `Future` |
| 23 | `topic23_file_io` | `FileIODemo` | `Path` + `Files`: write, append, read all, read line by line |
| 24 | `topic24_modern_java` | `ModernJavaFeatures` | `var`, records, switch expressions, sealed types, pattern matching, text blocks |

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

**10. Data types and operators**
- [ ] Widening (int → long) is automatic. Narrowing (double → int) needs a cast and cuts off the decimals, it doesn't round.
- [ ] `7 / 2` is `3`: int divided by int stays an int. `%` gives the remainder.
- [ ] Compare wrappers with `equals()`. `Integer` values outside -128..127 are different objects, so `==` is false.
- [ ] `&&` and `||` skip the right side when the answer is already known. `name != null && name.length() > 3` is safe.

**11. Strings**
- [ ] Strings are immutable: `s.toUpperCase()` does nothing unless you assign the result.
- [ ] Always compare text with `equals()`. Write `"yes".equals(input)` so a null `input` is safe.
- [ ] Build text in a loop with `StringBuilder`, not `+`.

**12. Arrays**
- [ ] The size is fixed. Indexes run from 0 to `length - 1`. `length` is a field, so it has no `()`.
- [ ] `int[] b = a;` copies the reference, not the values. Use `Arrays.copyOf` for a real copy.
- [ ] Print with `Arrays.toString` (or `deepToString` for 2D). Compare with `Arrays.equals`.

**13. Methods and static**
- [ ] Java is always pass-by-value. For an object, the value copied is the reference, so the method can change the object but can't make the caller's variable point somewhere else.
- [ ] Overloading = same method name with different parameters.
- [ ] A `static` field has one copy per class. A static method has no `this`.

**14. Encapsulation**
- [ ] Keep fields private. Change them only through methods that enforce the rules. Leave out the setter when a field should be read-only.
- [ ] Access levels, from narrowest to widest: `private` < (default, package) < `protected` < `public`.

**15. Inheritance and polymorphism**
- [ ] `super(...)` must be the first line of a subclass constructor.
- [ ] Overriding is decided at runtime (by the object). Overloading is decided at compile time (by the parameters).
- [ ] `if (animal instanceof Dog dog)` checks the type and casts in one step. A wrong cast throws `ClassCastException`.

**16. equals and hashCode**
- [ ] If `a.equals(b)`, then `a.hashCode() == b.hashCode()` must also be true. Override both, using the same fields.

**17. Enums**
- [ ] Use enums instead of magic strings. Compare them with `==`. A switch over an enum can cover every constant.

**18. Generics**
- [ ] Generics catch type errors at compile time. Avoid raw types like `List` without `<...>`.
- [ ] `<T extends X>` means "T must be an X". `List<? extends Number>` accepts a list of any Number subtype.

**19. Collections framework**
- [ ] Hash* has no order, LinkedHash* keeps insertion order, Tree* keeps things sorted.
- [ ] For a stack or a queue, use `ArrayDeque`. `PriorityQueue` always gives you the smallest element first.
- [ ] `map.merge(key, 1, Integer::sum)` is the one-line way to count.

**20. Comparable and Comparator**
- [ ] Comparable is the one natural order, written inside the class. A Comparator is any other order, written outside it.
- [ ] Compare numbers with `Integer.compare(a, b)`, not `a - b`, which can overflow.

**21. Custom exceptions**
- [ ] Extend `Exception` for a checked exception, or `RuntimeException` for an unchecked one. Pass a message to `super(...)` and store any useful data in fields.
- [ ] `finally` always runs, whether or not an exception happened.

**22. Multithreading**
- [ ] `start()` runs the code on a new thread. Calling `run()` just runs it on the current thread.
- [ ] `count++` is not atomic. Use `synchronized` or `AtomicInteger`.
- [ ] Prefer an `ExecutorService` over creating threads by hand, and always shut it down.

**23. File I/O**
- [ ] For small files, `Files.readAllLines` and `Files.writeString` are enough. For big files, read line by line with `Files.newBufferedReader`.
- [ ] Open readers and writers in try-with-resources so they always get closed.

**24. Modern Java**
- [ ] A `record` is an immutable data class, with the constructor, getters, `equals`, `hashCode` and `toString` generated for you.
- [ ] Switch expressions return a value and don't fall through. With a sealed type, the compiler checks that every case is covered.
- [ ] `var` still has a fixed type; the compiler works out what it is.

## Status
✅ Working. All 24 topics compile, and every `main` runs. `MyHashMap`, `PhoneNumberCleaner`, `TwoSum`, `EqualsAndHashCode` and `RaceConditionDemo` check themselves with asserts when run with `-ea`.
