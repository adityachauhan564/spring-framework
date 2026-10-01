package topic14_interfaces_and_dependency_injection;

/*
 * Topic    : default and static methods in interfaces (Java 8+)
 * Key idea : Since Java 8, an interface can also carry some ready code:
 *            - a DEFAULT method: every class that implements the interface gets it for free.
 *              A class can still write its own version if it wants.
 *            - a STATIC method: belongs to the interface itself. Call it as Discount.none().
 *            This is how Java added forEach(), stream() etc. to old interfaces like List,
 *            without breaking the lakhs of classes that already used them.
 * Run      : java -cp out topic14_interfaces_and_dependency_injection.DefaultMethods
 * Try this : Write your own describe() inside PercentOff and run again.
 * Note     : All the classes are put inside DefaultMethods to keep everything in one file.
 *            Topic 19 explains classes inside classes.
 */
public class DefaultMethods {

    interface Discount {
        double apply(double price);                   // abstract: every discount decides its own rule

        default String describe() {                   // default: ready-made for everyone, can be replaced
            return "saves " + (100 - apply(100)) + " on 100";
        }

        static Discount none() {                      // static: a helper that hands you a "no discount" object
            return new NoDiscount();
        }
    }

    // no discount at all - you pay the full MRP
    static class NoDiscount implements Discount {
        public double apply(double price) {
            return price;
        }
    }

    // "10% off" type of discount
    static class PercentOff implements Discount {
        private final double percent;

        PercentOff(double percent) {
            this.percent = percent;
        }

        public double apply(double price) {
            return price * (100 - percent) / 100;
        }
    }

    // A class can implement MANY interfaces, but can extend only ONE class
    interface Named {
        String name();
    }

    // flat 25 off during Diwali - and it also has a name, so it implements two interfaces
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
        Named named = new FestivalSale();             // the same kind of object, now seen through the Named interface
        System.out.println("The same object seen as Named: " + named.name());
    }
}
