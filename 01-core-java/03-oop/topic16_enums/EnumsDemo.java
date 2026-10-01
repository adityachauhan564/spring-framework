package topic16_enums;

/*
 * Topic    : Enums
 * Key idea : An enum is a fixed list of named values that can never change.
 *            Like the status of your Swiggy order: PLACED, SHIPPED, DELIVERED - nothing else.
 *            Why not just use text like "PENDING"? Because a typing mistake like "PENDNIG"
 *            compiles fine and breaks at runtime. With an enum, the compiler catches it.
 *            Enums can also have their own fields, constructors and methods.
 * Run      : java -cp out topic16_enums.EnumsDemo
 * Try this : Add a CANCELLED status - the compiler will show you the switch you must update.
 */
public class EnumsDemo {

    enum OrderStatus {
        PLACED, SHIPPED, DELIVERED
    }

    // an enum with a field, a constructor and a method - each planet carries its own gravity
    enum Planet {
        MERCURY(3.7), EARTH(9.8), JUPITER(24.8);   // the value in brackets goes to the constructor

        private final double gravity;      // in m/s^2

        Planet(double gravity) {           // an enum constructor is always private - nobody outside can make new planets
            this.gravity = gravity;
        }

        double weightOf(double massKg) {
            return massKg * gravity;
        }
    }

    static String message(OrderStatus status) {
        // switch expression on an enum: it must handle EVERY value, otherwise it won't compile
        return switch (status) {
            case PLACED -> "We got your order";
            case SHIPPED -> "On the way";
            case DELIVERED -> "Enjoy!";
        };
    }

    public static void main(String[] args) {
        OrderStatus status = OrderStatus.SHIPPED;
        System.out.println(status + ": " + message(status));

        // methods every enum gets for free
        for (OrderStatus s : OrderStatus.values()) {                 // values() = all the constants, in order
            System.out.println("  " + s.ordinal() + " " + s.name()); // ordinal() = position (from 0), name() = the text
        }
        System.out.println("valueOf(\"DELIVERED\"): " + OrderStatus.valueOf("DELIVERED"));   // text -> enum
        System.out.println("compare with == is safe: " + (status == OrderStatus.SHIPPED));   // each constant exists only once, so == works

        for (Planet planet : Planet.values()) {
            System.out.printf("  70 kg on %-8s weighs %6.1f N%n", planet, planet.weightOf(70));
        }
    }
}
