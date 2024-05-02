package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.progExe.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale saleToTest;

    @BeforeEach
    void setUp() {
        saleToTest = new Sale();
    }

    @AfterEach
    void tearDown() {
        saleToTest = null;

    }

    @Test
    void testIsAlreadyEntered() {
        int itemID = 1;
        ItemDTO itemDTO = new ItemDTO(itemID, 10.0, "Item 1", 0.25);
        saleToTest.addItem(itemDTO, 1);

        assertTrue(saleToTest.isAlreadyEntered(itemID), "Item 1 should be already entered in the sale");
    }

    @Test
    void testIsNotAlreadyEntered() {
        int itemID = 1;
        ItemDTO itemDTO = new ItemDTO(itemID, 10.0, "Item 1", 0.25);
        saleToTest.addItem(itemDTO, 1);

        assertFalse(saleToTest.isAlreadyEntered(2), "Item 2 should not be already entered in the sale");
    }

    @Test
    void testIncrementItemQuantity() {

        int itemID = 1;
        ItemDTO itemDTO = new ItemDTO(itemID, 10.0, "Item 1", 0.25);
        saleToTest.addItem(itemDTO, 1);
        saleToTest.incrementItemQuantity(itemID);
        int result = saleToTest.getItemsInSale().getFirst().getQuantity();
        int expectedResult = 2;

        assertEquals(expectedResult, result, "Item quantity should be incremented by 1");
    }


    @Test
    void testAddItem() {
        int itemID = 1;
        ItemDTO itemToAdd = new ItemDTO(itemID, 10.0, "Item 1", 0.25);
        saleToTest.addItem(itemToAdd, 1);

        ItemDTO itemThatWasAdded = saleToTest.getItemsInSale().getFirst().getItemDTO();

        assertEquals(itemToAdd, itemThatWasAdded, "Added ItemDTO should match the ItemDTO in sale");
    }

    @Test
    void testAddItemNull() {
        ItemDTO nullItem = null;
        saleToTest.addItem(nullItem, 1);

        int itemsInSale = saleToTest.getItemsInSale().size();
        int expectedResult = 0;
        assertEquals(itemsInSale, expectedResult, "A null ItemDTO should not be added to the sale");
    }


    @Test
    void testCloseSale() {
        ItemDTO itemDTO = new ItemDTO(1, 10.0, "Item 1", 0.25);
        saleToTest.addItem(itemDTO, 1);
        double expectedResult = 10 + 10*0.25;
        double totalPriceInclVAT = saleToTest.closeSale();

        assertEquals(expectedResult, totalPriceInclVAT, "Total price incl. VAT was incorrect");
    }

    @Test
    void testEnterPayment() {
        double  price = 10;
        double VAT = 0.25;
        ItemDTO itemDTO = new ItemDTO(1, price, "Item 1", VAT);
        saleToTest.addItem(itemDTO, 1);
        saleToTest.closeSale();

        double totalToPay = price + price * VAT;
        double amountPaid = 20.0;

        ReceiptDTO receipt = saleToTest.enterPayment(amountPaid);

        assertNotNull(receipt, "Receipt should not be null");

        assertEquals(saleToTest.getTimeOfSale(), receipt.getTimeOfSale(), "Time of sale should match");
        assertEquals(saleToTest.getItemsInSale(), receipt.getItemsBought(), "Items bought should match");
        assertEquals(totalToPay, receipt.getTotalPrice(), "Total price should match");
        assertEquals(price * VAT, receipt.getVAT(), "VAT should match");

        assertEquals(amountPaid, receipt.getAmountPaid(), "Amount paid should match");
        assertEquals(amountPaid - totalToPay, receipt.getChange(), "Change should match");
    }

}