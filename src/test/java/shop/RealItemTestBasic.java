package shop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the RealItem class to ensure correct handling of real item properties
 * and appropriate string representation.
 */
class RealItemTestBasic {

    private RealItem realItem;

    @BeforeEach
    void setUp() {
        // Initialize a RealItem object before each test to ensure test isolation.
        realItem = new RealItem();
    }

    /**
     * Verifies that the weight set using setWeight is accurately retrieved by getWeight.
     * ★★★★★ - Essential for ensuring that RealItem objects accurately represent their weight,
     * which is fundamental to their behavior and interactions.
     */
    @Test
    void testSetAndGetWeight() {
        double weight = 1000.5; // Example weight in grams
        realItem.setWeight(weight);
        assertEquals(weight, realItem.getWeight(), "Weight should match the value set by setWeight method.");
    }

    /**
     * Checks the toString method for correct formatting and inclusion of all RealItem properties.
     * ★★★★☆ - Very important for ensuring that RealItem objects are represented as strings correctly,
     * which is useful for logging, debugging, and user display purposes.
     */
    @Test
    void testToString() {
        // Setting sample values to test the toString output
        realItem.setName("TestProduct");
        realItem.setPrice(150.0);
        realItem.setWeight(1200.0);

        // The expected string includes 'class' keyword due to the use of getClass().getName() in the toString implementation
        String expected = "Class: class shop.RealItem; Name: TestProduct; Price: 150.0; Weight: 1200.0";
        String actual = realItem.toString();

        assertEquals(expected, actual, "toString should return the correct string representation of RealItem.");
    }
}
