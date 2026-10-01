package topic28_sorting.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Answers for topic28_sorting/Exercises.java
public class ExercisesSolution {

    record Employee(String name, String department, int salary) { }

    // first by length; if the lengths are equal, then alphabetically
    static final Comparator<String> BY_LENGTH_THEN_ALPHABET =
            Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder());

    static final Comparator<Employee> BY_DEPARTMENT_THEN_SALARY_DESC =
            Comparator.comparing(Employee::department)
                    .thenComparing(Comparator.comparingInt(Employee::salary).reversed());   // reverse ONLY the salary part, not the department

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

        List<Version> versions = new ArrayList<>(List.of(new Version(2, 0), new Version(1, 10), new Version(1, 2)));
        versions.sort(null);
        check(versions.toString().equals("[1.2, 1.10, 2.0]"), "exercise 3");
        System.out.println("All exercises pass");
    }

    // stops the program with a clear message when an answer is wrong
    private static void check(boolean ok, String exercise) {
        if (!ok) throw new AssertionError(exercise + " gives the wrong answer");
    }
}

record Version(int major, int minor) implements Comparable<Version> {
    @Override
    public int compareTo(Version other) {
        int byMajor = Integer.compare(major, other.major);
        return byMajor != 0 ? byMajor : Integer.compare(minor, other.minor);   // same major? then let minor decide
    }

    @Override
    public String toString() {
        return major + "." + minor;
    }
}
