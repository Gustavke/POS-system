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
    void testGetItemDetailsValid() throws Exception {
        int validItemID = 1;
        ItemDTO itemDTO = null;

        itemDTO = inventorySystemHandler.getItemDetails(validItemID);

        assertNotNull(itemDTO, "A valid itemDTO should be returned");
        assertEquals(validItemID, itemDTO.getItemID(),"The returned item should have same ID as the searched item");
    }

    @Test
    void testGetItemDetailsInvalid() throws Exception {
        int invalidItemID = -1;
        ItemDTO itemDTO;

        itemDTO = inventorySystemHandler.getItemDetails(invalidItemID);

        assertNull(itemDTO, "An invalid itemID should generate a null itemDTO");
    }
}