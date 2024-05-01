package se.kth.iv1350.progExe.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.progExe.integration.*;
import se.kth.iv1350.progExe.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller;
    PrinterHandler prnt = new PrinterHandler();
    DiscountDatabaseHandler disc = new DiscountDatabaseHandler();
    InventorySystemHandler inv = new InventorySystemHandler();
    AccountingSystemHandler acc = new AccountingSystemHandler();

    @BeforeEach
    void setUp() {
        controller =  new Controller(prnt, acc, disc, inv);
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
    void testEnterValidItem() throws Exception {
        ItemDTO itemDTO = controller.enterItem(1, 1);
        assertNotNull(itemDTO, "ItemDTO should be initialized after calling enterItem");
    }

    @Test
    void testEnterInvalidItem() throws Exception {
        ItemDTO itemDTO = controller.enterItem(-1, 1);
        assertNull(itemDTO, "ItemDTO should not be initialized after calling enterItem with invalid itemID");
    }

    @Test
    void testEnterDuplicateItem() throws Exception {
        controller.enterItem(1, 1);
        ItemDTO itemDTO = controller.enterItem(1, 1);
        assertNotNull(itemDTO, "ItemDTO should be returned from sale when adding duplicate items");
    }

    @Test
    void testCloseSaleNoItems() {
        double totalPrice = controller.closeSale();
        assertEquals(0, totalPrice, "Incorrect total price returned");
    }

    @Test
    void testCloseSaleWithItems() throws Exception {
        controller.enterItem(1, 1);
        double totalPrice = controller.closeSale();
        assertTrue(totalPrice > 0, "Incorrect total price returned");
    }

    @Test
    void enterPayment() {
    }
}