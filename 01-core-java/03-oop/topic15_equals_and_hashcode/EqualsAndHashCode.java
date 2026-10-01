package topic15_equals_and_hashcode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Topic    : equals() and hashCode()
 * Key idea : - By default, equals() only means "is it the SAME object in memory?".
 *              Two different objects with the same data are still "not equal".
 *            - Override equals() so it means "is the DATA the same?".
 *            - Whenever you override equals(), ALWAYS override hashCode() too.
 *              HashSet and HashMap first use hashCode() to pick a "bucket" (a shelf),
 *              then use equals() to look inside that bucket.
 *              Like a post office: the PIN code (hashCode) takes the letter to the right area,
 *              then the house address (equals) finds the exact house.
 *              Wrong PIN code = the letter goes to the wrong area and is never found.
 * Rule     : if a.equals(b) is true, then a.hashCode() MUST be equal to b.hashCode()
 * Uses     : HashSet - a collection that never keeps duplicates (topic 25 covers it fully)
 * Run      : java -ea -cp out topic15_equals_and_hashcode.EqualsAndHashCode
 *            (-ea switches on the "assert" checks at the end of main)
 * Try this : Delete the hashCode() method in GoodPoint and watch the set size change.
 */
public class EqualsAndHashCode {

    // no equals/hashCode written: two points with the same x and y are treated as "different"
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
                return true;                                  // it is literally the same object
            }
            if (!(other instanceof GoodPoint point)) {
                return false;                                 // null, or not a GoodPoint at all
            }
            return x == point.x && y == point.y;              // compare the fields that matter
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);                        // use the SAME fields that equals() uses
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
        badSet.add(b2);                                       // added again - the set can't tell it's a duplicate
        System.out.println("BadPoint  equals: " + b1.equals(b2) + ", set size: " + badSet.size());

        GoodPoint g1 = new GoodPoint(1, 2);
        GoodPoint g2 = new GoodPoint(1, 2);
        Set<GoodPoint> goodSet = new HashSet<>();
        goodSet.add(g1);
        goodSet.add(g2);                                      // same data, so the set keeps only one
        System.out.println("GoodPoint equals: " + g1.equals(g2) + ",  set size: " + goodSet.size()
                + ", contains (1, 2)? " + goodSet.contains(new GoodPoint(1, 2)));

        // assert = "this must be true". With -ea, the program stops here if any of these is false
        assert !b1.equals(b2) && badSet.size() == 2;
        assert g1.equals(g2) && g1.hashCode() == g2.hashCode();
        assert goodSet.size() == 1;
        assert !g1.equals(null) && !g1.equals("(1, 2)");
    }
}
