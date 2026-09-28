package topic17_records_and_immutability;

/*
 * Topic    : Records (Java 16+)
 * Key idea : a record is a class for DATA. From one line - record Point(int x, int y) - Java
 *            generates private final fields, a constructor, accessors x() and y(), and
 *            equals, hashCode and toString based on all the fields.
 * Run      : java -cp out topic17_records_and_immutability.RecordsDemo
 * Try this : add a field to Point and see that equals and toString include it automatically.
 */
public class RecordsDemo {

    record Point(int x, int y) {

        // compact constructor: validate, without repeating the parameter list or the assignments
        Point {
            if (x < 0 || y < 0) {
                throw new IllegalArgumentException("coordinates must be >= 0, got " + x + ", " + y);
            }
        }

        // records can have methods; "changing" one returns a NEW record
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

        System.out.println("toString:  " + p);                          // Point[x=3, y=4]
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
        // p.x = 10;   // compile error: record fields are final
    }
}
