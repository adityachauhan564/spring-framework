package topic01_first_program;

/*
 * Exercises for topic 01.
 * How to use:
 *   - Each method below has a "TODO" line. Delete that line and write your own code.
 *   - Then run:  java -cp out topic01_first_program.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    // 1. Return a name card. For ("Asha", "Java") it should return:
    //      Hi, I'm Asha and I'm learning Java!
    //    Join the fixed text (in quotes) and the two parameters using +.
    static String nameCard(String name, String language) {
        throw new UnsupportedOperationException("TODO exercise 1");
    }

    // 2. Return how many characters are in the text. Hint: text.length()
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

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
