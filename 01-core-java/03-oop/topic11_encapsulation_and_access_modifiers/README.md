# Topic 11 · Encapsulation and access modifiers

**Difficulty:** Intermediate · **Needs:** [10 Exception basics](../../02-objects-and-classes/topic10_exception_basics/) · **Next:** [12 Inheritance and polymorphism](../topic12_inheritance_and_polymorphism/)

## Why it matters
If any code can write `account.balance = -500`, the rule "a balance is never negative" has to be re-checked everywhere, and one forgotten check is a bug. Encapsulation puts the rule in **one** place: the fields are private, and the only way to change them is through methods that enforce the rule. It's the first of the four OOP pillars, and it's why an object can be trusted.

## What you'll learn
- Private fields behind validating methods
- Read-only fields (a getter with no setter)
- The four access levels: `private`, package (no keyword), `protected`, `public`

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic11_encapsulation_and_access_modifiers.EncapsulationDemo
```
Read `BankAccount` first, then the demo.

## Key concepts
- **Private state:** make fields `private`. Change them only through methods that check the rules (`deposit` rejects negative amounts).
- **Validate first:** check *before* you change the field, so a rejected call leaves the object as it was.
- **Read-only values:** no setter, and preferably a `final` field.
- **Access levels**, narrowest to widest:

  | Level | Who can see it |
  |---|---|
  | `private` | the class itself |
  | package (no keyword) | the same package |
  | `protected` | the package, plus subclasses anywhere |
  | `public` | everyone |

## Exercises
`Exercises.java` (run `java -cp out topic11_encapsulation_and_access_modifiers.Exercises`):
1. A `Temperature` with getters for Celsius and Fahrenheit
2. `warmBy(degrees)`
3. Rejecting anything below absolute zero, both in the constructor and in `warmBy`

## Common mistakes
- Adding a public setter for every field, which undoes the encapsulation.
- Checking the rule in the constructor but not in the other methods that change the field.
- Changing the field first and validating afterwards.
- Returning a mutable internal list directly: see [17 Records and immutability](../topic17_records_and_immutability/).

## Related topics
- [12 Inheritance](../topic12_inheritance_and_polymorphism/): `protected` for subclasses
- [17 Records and immutability](../topic17_records_and_immutability/): objects that can't change at all

## Revision checklist
- [ ] I can rank the access levels from narrowest to widest.
- [ ] I can explain why the balance check belongs inside `BankAccount`, not in the code that calls it.
- [ ] I validate before changing a field.
