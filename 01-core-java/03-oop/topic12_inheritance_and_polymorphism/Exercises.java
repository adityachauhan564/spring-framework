package topic12_inheritance_and_polymorphism;

/*
 * Exercises for topic 12. Complete the classes below this one, then run:
 *   java -cp out topic12_inheritance_and_polymorphism.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 3. The total area of any shapes, WITHOUT checking which kind each one is (no instanceof).
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// Shape is complete: a name, a default area, and a describe() that uses whatever area() the object has.
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

// 1. Rectangle and Circle extend Shape: pass the name up with super(...), and override area().
// 2. describe() then works without being rewritten. Why?
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
