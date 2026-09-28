# Topic 12 · Inheritance and polymorphism

**Difficulty:** Intermediate · **Needs:** [11 Encapsulation](../topic11_encapsulation_and_access_modifiers/) · **Next:** [13 Abstract classes](../topic13_abstract_classes/)

## Why it matters
Dogs, cats and cows are all animals. Circles and rectangles are all shapes. Inheritance lets you write the shared part once. Polymorphism is the bigger idea: code written for `Animal` works for every kind of animal, including ones added later, without a single `if (it's a dog)`. That's how frameworks call *your* code.

## What you'll learn
- `extends`, `super(...)` and `@Override`
- Dynamic dispatch: the **object's** type decides which method runs
- The variable's type vs the object's type, casting, `instanceof` with a pattern variable, `ClassCastException`
- `Object` as the root of every class

## Run it
From `03-oop` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic12_inheritance_and_polymorphism.PolymorphismDemo
```
Read `Animal`, then `Dog` and `Cat`, then the demo.

## Key concepts
- **IS-A:** a subclass IS-A superclass. It inherits fields and methods, and can override methods and add new ones.
- **`super`:** `super(name)` must be the first line of a subclass constructor. `super.describe()` calls the parent's version.
- **Overriding vs overloading:**
  - **overriding** (same signature in a subclass) is chosen at **runtime**, by the object;
  - **overloading** (same name, different parameters) is chosen at **compile time**.
- **Variable type vs object type:** in `Animal pet = new Dog(...)`, the variable type (`Animal`) decides what you may *call*, and the object type (`Dog`) decides *which code* runs.
- **Checking the type:** `if (pet instanceof Dog dog)` checks and casts in one step. A wrong cast throws `ClassCastException`.

## Exercises
`Exercises.java` (run `java -cp out topic12_inheritance_and_polymorphism.Exercises`):
1. `Rectangle` and `Circle` extending `Shape` and overriding `area()`
2. See why `describe()` works for both without being rewritten
3. `totalArea` for any mix of shapes, with no `instanceof`

## Common mistakes
- Confusing overloading with overriding. `@Override` makes the compiler check that you really override.
- Casting down without checking, which gives a `ClassCastException`.
- Deep inheritance trees for code reuse alone: often an interface or a field is simpler.
- Forgetting that `private` members aren't visible in the subclass. Use `protected` or a getter.

## Related topics
- [13 Abstract classes](../topic13_abstract_classes/): a parent that must be extended
- [14 Interfaces](../topic14_interfaces_and_dependency_injection/): polymorphism without inheritance
- [47 Modern Java](../../08-advanced-java/topic47_modern_java/): sealed classes and pattern-matching switch

## Revision checklist
- [ ] I can say whether the variable's type or the object's type decides which method runs.
- [ ] I can explain overriding vs overloading.
- [ ] I know why `totalArea` needs no `instanceof`.
