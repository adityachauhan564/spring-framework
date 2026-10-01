package topic12_inheritance_and_polymorphism;

/*
 * Topic    : Inheritance
 * Key idea : A child class (subclass) gets everything from its parent class (superclass) for free -
 *            the fields and the methods. Like a son getting his father's surname and family house.
 *            - "Dog extends Animal" means a Dog IS-A Animal.
 *            - The child can write its own version of a parent method. This is called overriding.
 *            - The child can still call the parent's version using the word super.
 * Read     : Animal -> Dog, Cat -> PolymorphismDemo
 */
public class Animal {

    protected final String name;    // protected: child classes (Dog, Cat) can use it directly

    public Animal(String name) {
        this.name = name;
    }

    // the default sound. Dog and Cat replace this with their own sound
    public String makeSound() {
        return "...";
    }

    public String describe() {
        // makeSound() here runs the CHILD's version if the object is a Dog or Cat - decided while running
        return name + " says " + makeSound();
    }
}
