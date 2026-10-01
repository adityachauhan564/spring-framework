package topic28_sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Exercises for topic 28.
 * How to use:
 *   - Replace each "null" and each TODO with your own code.
 *   - Then run:  java -cp out topic28_sorting.Exercises
 *   - It stops at the first exercise that is not solved yet.
 *   - When you see "All exercises pass", you are done.
 * Stuck? See solutions/ExercisesSolution.java - but please try yourself first.
 */
public class Exercises {

    record Employee(String name, String department, int salary) { }

    // 1. Shorter words first. Words with the same length go alphabetically.
    static final Comparator<String> BY_LENGTH_THEN_ALPHABET = null;          // TODO

    // 2. By department A to Z. Inside the same department, the highest salary first.
    static final Comparator<Employee> BY_DEPARTMENT_THEN_SALARY_DESC = null; // TODO

    public static void main(String[] args) {
        List<String> words = new ArrayList<>(List.of("pear", "fig", "banana", "kiwi", "apple"));
        check(BY_LENGTH_THEN_ALPHABET != null, "exercise 1");
        words.sort(BY_LENGTH_THEN_ALPHABET);
        check(words.equals(List.of("fig", "kiwi", "pear", "apple", "banana")), "exercise 1");

        List<Employee> staff = new ArrayList<>(List.of(
                new Employee("Ravi", "IT", 70), new Employee("Asha", "HR", 60),
                new Employee("Meera", "IT", 90), new Employee("Kiran", "HR", 80)));
        check(BY_DEPARTMENT_THEN_SALARY_DESC != null, "exercise 2");
        staff.sort(BY_DEPARTMENT_THEN_SALARY_DESC);
        check(staff.get(0).name().equals("Kiran") && staff.get(2).name().equals("Meera"), "exercise 2");

        // 3. Versions must sort the natural way: 1.2 < 1.10 < 2.0
        //    (compare them as numbers, not as text - as text, "1.10" would wrongly come before "1.2")
        List<Version> versions = new ArrayList<>(List.of(new Version(2, 0), new Version(1, 10), new Version(1, 2)));
        versions.sort(null);                     // null = use the natural order, i.e. compareTo
        check(versions.toString().equals("[1.2, 1.10, 2.0]"), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

// 3. Give Version a natural order: first by major, then by minor.
//    Use Integer.compare, not subtraction.
record Version(int major, int minor) implements Comparable<Version> {
    @Override
    public int compareTo(Version other) {
        throw new UnsupportedOperationException("TODO exercise 3");
    }

    @Override
    public String toString() {
        return major + "." + minor;
    }
}
