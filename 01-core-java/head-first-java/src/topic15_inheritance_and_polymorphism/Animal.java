package topic15_inheritance_and_polymorphism;

/*
 * Topic    : Inheritance
 * Key idea : a subclass IS-A superclass. It inherits fields and methods, can
 *            override methods, and calls the parent with super.
 * Read     : Animal -> Dog, Cat -> PolymorphismDemo
 */
public class Animal {

    protected final String name;    // protected: visible to subclasses

    public Animal(String name) {
        this.name = name;
    }

    public String makeSound() {
        return "...";
    }

    public String describe() {
        return name + " says " + makeSound();   // calls the OVERRIDDEN version at runtime
    }
}
