package topic15_inheritance_and_polymorphism;

public class Dog extends Animal {

    public Dog(String name) {
        super(name);                     // must be the first line: build the Animal part first
    }

    @Override                            // compiler checks we really override something
    public String makeSound() {
        return "Woof";
    }

    public String fetch() {              // only Dogs have this
        return name + " fetches the ball";
    }
}
