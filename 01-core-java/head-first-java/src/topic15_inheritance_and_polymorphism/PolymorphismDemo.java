package topic15_inheritance_and_polymorphism;

import java.util.List;

/*
 * Run      : java -cp out topic15_inheritance_and_polymorphism.PolymorphismDemo
 * Key idea : one variable type (Animal), many object types (Dog, Cat).
 *            Overriding = runtime choice (same signature, subclass).
 *            Overloading = compile-time choice (same name, different parameters).
 * Try this : add a Cow class - the loop below needs no change.
 */
public class PolymorphismDemo {

    public static void main(String[] args) {
        List<Animal> animals = List.of(new Dog("Bruno"), new Cat("Kitty"), new Animal("Generic"));

        for (Animal animal : animals) {
            System.out.println(animal.describe());          // each object picks its own version
        }

        // The variable type limits what you can CALL
        Animal pet = new Dog("Rex");
        // pet.fetch();   // compile error: Animal has no fetch()

        // instanceof with pattern matching (Java 16+): check and cast in one step
        if (pet instanceof Dog dog) {
            System.out.println(dog.fetch());
        }

        // a wrong cast compiles but fails at runtime
        Animal cat = new Cat("Tom");
        try {
            Dog notADog = (Dog) cat;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: a Cat is not a Dog");
        }

        // every class extends Object, so toString/equals/hashCode are inherited
        System.out.println("Dog's superclass: " + Dog.class.getSuperclass().getSimpleName()
                + ", Animal's superclass: " + Animal.class.getSuperclass().getSimpleName());
    }
}
