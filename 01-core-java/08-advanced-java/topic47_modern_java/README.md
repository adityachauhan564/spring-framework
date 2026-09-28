# Topic 47 · Modern Java features

**Difficulty:** Advanced · **Needs:** [17 Records](../../03-oop/topic17_records_and_immutability/), [12 Inheritance](../../03-oop/topic12_inheritance_and_polymorphism/) · **Next:** [48 JVM memory](../topic48_jvm_memory/)

## Why it matters
Java has changed a lot since version 8, and current codebases, Spring Boot 3 and 4 included, use the newer features everywhere. They remove boilerplate (`var`, text blocks), and more importantly they let the **compiler** check more:
- a sealed hierarchy tells it every possible subtype;
- a pattern-matching switch over it must handle each one.

This topic gathers the features you'll read most in modern code, and that you've partly used already.

## What you'll learn
- `var`: local type inference
- Switch expressions, and pattern-matching `switch` with guards (`when`) and `case null`
- Sealed interfaces and classes (`sealed ... permits`)
- `instanceof` patterns
- Text blocks and `.formatted(...)`
- `List.of` / `Map.of`, and handy String methods (`repeat`, `strip`, `isBlank`)

## Run it
From `08-advanced-java` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic47_modern_java.ModernJavaFeatures
```

| Feature | Since Java |
|---|---|
| `List.of` / `Map.of` | 9 |
| `var` | 10 |
| String `repeat`, `strip`, `isBlank` | 11 |
| switch expressions | 14 |
| text blocks | 15 |
| records, `instanceof` patterns | 16 |
| sealed types | 17 |
| pattern matching for `switch` | 21 |

## Key concepts
- **`var`:** `var names = List.of(...)` is still **statically typed**; the compiler works out `List<String>`. Use it when the type is obvious from the right-hand side.
- **Sealed types:** `sealed interface Shape permits Circle, Square` lists the only allowed subtypes, so a switch over `Shape` can be exhaustive with no `default`. Add a subtype, and the compiler shows every switch to update.
- **Pattern switches:** `case Integer i when i > 0 ->` checks the type, binds a variable and tests a condition. Order matters: put the specific cases first.
- **Text blocks:** `"""` strings keep their line breaks. The indentation up to the closing `"""` is removed.

## Exercises
`Exercises.java` (run `java -cp out topic47_modern_java.Exercises`):
1. The area of a sealed shape, with no `default`
2. A type switch with guards and `case null`
3. JSON from a text block

## Common mistakes
- `var` where the type isn't obvious (`var x = service.process();`).
- A `default` branch on a sealed switch: it hides the compile error you want when a new subtype arrives.
- A general case (`case Integer i`) before a guarded one, which is a compile error: that case can never match.

## Related topics
- [16 Enums](../../03-oop/topic16_enums/): exhaustive switches over enums
- [17 Records](../../03-oop/topic17_records_and_immutability/): the data carriers inside sealed hierarchies

## Revision checklist
- [ ] I can explain what `sealed` guarantees.
- [ ] I can write a pattern-matching switch with a guard.
- [ ] I know `var` is still statically typed.
