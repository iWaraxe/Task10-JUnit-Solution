package parser;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.*;
import shop.Cart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced tests for the JsonParser class focusing on various scenarios,
 * including error handling, special cases, and file operations.
 */
class JsonParserTestAdvanced {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private JsonParser jsonParser;
    private Cart testCart;

    @BeforeEach
    void setUp() {
        jsonParser = new JsonParser();
        testCart = new Cart("test-cart");
        // Mock or initialize testCart with some items for a meaningful data test scenario.
    }

    @AfterEach
    void tearDown() {
        // Clean up by deleting any test files created, ensuring tests do not interfere with each other.
        try {
            Files.deleteIfExists(Path.of(RESOURCES_PATH + testCart.getCartName() + ".json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test overwriting existing files to ensure JsonParser correctly updates files on write.
     * ★★★★★ - Essential for data integrity and ensuring updates are correctly persisted.
     */
    @Test
    void testWriteToFile_OverwriteExisting1() throws InterruptedException {
        String filename = RESOURCES_PATH + testCart.getCartName() + ".json";
        Path filePath = Paths.get(filename);
        jsonParser.writeToFile(testCart);
        Thread.sleep(1000); // Wait for 1 second to ensure the file system updates the timestamp.
        jsonParser.writeToFile(testCart); // write again to check overwrite
        long afterWriteModifiedTime = filePath.toFile().lastModified();
        assertTrue(afterWriteModifiedTime > System.currentTimeMillis() - 1000, "File should be overwritten with new content.");
    }

    /**
     * Ensure that serialization and deserialization processes are consistent.
     * ★★★★★ - Fundamental for validating the primary functionality of JsonParser.
     */
    @Test
    void testWriteAndReadConsistency() {
        String filename = RESOURCES_PATH + testCart.getCartName() + ".json";
        jsonParser.writeToFile(testCart);
        Cart readCart = jsonParser.readFromFile(new File(filename));
        assertAll("Cart should be the same before and after serialization",
                () -> assertEquals(testCart.getCartName(), readCart.getCartName(), "Cart names must match."),
                () -> assertEquals(testCart.getTotalPrice(), readCart.getTotalPrice(), "Total prices must match.")
                // Additional checks for each property can be added here.
        );
    }

    /**
     * Test parsing from a malformed JSON file to verify error handling capabilities.
     * ★★★★☆ - Critical for robust error handling and ensuring meaningful feedback is provided for correction.
     */
    @Test
    void testReadFromFile_MalformedJSON() {
        String filename = RESOURCES_PATH + "malformed.json";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardCharsets.UTF_8)) {
            writer.write("{invalid json}");
        } catch (IOException e) {
            fail("Setup of malformed.json failed", e);
        }

        File malformedFile = new File(filename);
        assertThrows(JsonSyntaxException.class, () -> jsonParser.readFromFile(malformedFile),
                "JsonParser should throw JsonSyntaxException for malformed JSON.");
    }

    /**
     * Verify the parser's ability to handle serialization of empty Cart objects.
     * ★★★★☆ - Important to ensure that the absence of items is handled gracefully, not resulting in errors or invalid files.
     */
    @Test
    void testEmptyCartSerialization() {
        Cart emptyCart = new Cart("empty-cart");
        String filename = RESOURCES_PATH + emptyCart.getCartName() + ".json";
        assertDoesNotThrow(() -> jsonParser.writeToFile(emptyCart), "Serialization of an empty cart should not throw an exception.");
        Cart readCart = jsonParser.readFromFile(new File(filename));
        assertNotNull(readCart, "Deserialized cart should not be null even if it was empty.");
        assertEquals("empty-cart", readCart.getCartName(), "Cart name should match for an empty cart.");
        assertEquals(0, readCart.getTotalPrice(), "Total price of an empty cart should be zero.");
    }

    /**
     * Test the parser's handling of special characters in cart names.
     * ★★★☆☆ - Useful for ensuring file naming conventions are adhered to and special characters are handled.
     */
    @Test
    void testHandlingSpecialCharactersInCartName() {
        Cart specialCharCart = new Cart("special:char?*cart");
        String filename = RESOURCES_PATH + specialCharCart.getCartName() + ".json";
        assertDoesNotThrow(() -> jsonParser.writeToFile(specialCharCart), "Writing should not throw despite special characters in name.");
        assertTrue(Files.exists(Paths.get(filename)),
                "File should exist, with special characters handled in the name.");
    }

    /**
     * Test to ensure that the content written to the file matches expectations.
     * ★★★☆☆ - Validates that the JsonParser not only writes files but writes them with the correct content.
     */
    @Test
    void testFileContentAfterWrite() {
        String filename = RESOURCES_PATH + testCart.getCartName() + ".json";
        jsonParser.writeToFile(testCart);
        String content = "";
        try {
            content = Files.readString(Path.of(filename));
        } catch (IOException e) {
            fail("IOException when reading file content", e);
        }
        assertFalse(content.isEmpty(), "File content should not be empty after write.");
        assertTrue(content.trim().startsWith("{\"cartName\":\"" + testCart.getCartName() + "\""),
                "File content should be in correct JSON format starting with the cart name.");
    }

    // Additional tests can follow the structure and rating system demonstrated above.
}
