package topic12_inheritance_and_polymorphism;

/*
 * Run      : java -cp out topic12_inheritance_and_polymorphism.PolymorphismDemo
 * Key idea : Polymorphism = "many forms". One variable type (Animal) can hold many object types (Dog, Cat).
 *            Like the word "driver": it can be a bus driver, auto driver or truck driver.
 *            You just say "drive!", and each one drives in their own way.
 *            - Overriding  = SAME method name and parameters in the child class.
 *                            Java picks the version while the program RUNS.
 *            - Overloading = same name, DIFFERENT parameters.
 *                            Java picks the version while COMPILING.
 * Try this : Add a Cow class - the loop below will work without any change.
 */
public class PolymorphismDemo {

    public static void main(String[] args) {
        Animal[] animals = {new Dog("Bruno"), new Cat("Kitty"), new Animal("Generic")};

        for (Animal animal : animals) {
            System.out.println(animal.describe());          // each object runs its OWN version of describe()
        }

        // The variable's type decides which methods you are allowed to CALL
        Animal pet = new Dog("Rex");
        // pet.fetch();   // compile error: the variable is an Animal, and Animal has no fetch()

        // instanceof with pattern matching (Java 16+): "if pet is a Dog, call it dog" - check and cast in one step
        if (pet instanceof Dog dog) {
            System.out.println(dog.fetch());
        }

        // a wrong cast compiles fine, but crashes while running
        Animal cat = new Cat("Tom");
        try {
            Dog notADog = (Dog) cat;                        // you can't turn a Cat into a Dog
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: a Cat is not a Dog");
        }

        // every class in Java secretly extends Object, so toString/equals/hashCode come from there
        System.out.println("Dog's superclass: " + Dog.class.getSuperclass().getSimpleName()
                + ", Animal's superclass: " + Animal.class.getSuperclass().getSimpleName());
    }
}
