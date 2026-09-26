package topic16_equals_and_hashcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Topic    : equals() and hashCode()
 * Key idea : by default equals() means "same object". Override it to mean "same data",
 *            and ALWAYS override hashCode() with it - HashSet/HashMap use hashCode()
 *            to find the bucket first, then equals() inside the bucket.
 * Rule     : a.equals(b) == true  =>  a.hashCode() == b.hashCode()
 * Run      : java -ea -cp out topic16_equals_and_hashcode.EqualsAndHashCode
 * Try this : delete the hashCode() method in GoodPoint and watch the set size change.
 */
public class EqualsAndHashCode {

    // no equals/hashCode: two points with the same x, y are "different"
    static class BadPoint {
        final int x;
        final int y;

        BadPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class GoodPoint {
        private final int x;
        private final int y;

        GoodPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;                                  // same object
            }
            if (!(other instanceof GoodPoint point)) {
                return false;                                 // null or another type
            }
            return x == point.x && y == point.y;              // compare the fields that matter
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);                        // same fields as equals()
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        BadPoint b1 = new BadPoint(1, 2);
        BadPoint b2 = new BadPoint(1, 2);
        Set<BadPoint> badSet = new HashSet<>();
        badSet.add(b1);
        badSet.add(b2);
        System.out.println("BadPoint  equals: " + b1.equals(b2) + ", set size: " + badSet.size());

        GoodPoint g1 = new GoodPoint(1, 2);
        GoodPoint g2 = new GoodPoint(1, 2);
        Set<GoodPoint> goodSet = new HashSet<>();
        goodSet.add(g1);
        goodSet.add(g2);
        System.out.println("GoodPoint equals: " + g1.equals(g2) + ",  set size: " + goodSet.size()
                + ", contains (1, 2)? " + goodSet.contains(new GoodPoint(1, 2)));

        assert !b1.equals(b2) && badSet.size() == 2;
        assert g1.equals(g2) && g1.hashCode() == g2.hashCode();
        assert goodSet.size() == 1;
        assert !g1.equals(null) && !g1.equals("(1, 2)");
    }
}
