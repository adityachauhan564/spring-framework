package topic50_unit_testing;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Topic    : A class with business rules that are worth testing
 * Key idea : Every rule becomes one test in ShoppingCartTest:
 *              - an empty cart costs 0
 *              - a negative price is refused
 *              - the discount applies only ABOVE 1000
 *            If someone changes a rule by mistake later, a test turns red and catches it.
 * Test     : ./mvnw test -Dtest=ShoppingCartTest
 */
public class ShoppingCart {

    private final Map<String, Double> items = new LinkedHashMap<>();     // item name -> total price for that item

    public void add(String item, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("price must not be negative: " + price);
        }
        items.merge(item, price, Double::sum);       // same item again? add its price to the existing one
    }

    public void remove(String item) {
        if (items.remove(item) == null) {            // remove() gives null when the item wasn't there
            throw new IllegalStateException("not in the cart: " + item);
        }
    }

    public int itemCount() {
        return items.size();
    }

    // 10% off the whole cart when it costs MORE than 1000 (exactly 1000 gets no discount)
    public double total() {
        double sum = items.values().stream().mapToDouble(Double::doubleValue).sum();
        return sum > 1000 ? sum * 0.9 : sum;
    }
}
