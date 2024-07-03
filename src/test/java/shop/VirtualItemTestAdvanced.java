package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced tests for the VirtualItem class to assess handling of various scenarios,
 * focusing on property management and string representation accuracy.
 */
class VirtualItemTestAdvanced {

    private VirtualItem virtualItem;

    @BeforeEach
    void setUp() {
        // Initialize a VirtualItem object before each test for consistency and isolation.
        virtualItem = new VirtualItem();
    }

    /**
     * Verifies that the sizeOnDisk set influences the string representation of VirtualItem.
     * ★★★★★ - Essential to confirm that changes in VirtualItem properties are accurately reflected
     * in its string representation, critical for data integrity and user interface display.
     */
    @Test
    void testToStringAfterSettingSizeOnDisk() {
        virtualItem.setName("Office Suite");
        virtualItem.setPrice(200.0);
        virtualItem.setSizeOnDisk(5000.0); // Set size on disk

        assertTrue(virtualItem.toString().contains("Size on disk: 5000.0"), "toString output should include the size on disk set.");
    }

    /**
     * Ensures the VirtualItem's name property is included and correctly formatted in the toString output.
     * ★★★★★ - Crucial for ensuring the item's identification is clearly and correctly represented,
     * supporting traceability and user interface display requirements.
     */
    @Test
    void testToStringIncludesName() {
        virtualItem.setName("Anti-Virus");
        virtualItem.setPrice(39.99);
        virtualItem.setSizeOnDisk(1500.0);

        assertTrue(virtualItem.toString().contains("Name: Anti-Virus"), "toString should accurately reflect the VirtualItem's name.");
    }


    /**
     * Tests the response of setSizeOnDisk and getSizeOnDisk methods to negative values.
     * ★★★☆☆ - Important to verify how the class handles unexpected size values,
     * ensuring robustness in face of erroneous data.
     */
    @Test
    void testSetAndGetSizeOnDiskWithNegativeValue() {
        double sizeOnDisk = -1000.5; // Negative size value
        virtualItem.setSizeOnDisk(sizeOnDisk);
        assertEquals(sizeOnDisk, virtualItem.getSizeOnDisk(), "Size on disk should correctly handle negative values.");
    }


    /**
     * Verifies the behavior of the VirtualItem class when setting extremely large size values.
     * ★★☆☆☆ - Useful for testing the class's ability to handle large numbers,
     * ensuring the application's reliability with unconventional data.
     */
    @Test
    void testSetAndGetSizeOnDiskWithLargeValue() {
        double sizeOnDisk = Double.MAX_VALUE;
        virtualItem.setSizeOnDisk(sizeOnDisk);
        assertEquals(sizeOnDisk, virtualItem.getSizeOnDisk(), "Size on disk should be able to handle very large values.");
    }
}
