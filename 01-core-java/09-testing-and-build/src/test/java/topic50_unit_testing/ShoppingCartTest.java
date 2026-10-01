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
 * Topic    : The JUnit 5 features you will use every day
 *   @BeforeEach          runs before EVERY test, so each test starts with a fresh, empty cart
 *   assertEquals         for decimals, give a small "delta" (allowed difference) -
 *                        because 0.1 + 0.2 is not EXACTLY 0.3 in a computer
 *   assertThrows         the test passes ONLY if the code throws that exception
 *   assertAll            checks several things and reports ALL the failures, not just the first one
 *   @ParameterizedTest   one test method, run again and again with different inputs
 *   @DisplayName         a readable name to show in the test report
 * Run      : ./mvnw test -Dtest=ShoppingCartTest
 */
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void newCart() {
        cart = new ShoppingCart();                      // a fresh cart before every single test
    }

    @Test
    void anEmptyCartCostsNothing() {
        assertEquals(0, cart.total(), 0.001);           // 0.001 = how close is "close enough" for a decimal
    }

    @Test
    void addingTheSameItemTwiceAddsUpItsPrice() {
        cart.add("pen", 10);
        cart.add("pen", 10);

        assertAll(                                      // check both, and report both if both fail
                () -> assertEquals(1, cart.itemCount()),
                () -> assertEquals(20, cart.total(), 0.001));
    }

    @Test
    void aNegativePriceIsRejected() {
        // assertThrows also hands back the exception, so we can check its message too
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> cart.add("pen", -1));
        assertEquals("price must not be negative: -1.0", error.getMessage());
    }

    @Test
    void removingSomethingNotInTheCartFails() {
        assertThrows(IllegalStateException.class, () -> cart.remove("ghost"));
    }

    // each line of @CsvSource is one run of this test: price, expected total
    @DisplayName("10% discount only above 1000")
    @ParameterizedTest(name = "a cart of {0} costs {1}")
    @CsvSource({
            "500,  500",
            "1000, 1000",          // exactly 1000: no discount. Always test the boundary on its own
            "2000, 1800"
    })
    void discountStartsAbove1000(double price, double expectedTotal) {
        cart.add("item", price);
        assertEquals(expectedTotal, cart.total(), 0.001);
    }
}
