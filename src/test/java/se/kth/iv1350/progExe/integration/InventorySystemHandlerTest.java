package se.kth.iv1350.progExe.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemHandlerTest {

    private InventorySystemHandler inventorySystemHandler;

    @BeforeEach
    void setUp() {
        inventorySystemHandler = new InventorySystemHandler();
    }

    @Test
    void testGetItemDetailsValid() {
        int validItemID = 1;
        ItemDTO itemDTO = null;

        try {
            itemDTO = inventorySystemHandler.getItemDetails(validItemID);
            assertNotNull(itemDTO, "A valid itemDTO should be returned");
            assertEquals(validItemID, itemDTO.getItemID(),
                    "The returned item should have same ID as the searched item");
        } catch (Exception e) {
            fail("A valid item threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testGetItemDetailsInvalid() {
        int invalidItemID = -2;
        ItemDTO itemDTO;

        try {
            itemDTO = inventorySystemHandler.getItemDetails(invalidItemID);
            fail("An invalid item ID should throw UnknownItemIDException");
        } catch (UnknownItemIDException e) {
            assertEquals("Unknown item ID: " + invalidItemID, e.getMessage(),
                    "Incorrect exception message");
            assertEquals(invalidItemID, e.getItemIDThatIsUnknown(),
                    "Exception returns incorrect item ID that is unknown");
        } catch (InventorySystemException e) {
            fail("An invalid item ID should throw UnknownItemIDException");
        }

    }

    @Test
    public void testGetItemDetailsFailedConnection() {
        int simulateFailedConnectionID = InventorySystemHandler.ITEM_ID_TO_SIMULATE_FAILED_CONNECTION;
        try {
            inventorySystemHandler.getItemDetails(simulateFailedConnectionID);
            fail("Expected an InventorySystemException to be thrown");
        } catch (InventorySystemException e) {
            assertEquals("Could not connect to external inventory system", e.getMessage(),
                    "Incorrect exception message");
        } catch (UnknownItemIDException e) {
            fail("Expected an InventorySystemException, but got an UnknownItemIDException");
        }
    }
}