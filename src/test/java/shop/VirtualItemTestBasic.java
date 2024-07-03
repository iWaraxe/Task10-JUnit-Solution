package shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the VirtualItem class.
 * Validates that VirtualItem properties are correctly managed and
 * that the class accurately represents its data in string form.
 */
class VirtualItemTestBasic {

    private VirtualItem virtualItem;

    @BeforeEach
    void setUp() {
        // Initialize a VirtualItem object before each test.
        virtualItem = new VirtualItem();
    }

    /**
     * Test setting and getting the size on disk.
     * ★★★★★ - Essential for verifying that VirtualItem properties are correctly manipulated.
     */
    @Test
    void testSetAndGetSizeOnDisk() {
        double sizeOnDisk = 5000.5; // Sample size on disk in megabytes
        virtualItem.setSizeOnDisk(sizeOnDisk);
        assertEquals(sizeOnDisk, virtualItem.getSizeOnDisk(), 0.01, "Size on disk should match the value set by setSizeOnDisk method.");
    }

    /**
     * Test the accuracy of the toString method.
     * ★★★★☆ - Important for ensuring VirtualItem's string representation includes all relevant information.
     */
    @Test
    void testToString() {
        // Setting sample values
        virtualItem.setName("Windows 10");
        virtualItem.setPrice(100.0);
        virtualItem.setSizeOnDisk(15000.0);

        String expected = "Class: class shop.VirtualItem; Name: Windows 10; Price: 100.0; Size on disk: 15000.0";
        String actual = virtualItem.toString();

        assertEquals(expected, actual, "toString should return the correct string representation of VirtualItem.");
    }
}
