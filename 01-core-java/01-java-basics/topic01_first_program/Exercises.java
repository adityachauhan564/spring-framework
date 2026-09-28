package topic01_first_program;

/*
 * Exercises for topic 01. Replace each "TODO" line with your code, then run:
 *   java -cp out topic01_first_program.Exercises
 * It stops at the first exercise that isn't solved yet. "All exercises pass" means you're done.
 * Stuck? See solutions/ExercisesSolution.java - but try first.
 */
public class Exercises {

    // 1. Return a name card, for example for ("Asha", "Java"):
    //      Hi, I'm Asha and I'm learning Java!
    //    Build it with + between text in quotes and the two parameters.
    static String nameCard(String name, String language) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Return the number of characters in the text (hint: text.length()).
    static int letterCount(String text) {
        throw new UnsupportedOperationException("TODO exercise 2");
    }

    public static void main(String[] args) {
        check(nameCard("Asha", "Java").equals("Hi, I'm Asha and I'm learning Java!"), "exercise 1");
        check(nameCard("Ravi", "Spring").equals("Hi, I'm Ravi and I'm learning Spring!"), "exercise 1");
        check(letterCount("Hello") == 5, "exercise 2");
        check(letterCount("") == 0, "exercise 2");
        System.out.println("All exercises pass");
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
