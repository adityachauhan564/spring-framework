package topic20_lambdas_and_functional_interfaces;

/*
 * Topic    : Lambdas and functional interfaces
 * Key idea : - A functional interface is an interface with exactly ONE abstract method.
 *            - A lambda is a short way to write that one method:  (parameters) -> body
 *            Instead of writing a full class just to add two numbers, you write: (a, b) -> a + b
 *            Like giving a short instruction to the auto driver - "left from the temple" -
 *            instead of drawing him a full map.
 * Run      : java -cp out topic20_lambdas_and_functional_interfaces.LambdaSyntax
 * Try this : Write a Calculator lambda for "power" using Math.pow.
 */
public class LambdaSyntax {

    @FunctionalInterface                  // the compiler complains if someone adds a second abstract method
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface Greeter {
        String greet(String name);
    }

    public static void main(String[] args) {
        // 1. the old way: an anonymous class - 6 lines just to add two numbers
        Calculator addOld = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        // 2. exactly the same thing, as a lambda - 1 line
        Calculator add = (a, b) -> a + b;

        // different ways to write a lambda
        Calculator multiply = (int a, int b) -> a * b;          // you may write the types, but it's optional
        Calculator max = (a, b) -> {                            // more than one line? use { } and write 'return'
            if (a > b) {
                return a;
            }
            return b;
        };
        Greeter hello = name -> "Hello, " + name;              // only one parameter: brackets not needed

        System.out.println("anonymous class: " + addOld.calculate(2, 3));
        System.out.println("add:      " + add.calculate(2, 3));
        System.out.println("multiply: " + multiply.calculate(2, 3));
        System.out.println("max:      " + max.calculate(2, 3));
        System.out.println(hello.greet("Aditya"));

        // a lambda is a value - you can pass it to a method like any other value
        System.out.println("apply(add, 10, 5)      = " + apply(add, 10, 5));
        System.out.println("apply((a, b) -> a - b) = " + apply((a, b) -> a - b, 10, 5));

        // a lambda can use a local variable only if that variable is never changed ("effectively final")
        int bonus = 100;
        Calculator addWithBonus = (a, b) -> a + b + bonus;
        // bonus = 200;   // compile error: bonus is used inside a lambda, so it can't be changed
        System.out.println("addWithBonus: " + addWithBonus.calculate(1, 2));
    }

    // this method doesn't know WHAT operation it will do - the caller hands it over as a lambda
    static int apply(Calculator operation, int a, int b) {
        return operation.calculate(a, b);
    }
}
