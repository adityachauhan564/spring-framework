package topic47_modern_java;

/*
 * Exercises for topic 47. Replace each "TODO" line with your code, then run:
 *   java -cp out topic47_modern_java.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    sealed interface Shape permits Circle, Square, Triangle { }

    record Circle(double radius) implements Shape { }

    record Square(double side) implements Shape { }

    record Triangle(double base, double height) implements Shape { }

    // 1. The area of any Shape, with a pattern-matching switch and NO default branch.
    //    (Because Shape is sealed, the compiler knows these are all the cases.)
    static double area(Shape shape) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Describe any value with a switch over its TYPE, using guards (when):
    //      a positive Integer  -> "positive number"
    //      any other Integer   -> "number"
    //      a String            -> "text of length N"
    //      null                -> "nothing"
    //      anything else       -> "something else"
    static String describe(Object value) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    // 3. Build this JSON with a TEXT BLOCK and .formatted(name, age) - no \n, no + between lines:
    //    {
    //      "name": "<name>",
    //      "age": <age>
    //    }
    static String json(String name, int age) {
        throw new UnsupportedOperationException("TODO exercise 3");
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

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
