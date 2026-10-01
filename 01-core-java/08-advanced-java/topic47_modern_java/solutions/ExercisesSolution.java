package topic47_modern_java.solutions;

// Answers for topic47_modern_java/Exercises.java
public class ExercisesSolution {

    sealed interface Shape permits Circle, Square, Triangle { }

    record Circle(double radius) implements Shape { }

    record Square(double side) implements Shape { }

    record Triangle(double base, double height) implements Shape { }

    static double area(Shape shape) {
        return switch (shape) {                          // covers every case: add a 4th shape and this stops compiling
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Square s -> s.side() * s.side();
            case Triangle t -> t.base() * t.height() / 2;
        };
    }

    static String describe(Object value) {
        return switch (value) {
            case null -> "nothing";                      // since Java 21, a switch can handle null directly
            case Integer i when i > 0 -> "positive number";   // a guard ("when"): the more specific case must come first
            case Integer i -> "number";
            case String s -> "text of length " + s.length();
            default -> "something else";
        };
    }

    static String json(String name, int age) {
        // %s = put text here, %d = put a whole number here.
        // The spaces to the left of the closing """ are removed from every line automatically
        return """
                {
                  "name": "%s",
                  "age": %d
                }
                """.formatted(name, age);
    }

    public static void main(String[] args) {
        check(area(new Square(2)) == 4 && area(new Triangle(4, 3)) == 6, "exercise 1");
        check(Math.abs(area(new Circle(1)) - Math.PI) < 1e-9, "exercise 1 circle");

        check(describe(5).equals("positive number") && describe(-5).equals("number"), "exercise 2 numbers");
        check(describe("hey").equals("text of length 3") && describe(null).equals("nothing"), "exercise 2 text / null");
        check(describe(2.5).equals("something else"), "exercise 2 other");

        check(json("Asha", 30).equals("{\n  \"name\": \"Asha\",\n  \"age\": 30\n}\n"), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
