package topic50_unit_testing;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/*
 * Topic    : The JUnit 5 features you use every day
 *   @BeforeEach          runs before EVERY test: each test starts from a fresh cart
 *   assertEquals         with a delta for doubles (0.1 + 0.2 isn't exactly 0.3)
 *   assertThrows         the test passes only if the code throws that exception
 *   assertAll            checks several things and reports ALL failures, not just the first
 *   @ParameterizedTest   one test method, many inputs
 *   @DisplayName         a readable name in the test report
 * Run      : ./mvnw test -Dtest=ShoppingCartTest
 */
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void newCart() {
        cart = new ShoppingCart();
    }

    @Test
    void anEmptyCartCostsNothing() {
        assertEquals(0, cart.total(), 0.001);
    }

    @Test
    void addingTheSameItemTwiceAddsUpItsPrice() {
        cart.add("pen", 10);
        cart.add("pen", 10);

        assertAll(
                () -> assertEquals(1, cart.itemCount()),
                () -> assertEquals(20, cart.total(), 0.001));
    }

    @Test
    void aNegativePriceIsRejected() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> cart.add("pen", -1));
        assertEquals("price must not be negative: -1.0", error.getMessage());
    }

    @Test
    void removingSomethingNotInTheCartFails() {
        assertThrows(IllegalStateException.class, () -> cart.remove("ghost"));
    }

    @DisplayName("10% discount only above 1000")
    @ParameterizedTest(name = "a cart of {0} costs {1}")
    @CsvSource({
            "500,  500",
            "1000, 1000",          // exactly 1000: no discount - boundaries deserve their own case
            "2000, 1800"
    })
    void discountStartsAbove1000(double price, double expectedTotal) {
        cart.add("item", price);
        assertEquals(expectedTotal, cart.total(), 0.001);
    }
}
