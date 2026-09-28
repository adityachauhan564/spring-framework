# Topic 08 · Classes and objects

**Difficulty:** Beginner · **Needs:** [07 Methods](../../01-java-basics/topic07_methods/) · **Next:** [09 static and final](../topic09_static_and_final/)

## Why it matters
So far every value has lived in a local variable inside a method. Real programs model *things*: a player, a song, an employee, a bank account. Each has its own data and its own behaviour. A class describes such a thing once, and you create as many objects from it as you need. Everything in the rest of Java, and all of Spring, is built from objects.

## What you'll learn
- A class as a blueprint, and an object as one instance of it
- Fields (instance variables) vs local variables
- Constructors, `this`, getters and `toString`
- `final` fields for data that never changes

## Run it
From `02-objects-and-classes` (compile first: `javac -d out $(find . -name "*.java")`):
```bash
java -cp out topic08_classes_and_objects.GameLauncher   # read GameLauncher -> GuessGame -> Player
java -cp out topic08_classes_and_objects.Song
java -cp out topic08_classes_and_objects.Employee        # the class template to copy
```
The guessing game comes from *Head First Java* chapter 2. Each Player object guesses its own number.

## Key concepts
- **Fields:** each object has its **own copy** of the fields. That's why `song1` and `song2` play different songs through the same `play()` method.
- **Where state lives:** a variable declared inside a method is local, and disappears when the method ends. State that must last belongs in a field.
- **Constructors:** a constructor runs once, at `new`, and sets up the object. `this.title = title` means "field = parameter".
- **A good class template:** `private` fields, a constructor, getters, and `toString()`. Make fields `final` when they never change.

## Exercises
`Exercises.java` (run `java -cp out topic08_classes_and_objects.Exercises`):
1. Complete a `Book` class: constructor, getters, `isLong()`, `toString()`.
2. Complete a `Counter` class, and check that two counters keep separate counts.

## Common mistakes
- Declaring the "fields" inside `main`, so no method can see them.
- `title = title;` in a constructor without `this.`, which assigns the parameter to itself and leaves the field `null`.
- Making every field `public`: see [11 Encapsulation](../../03-oop/topic11_encapsulation_and_access_modifiers/).
- Forgetting `@Override public String toString()`, so printing shows `Book@1b6d3586`.

## Related topics
- [09 static and final](../topic09_static_and_final/): members that belong to the class, not an object
- [11 Encapsulation](../../03-oop/topic11_encapsulation_and_access_modifiers/): protecting an object's rules
- [15 equals and hashCode](../../03-oop/topic15_equals_and_hashcode/): when two objects count as equal

## Revision checklist
- [ ] I can explain how each object keeps its own state.
- [ ] I can write a class with private final fields, a constructor, getters and `toString`.
- [ ] I know what `this.name = name` does and what goes wrong without `this`.
