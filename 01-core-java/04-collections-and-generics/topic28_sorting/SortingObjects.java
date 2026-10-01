package topic28_sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Topic    : Sorting your own objects
 * Key idea : Java can sort numbers and text by itself. For your own class, you must tell it HOW.
 *            - Comparable = the ONE natural order, written INSIDE the class (the compareTo method).
 *                           Like a class register, always sorted by roll number.
 *            - Comparator = any number of extra orders, written OUTSIDE the class.
 *                           Like sorting the same students by marks for the result, or by height for the photo.
 *            compareTo / compare return:  negative = a comes first,  0 = equal,  positive = b comes first
 * Run      : java -cp out topic28_sorting.SortingObjects
 * Try this : Sort by name length, and alphabetically when two lengths are equal.
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
            return this.name.compareTo(other.name);            // the natural order: alphabetically by name
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

        // 1. Comparable: the natural order
        List<Student> byName = new ArrayList<>(students);
        byName.sort(null);                                      // null = "use the class's own compareTo"
        System.out.println("By name (Comparable):      " + byName);

        // 2. Comparator: a different order, and the Student class doesn't change at all
        List<Student> byMarks = new ArrayList<>(students);
        byMarks.sort(Comparator.comparingInt(Student::getMarks));
        System.out.println("By marks (Comparator):     " + byMarks);

        // 3. like a merit list: highest marks first (reversed), and if marks are equal, by name (thenComparing)
        List<Student> ranking = new ArrayList<>(students);
        ranking.sort(Comparator.comparingInt(Student::getMarks).reversed()
                .thenComparing(Student::getName));
        System.out.println("Ranking (marks desc, name): " + ranking);

        // The same Comparator written the old way, as an anonymous class - longer, same idea
        Comparator<Student> byMarksOldStyle = new Comparator<>() {
            @Override
            public int compare(Student a, Student b) {
                return Integer.compare(a.getMarks(), b.getMarks());   // don't write a - b: with huge numbers it can overflow
            }
        };
        System.out.println("Lowest marks: " + Collections.min(students, byMarksOldStyle));
    }
}
