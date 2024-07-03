package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced tests for the RealItem class to ensure comprehensive testing of real item properties
 * and behaviors under various conditions.
 */
class RealItemTestAdvanced {

    private RealItem realItem;

    @BeforeEach
    void setUp() {
        // Initialize a RealItem object before each test for consistency and isolation.
        realItem = new RealItem();
    }

    /**
     * Verifies that the price set influences the string representation of RealItem.
     * ★★★★★ - Essential to confirm that changes in RealItem properties are accurately reflected
     * in its string representation, critical for data integrity and debugging.
     */
    @Test
    void testToStringAfterSettingPrice() {
        realItem.setName("Mountain Bike");
        realItem.setPrice(750.0); // Set price after name
        realItem.setWeight(14000.0); // Set weight last to observe order in toString doesn't matter

        assertTrue(realItem.toString().contains("Price: 750.0"), "toString output should include the price set.");
    }

    /**
     * Ensures the RealItem's name property is included and correctly formatted in the toString output.
     * ★★★★★ - Crucial for ensuring the item's identification is clearly and correctly represented,
     * supporting traceability and user interface display requirements.
     */
    @Test
    void testToStringIncludesName() {
        realItem.setName("Water Bottle");
        realItem.setPrice(25.0);
        realItem.setWeight(200.0);

        assertTrue(realItem.toString().contains("Name: Water Bottle"), "toString should accurately reflect the RealItem's name.");
    }

    /**
     * Tests the response of setWeight and getWeight methods to negative values.
     * ★★★☆☆ - Important to verify how the class handles unexpected weight values,
     * ensuring robustness in face of erroneous data.
     */
    @Test
    void testSetAndGetWeightWithNegativeValue() {
        double weight = -1000.5; // Negative weight value
        realItem.setWeight(weight);
        assertEquals(weight, realItem.getWeight(), "Weight should correctly handle negative values.");
    }

    /**
     * Verifies the behavior of the RealItem class when setting extremely large weight values.
     * ★★☆☆☆ - Useful for testing the class's ability to handle large numbers,
     * ensuring the application's reliability with unconventional data.
     */
    @Test
    void testSetAndGetWeightWithLargeValue() {
        double weight = Double.MAX_VALUE;
        realItem.setWeight(weight);
        assertEquals(weight, realItem.getWeight(), "Weight should be able to handle very large values.");
    }
}
