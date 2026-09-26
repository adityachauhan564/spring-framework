package topic15_inheritance_and_polymorphism;

public class Cat extends Animal {

    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return "Meow";
    }

    @Override
    public String describe() {
        return super.describe() + " (and ignores you)";   // extend the parent's version
    }
}
