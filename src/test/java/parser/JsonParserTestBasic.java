package parser;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the JsonParser class.
 * This class provides a suite of tests to ensure the JsonParser's correctness and robustness
 * in handling various scenarios, from basic functionality to error handling.
 */
public class JsonParserTestBasic {

    private final String basePath = "src/main/resources/";
    private JsonParser jsonParser;
    private Cart testCart;

    @BeforeEach
    void setUp() {
        // Initialize common test setup: creating a JsonParser instance and a test Cart.
        jsonParser = new JsonParser();
        testCart = new Cart("test-cart");
        // Initialize testCart with some items if necessary.
        // Initialize testCart with some items.
        RealItem car = new RealItem();
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560); // Weight in grams

        VirtualItem game = new VirtualItem();
        game.setName("Cyberpunk 2077");
        game.setPrice(59.99);
        game.setSizeOnDisk(70000); // Size on disk in megabytes

        // Adding the items to the cart
        testCart.addRealItem(car);
        testCart.addVirtualItem(game);
    }

    /**
     * Test writing a Cart object to a file.
     * ★★★★★ - Essential to ensure the core functionality of serializing Cart objects into JSON format.
     */
    @Test
    void testWriteToFile() {
        String filename = basePath + testCart.getCartName() + ".json";
        jsonParser.writeToFile(testCart);
        // Verify the file has been created.
        assertTrue(Files.exists(Path.of(filename)), "File should exist after writing to it.");
        // Cleanup: delete the file after the test to avoid clutter.
        new File(filename).delete();
    }

    /**
     * Test reading a Cart object from a file.
     * ★★★★★ - Critical for validating the parser's ability to deserialize Cart objects from JSON format.
     */
    @Test
    void testReadFromFile() {
        // Setup: first write a Cart to a file to ensure there's something to read.
        String filename = basePath + testCart.getCartName() + ".json";
        jsonParser.writeToFile(testCart);
        // Action: read the Cart back from the file.
        Cart readCart = jsonParser.readFromFile(new File(filename));
        // Verification: ensure the Cart read is not null, implying successful deserialization.
        assertNotNull(readCart, "Deserialized cart should not be null.");
        // Cleanup: delete the file after the test.
        new File(filename).delete();
    }

    /**
     * Test reading a cart from a JSON file.
     * ★★★★★ - Essential for validating data integrity and correct deserialization.
     * This test ensures that the JsonParser can accurately reconstruct a Cart object from its JSON representation,
     * preserving the correctness of cart details such as name, items, and total price.
     */
    @Test
    void testReadFromAndewCartJson() {
        File file = new File("src/main/resources/andrew-cart.json");
        Cart cart = jsonParser.readFromFile(file);

        assertAll("Verifying Cart loaded from andrew-cart.json",
                () -> assertEquals("andrew-cart", cart.getCartName(), "Cart name should match JSON file."),
                () -> assertEquals(38445.48, cart.getTotalPrice(), 0.01, "Total price should match the expected value from JSON.")
        );
    }

    @Test
    void testReadFromEugenCartJson() {
        File file = new File("src/main/resources/eugen-cart.json");
        Cart cart = jsonParser.readFromFile(file);

        assertAll("Verifying Cart loaded from eugen-cart.json",
                () -> assertEquals("eugen-cart", cart.getCartName(), "Cart name should match JSON file."),
                () -> assertEquals(26560.68, cart.getTotalPrice(), 0.01, "Total price should match the expected value from JSON.")
        );
    }

    /**
     * Test the parser's error handling when attempting to read from non-existent files.
     * ★★★★☆ - Important for ensuring the parser robustly handles error scenarios without crashing.
     */
    @ParameterizedTest
    @ValueSource(strings = {"nonexistent1.json", "nonexistent2.json", "nonexistent3.json", "nonexistent4.json", "nonexistent5.json"})
    void testReadFromFileException(String filename) {
        // Setup: define a path to a file that does not exist.
        File file = new File(basePath + filename);
        // Action & Verification: assert that attempting to read from a non-existent file throws a NoSuchFileException.
        assertThrows(NoSuchFileException.class, () -> jsonParser.readFromFile(file), "Expected NoSuchFileException for non-existent file.");
    }
}