package se.kth.iv1350.progExe.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.progExe.integration.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;
    PrinterHandler prnt = new PrinterHandler();
    DiscountDatabaseHandler disc = new DiscountDatabaseHandler();
    InventorySystemHandler inv = new InventorySystemHandler();
    AccountingSystemHandler acc = new AccountingSystemHandler();

    @BeforeEach
    void setUp() {
        controller = new Controller(prnt, acc, disc, inv);
        controller.startSale();
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void testStartSale() {
        assertNotNull(controller.getSale(), "Sale object should be initialized after calling startSale()");
    }

    @Test
    void testEnterValidItem() {
        ItemDTO itemDTO = null;
        try {
            itemDTO = controller.enterItem(1, 1);
            assertNotNull(itemDTO, "ItemDTO should be initialized after calling enterItem");
        } catch (Exception e) {
            fail("A valid item threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testEnterInvalidItem() {
        int invalidItemID = -2;
        ItemDTO itemDTO = null;
        try {
            itemDTO = controller.enterItem(invalidItemID, 1);
            fail("An invalid item ID should throw UnknownItemIDException");
        } catch (UnknownItemIDException e) {
            assertEquals("Unknown item ID: " + invalidItemID, e.getMessage(),
                    "Incorrect exception message");
            assertEquals(invalidItemID, e.getItemIDThatIsUnknown(),
                    "Exception returns incorrect item ID that is unknown");
        } catch (OperationFailedException e) {
            fail("UnknownItemIDException should have been thrown in the controller");
        }
    }

    @Test
    public void testEnterItemFailedConnection() {
        int simulateFailedConnectionID = InventorySystemHandler.ITEM_ID_TO_SIMULATE_FAILED_CONNECTION;
        try {
            controller.enterItem(simulateFailedConnectionID, 1);
            fail("Expected an OperationFailedException to be thrown");
        } catch (OperationFailedException e) {
            System.out.println(e.getMessage());
            assertEquals("Failed to get item details", e.getMessage(),
                    "Incorrect exception message");
        } catch (Exception e) {
            fail("Expected an InventorySystemException, but got an UnknownItemIDException");
        }
    }

    @Test
    void testEnterDuplicateItem() {
        try {
            controller.enterItem(1, 1);
            ItemDTO itemDTO = controller.enterItem(1, 1);
            assertNotNull(itemDTO, "ItemDTO should be returned from sale when adding duplicate items");
        } catch (Exception e) {
            fail("A valid item threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testCloseSaleNoItems() {
        double totalPrice = controller.closeSale();
        assertEquals(0, totalPrice, "Incorrect total price returned");
    }

    @Test
    void testCloseSaleWithItems() {
        try {
            controller.enterItem(1, 1);
            double totalPrice = controller.closeSale();
            assertTrue(totalPrice > 0, "Incorrect total price returned");
        } catch (Exception e) {
            fail("A valid item threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testEnterPayment() {
        ItemDTO itemDTO = new ItemDTO(9999, 10, "test item", 0.10);
        controller.getSale().addItem(itemDTO, 1);
        controller.closeSale();
        double change = controller.enterPayment(20);
        double expectedChange = 9;
        assertEquals(change, expectedChange, "Incorrect amount of change");

    }
}