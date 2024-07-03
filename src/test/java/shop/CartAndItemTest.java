package shop;

import org.junit.jupiter.api.Test;
import parser.JsonParser;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CartAndItemTest {

    /**
     * Test to verify total price is correctly updated after removing items.
     * This test is expected to fail given the current implementation flaw.
     */
    @Test
    void testTotalPriceAfterItemRemoval() {
        Cart cart = new Cart("test-cart");
        RealItem realItem = new RealItem();
        realItem.setPrice(100.0);
        cart.addRealItem(realItem);

        cart.deleteRealItem(realItem);
        assertEquals(0, cart.getTotalPrice(), "Total price should be zero after removing the item.");
    }

    /**
     * Test to verify handling of reading a multi-line JSON file.
     * This test illuminates the flaw in the JsonParser's readFromFile method.
     */
    @Test
    void testReadMultiLineJson() {
        JsonParser parser = new JsonParser();
        Cart cart = parser.readFromFile(new File("src/test/resources/multi-line-cart.json"));
        assertNotNull(cart, "Cart should not be null when reading a multi-line JSON.");
    }

    /**
     * Test to verify that adding a null item does not cause an exception.
     * This test is designed to highlight the lack of input validation.
     */
    @Test
    void testAddNullItem() {
        Cart cart = new Cart("test-cart");
        assertDoesNotThrow(() -> cart.addRealItem(null), "Adding a null real item should not throw an exception.");
    }

    /**
     * Test to verify consistent toString output.
     * This test checks the consistency and format of the toString output.
     */
    @Test
    void testToStringFormat() {
        RealItem realItem = new RealItem();
        realItem.setName("TestItem");
        realItem.setPrice(100.0);
        realItem.setWeight(10.0);

        String expected = "Class: shop.RealItem; Name: TestItem; Price: 100.0; Weight: 10.0";
        assertEquals(expected, realItem.toString(), "toString format should match the expected output.");
    }
}
