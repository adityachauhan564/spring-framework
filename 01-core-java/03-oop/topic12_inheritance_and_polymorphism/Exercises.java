package topic12_inheritance_and_polymorphism;

/*
 * Exercises for topic 12.
 * How to use:
 *   - Complete the classes written BELOW this one, and the totalArea method. Fill in every "TODO".
 *   - Then run:  java -cp out topic12_inheritance_and_polymorphism.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 3. Add up the area of all the shapes - WITHOUT checking what kind each one is (no instanceof).
    static double totalArea(Shape[] shapes) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    public static void main(String[] args) {
        Shape square = new Rectangle(2, 2);
        Shape circle = new Circle(1);
        check(square.area() == 4, "exercise 1 Rectangle.area");
        check(Math.abs(circle.area() - Math.PI) < 1e-9, "exercise 1 Circle.area");

        check(square.describe().equals("Rectangle with area 4.00"), "exercise 2 describe");
        check(circle.describe().equals("Circle with area 3.14"), "exercise 2 describe");

        check(Math.abs(totalArea(new Shape[] {square, circle, new Rectangle(1, 3)}) - (7 + Math.PI)) < 1e-9,
                "exercise 3 totalArea");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// Shape is already complete. It has a name, a default area of 0,
// and a describe() method that uses whatever area() the actual object has.
class Shape {
    private final String name;

    Shape(String name) {
        this.name = name;
    }

    double area() {
        return 0;
    }

    String describe() {
        return String.format("%s with area %.2f", name, area());
    }
}

// 1. Rectangle and Circle both extend Shape. Send the name up with super(...), and override area().
// 2. After that, describe() works for both without rewriting it. Think about why.
class Rectangle extends Shape {
    Rectangle(double width, double height) {
        super("TODO");
        // TODO: keep width and height
    }

    // TODO: override area()
}

class Circle extends Shape {
    Circle(double radius) {
        super("TODO");
        // TODO: keep the radius
    }

    // TODO: override area()
}
