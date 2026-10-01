package topic17_records_and_immutability;

/*
 * Topic    : Records (Java 16+)
 * Key idea : A record is a short way to write a class that only carries DATA.
 *            From this one line:  record Point(int x, int y)
 *            Java writes all of this for you:
 *              - private final fields x and y
 *              - a constructor
 *              - getters x() and y()   (note: x(), not getX())
 *              - equals(), hashCode() and toString() using all the fields
 *            Saves you 30-40 lines of boring code.
 * Run      : java -cp out topic17_records_and_immutability.RecordsDemo
 * Try this : Add a field to Point and see that equals and toString include it automatically.
 */
public class RecordsDemo {

    record Point(int x, int y) {

        // compact constructor: for checking the values. No need to repeat the parameters
        // or write this.x = x - Java does the assigning after this block
        Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("coordinates must be >= 0, got " + x + ", " + y);
            }
        }

        // records can have methods. A record can't change, so "moving" it gives back a NEW record
        Point moveBy(int dx, int dy) {
            return new Point(x + dx, y + dy);
        }

        double distanceToOrigin() {
            return Math.sqrt(x * x + y * y);
        }
    }

    public static void main(String[] args) {
        Point p = new Point(3, 4);
        Point same = new Point(3, 4);

        System.out.println("toString:  " + p);                          // prints Point[x=3, y=4]
        System.out.println("accessors: x=" + p.x() + ", y=" + p.y());   // x(), not getX()
        System.out.println("equals:    " + p.equals(same) + ", hashCode equal: " + (p.hashCode() == same.hashCode()));
        System.out.println("distance:  " + p.distanceToOrigin());

        Point moved = p.moveBy(1, 1);
        System.out.println("moveBy:    " + moved + " - and p is still " + p);

        try {
            new Point(-1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected:  " + e.getMessage());
        }
        // p.x = 10;   // compile error: record fields are final, they can't be changed
    }
}
