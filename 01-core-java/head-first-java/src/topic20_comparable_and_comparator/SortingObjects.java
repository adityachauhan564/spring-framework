package topic20_comparable_and_comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Topic    : Sorting your own objects
 * Key idea : Comparable = the ONE natural order, written inside the class (compareTo).
 *            Comparator = any number of extra orders, written outside the class.
 *            compareTo/compare return negative (a first), 0 (equal), positive (b first).
 * Run      : java -cp out topic20_comparable_and_comparator.SortingObjects
 * Try this : sort by name length, then alphabetically for equal lengths.
 */
public class SortingObjects {

    static class Student implements Comparable<Student> {
        private final String name;
        private final int marks;

        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        String getName() {
            return name;
        }

        int getMarks() {
            return marks;
        }

        @Override
        public int compareTo(Student other) {
            return this.name.compareTo(other.name);            // natural order: by name
        }

        @Override
        public String toString() {
            return name + "(" + marks + ")";
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(List.of(
                new Student("Ravi", 82),
                new Student("Asha", 95),
                new Student("Kiran", 82),
                new Student("Meera", 70)));

        // 1. Comparable: natural order
        List<Student> byName = new ArrayList<>(students);
        byName.sort(null);                                      // null = use compareTo
        System.out.println("By name (Comparable):      " + byName);

        // 2. Comparator: an extra order, no change to Student
        List<Student> byMarks = new ArrayList<>(students);
        byMarks.sort(Comparator.comparingInt(Student::getMarks));
        System.out.println("By marks (Comparator):     " + byMarks);

        // 3. reversed + tie-breaker with thenComparing
        List<Student> ranking = new ArrayList<>(students);
        ranking.sort(Comparator.comparingInt(Student::getMarks).reversed()
                .thenComparing(Student::getName));
        System.out.println("Ranking (marks desc, name): " + ranking);

        // Same thing as an old-style anonymous class - longer, same idea
        Comparator<Student> byMarksOldStyle = new Comparator<>() {
            @Override
            public int compare(Student a, Student b) {
                return Integer.compare(a.getMarks(), b.getMarks());   // not a - b: that can overflow
            }
        };
        System.out.println("Lowest marks: " + students.stream().min(byMarksOldStyle).orElseThrow());
    }
}
