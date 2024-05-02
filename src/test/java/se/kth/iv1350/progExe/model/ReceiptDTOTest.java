package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.progExe.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptDTOTest {

    ReceiptDTO receiptDTO;
    Sale sale;
    ItemDTO testItemDTO = new ItemDTO(999, 40, "test Item", 0.1);

    @BeforeEach
    void setUp() {
        sale = new Sale();
        sale.addItem(testItemDTO, 1);
        sale.closeSale();
        receiptDTO = sale.enterPayment(100);
    }

    @AfterEach
    void tearDown() {
        receiptDTO = null;
        sale = null;
    }

    @Test
    void testToString() {
        String resultString = receiptDTO.toString();
        assertTrue(resultString.contains("------------- Receipt -------------"), "Missing receipt title");
        assertTrue(resultString.contains("Time of Sale:"), "Missing time of Sale");
        assertTrue(resultString.contains("Items Bought:"), "Missing Items Bought");
        assertTrue(resultString.contains("Total Price: 44.00 EUR"),  "Missing Total Price");
        assertTrue(resultString.contains("Total VAT: 4.00 EUR"), "Missing Total VAT");
        assertTrue(resultString.contains("Amount Paid: 100.00 EUR"), "Missing Amount Paid");
        assertTrue(resultString.contains("Change: 56.00 EUR"), "Missing Change");
    }
}