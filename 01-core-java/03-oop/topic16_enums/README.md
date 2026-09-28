# Topic 16 · Enums

**Difficulty:** Intermediate · **Needs:** [12 Inheritance and polymorphism](../topic12_inheritance_and_polymorphism/) · **Next:** [17 Records and immutability](../topic17_records_and_immutability/)

## Why it matters
An order status stored as the string `"SHIPED"` (with a typo) compiles fine and breaks at runtime. An enum is a fixed, named set of values: `OrderStatus.SHIPPED`. The compiler rejects typos, a switch can be checked for completeness, and each constant can carry its own data and behaviour. Whenever you're tempted to write "magic strings" or integer codes, reach for an enum.

## What you'll learn
- Declaring an enum, and `values()`, `valueOf()`, `name()`, `ordinal()`
- Enums with fields, a constructor and methods
- An exhaustive switch expression over an enum

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic16_enums.EnumsDemo
```

## Key concepts
- **Constants:** each constant is a single object created once, so comparing with `==` is safe and correct.
- **Data and behaviour:** an enum can have a `private final` field, a constructor (always private) and methods, as in `Planet.weightOf`.
- **Exhaustive switch:** a switch **expression** over an enum must cover every constant. Add one, and the compiler shows every switch to update.
- **Don't rely on position:** `ordinal()` is the position in the declaration. Reordering the constants changes it, so don't store it or build logic on it.

## Exercises
`Exercises.java` (run `java -cp out topic16_enums.Exercises`):
1. `TrafficLight.next()` with a switch expression
2. A `Coin` enum where each constant carries its value
3. The total of any number of coins

## Common mistakes
- Using strings or ints instead of an enum, then checking them with `equals` everywhere.
- Relying on `ordinal()` for business logic, or saving it to a database.
- `OrderStatus.valueOf("shipped")`: the name is case-sensitive, and it throws `IllegalArgumentException`.

## Related topics
- [04 Control flow](../../01-java-basics/topic04_control_flow/): switch expressions
- [47 Modern Java](../../08-advanced-java/topic47_modern_java/): sealed types, the next step after enums

## Revision checklist
- [ ] I can explain why `==` is safe for enums.
- [ ] I can give an enum a field and a constructor.
- [ ] I know why a switch expression over an enum is safer than a string switch.
