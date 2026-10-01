package topic12_inheritance_and_polymorphism.solutions;

// Answers for topic12_inheritance_and_polymorphism/Exercises.java
public class ExercisesSolution {

    static double totalArea(Shape[] shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.area();          // each object runs its own area() - Java decides while running
        }
        return total;
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

class Shape {
    private final String name;

    Shape(String name) {
        this.name = name;
    }

    double area() {
        return 0;
    }

    String describe() {
        return String.format("%s with area %.2f", name, area());   // calls area() of the REAL object (Rectangle or Circle)
    }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        super("Rectangle");                 // first line: build the Shape part
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}
