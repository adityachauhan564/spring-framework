package topic50_unit_testing;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Topic    : A class with rules worth testing
 * Key idea : every rule (an empty cart is 0, a negative price is rejected, the discount applies
 *            only above 1000) is one test in ShoppingCartTest.
 * Test     : ./mvnw test -Dtest=ShoppingCartTest
 */
public class ShoppingCart {

    private final Map<String, Double> items = new LinkedHashMap<>();

    public void add(String item, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must not be negative: " + price);
        }
        items.merge(item, price, Double::sum);
    }

    public void remove(String item) {
        if (items.remove(item) == null) {
            throw new IllegalStateException("not in the cart: " + item);
        }
    }

    public int itemCount() {
        return items.size();
    }

    // 10% off the whole cart when it costs more than 1000
    public double total() {
        double sum = items.values().stream().mapToDouble(Double::doubleValue).sum();
        return sum > 1000 ? sum * 0.9 : sum;
    }
}
