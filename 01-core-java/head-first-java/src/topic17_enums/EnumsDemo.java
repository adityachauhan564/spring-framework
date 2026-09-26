package topic17_enums;

/*
 * Topic    : Enums
 * Key idea : an enum is a fixed set of named constants - safer than magic strings
 *            like "PENDING". Enums can have fields, constructors and methods.
 * Run      : java -cp out topic17_enums.EnumsDemo
 * Try this : add a CANCELLED status and let the compiler show you the switch to update.
 */
public class EnumsDemo {

    enum OrderStatus {
        PLACED, SHIPPED, DELIVERED
    }

    // an enum with a field, a constructor and a method
    enum Planet {
        MERCURY(3.7), EARTH(9.8), JUPITER(24.8);

        private final double gravity;      // m/s^2

        Planet(double gravity) {           // enum constructors are always private
            this.gravity = gravity;
        }

        double weightOf(double massKg) {
            return massKg * gravity;
        }
    }

    static String message(OrderStatus status) {
        // switch expression: must cover every constant, or it won't compile
        return switch (status) {
            case PLACED -> "We got your order";
            case SHIPPED -> "On the way";
            case DELIVERED -> "Enjoy!";
        };
    }

    public static void main(String[] args) {
        OrderStatus status = OrderStatus.SHIPPED;
        System.out.println(status + ": " + message(status));

        // built-in methods
        for (OrderStatus s : OrderStatus.values()) {
            System.out.println("  " + s.ordinal() + " " + s.name());
        }
        System.out.println("valueOf(\"DELIVERED\"): " + OrderStatus.valueOf("DELIVERED"));
        System.out.println("compare with == is safe: " + (status == OrderStatus.SHIPPED));

        for (Planet planet : Planet.values()) {
            System.out.printf("  70 kg on %-8s weighs %6.1f N%n", planet, planet.weightOf(70));
        }
    }
}
