package topic02_lambdas_and_functional_interfaces;

/*
 * Topic    : Lambdas and functional interfaces
 * Key idea : a functional interface has exactly ONE abstract method. A lambda is a short
 *            way to write that one method: (parameters) -> body.
 * Run      : java -cp out topic02_lambdas_and_functional_interfaces.LambdaSyntax
 * Try this : write a Calculator lambda for "power" using Math.pow.
 */
public class LambdaSyntax {

    @FunctionalInterface                  // compiler error if a second abstract method is added
    interface Calculator {
        int calculate(int a, int b);
    }

    @FunctionalInterface
    interface Greeter {
        String greet(String name);
    }

    public static void main(String[] args) {
        // 1. the old way: an anonymous class
        Calculator addOld = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        // 2. the same thing as a lambda
        Calculator add = (a, b) -> a + b;

        // lambda forms
        Calculator multiply = (int a, int b) -> a * b;          // explicit types (optional)
        Calculator max = (a, b) -> {                            // block body needs 'return'
            if (a > b) {
                return a;
            }
            return b;
        };
        Greeter hello = name -> "Hello, " + name;              // one parameter: no brackets needed

        System.out.println("anonymous class: " + addOld.calculate(2, 3));
        System.out.println("add:      " + add.calculate(2, 3));
        System.out.println("multiply: " + multiply.calculate(2, 3));
        System.out.println("max:      " + max.calculate(2, 3));
        System.out.println(hello.greet("Aditya"));

        // lambdas are values: pass them to a method
        System.out.println("apply(add, 10, 5)      = " + apply(add, 10, 5));
        System.out.println("apply((a, b) -> a - b) = " + apply((a, b) -> a - b, 10, 5));

        // a lambda can use local variables only if they are effectively final (never reassigned)
        int bonus = 100;
        Calculator addWithBonus = (a, b) -> a + b + bonus;
        // bonus = 200;   // compile error: bonus is used in a lambda, so it can't change
        System.out.println("addWithBonus: " + addWithBonus.calculate(1, 2));
    }

    static int apply(Calculator operation, int a, int b) {
        return operation.calculate(a, b);
    }
}
