package topic12_inheritance_and_polymorphism;

// A Cat IS-A Animal. It gives its own sound, and adds a little extra to describe()
public class Cat extends Animal {

    public Cat(String name) {
        super(name);                     // pass the name up to the Animal constructor
    }

    @Override
    public String makeSound() {
        return "Meow";
    }

    @Override
    public String describe() {
        return super.describe() + " (and ignores you)";   // reuse the parent's text, then add our own bit at the end
    }
}
