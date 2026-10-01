package topic01_first_program.solutions;

// Answers for topic01_first_program/Exercises.java
public class ExercisesSolution {

    static String nameCard(String name, String language) {
        return "Hi, I'm " + name + " and I'm learning " + language + "!";   // + joins text pieces together
    }

    static int letterCount(String text) {
        return text.length();               // length() counts every character, spaces also
    }

    public static void main(String[] args) {
        check(nameCard("Asha", "Java").equals("Hi, I'm Asha and I'm learning Java!"), "exercise 1");
        check(nameCard("Ravi", "Spring").equals("Hi, I'm Ravi and I'm learning Spring!"), "exercise 1");
        check(letterCount("Hello") == 5, "exercise 2");
        check(letterCount("") == 0, "exercise 2");
        System.out.println("All exercises pass");

        // Answers for the README exercise "fix the broken programs":
        //   1. System.out.println("Hi")      -> the ; at the end is missing, add it
        //   2. system.out.println("Hi");     -> write System with a capital S. Java is case-sensitive
        //   3. public class Hello in a file named Greeting.java -> rename the file to Hello.java
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
