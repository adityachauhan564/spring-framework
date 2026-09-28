package topic14_interfaces_and_dependency_injection;

/*
 * Topic    : default and static methods in interfaces (Java 8+)
 * Key idea : an interface can ship code: a DEFAULT method is inherited by every implementation
 *            (which may override it), and a STATIC method belongs to the interface itself.
 *            That's how the JDK added forEach, stream() etc. to existing interfaces
 *            without breaking every class that already implemented them.
 * Run      : java -cp out topic14_interfaces_and_dependency_injection.DefaultMethods
 * Try this : override describe() in PercentOff and print again.
 * Note     : the classes are nested inside DefaultMethods to keep one file; topic 19 explains that.
 */
public class DefaultMethods {

    interface Discount {
        double apply(double price);                   // abstract: each discount decides

        default String describe() {                   // default: shared behaviour, can be overridden
            return "saves " + (100 - apply(100)) + " on 100";
        }

        static Discount none() {                      // static: a factory on the interface itself
            return new NoDiscount();
        }
    }

    static class NoDiscount implements Discount {
        public double apply(double price) {
            return price;
        }
    }

    static class PercentOff implements Discount {
        private final double percent;

        PercentOff(double percent) {
            this.percent = percent;
        }

        public double apply(double price) {
            return price * (100 - percent) / 100;
        }
    }

    // A class can implement several interfaces - it can extend only one class
    interface Named {
        String name();
    }

    static class FestivalSale implements Discount, Named {
        public double apply(double price) {
            return price - 25;
        }

        public String name() {
            return "Festival sale";
        }
    }

    public static void main(String[] args) {
        Discount[] discounts = {Discount.none(), new PercentOff(10), new FestivalSale()};
        for (Discount discount : discounts) {
            System.out.println(discount.getClass().getSimpleName() + ": 200 -> " + discount.apply(200)
                    + " (" + discount.describe() + ")");
        }
        Named named = new FestivalSale();
        System.out.println("The same object seen as Named: " + named.name());
    }
}
