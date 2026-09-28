package topic01_first_program.solutions;

// Solutions for topic01_first_program/Exercises.java
public class ExercisesSolution {

    static String nameCard(String name, String language) {
        return "Hi, I'm " + name + " and I'm learning " + language + "!";
    }

    static int letterCount(String text) {
        return text.length();
    }

    public static void main(String[] args) {
        check(nameCard("Asha", "Java").equals("Hi, I'm Asha and I'm learning Java!"), "exercise 1");
        check(nameCard("Ravi", "Spring").equals("Hi, I'm Ravi and I'm learning Spring!"), "exercise 1");
        check(letterCount("Hello") == 5, "exercise 2");
        check(letterCount("") == 0, "exercise 2");
        System.out.println("All exercises pass");

        // README exercise "fix the broken programs":
        //   1. System.out.println("Hi")      -> add the missing ;
        //   2. system.out.println("Hi");     -> System (capital S): Java is case-sensitive
        //   3. public class Hello in a file named Greeting.java -> rename the file to Hello.java
    }

    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}
