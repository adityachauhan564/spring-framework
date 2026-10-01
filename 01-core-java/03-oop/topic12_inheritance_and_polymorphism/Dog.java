package topic12_inheritance_and_polymorphism;

// A Dog IS-A Animal. It gives its own sound, and has one extra skill only dogs have: fetch()
public class Dog extends Animal {

    public Dog(String name) {
        super(name);                     // must be the first line: build the Animal part first, then the Dog part
    }

    @Override                            // asks the compiler to confirm we are really replacing a parent method (catches spelling mistakes)
    public String makeSound() {
        return "Woof";
    }

    public String fetch() {              // only Dogs have this method, Animal does not
        return name + " fetches the ball";
    }
}
