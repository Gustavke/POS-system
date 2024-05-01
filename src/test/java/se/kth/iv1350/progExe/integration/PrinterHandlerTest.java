package se.kth.iv1350.progExe.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.progExe.model.ReceiptDTO;
import se.kth.iv1350.progExe.model.Sale;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrinterHandlerTest {
    private PrinterHandler printerHandler;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        printerHandler = new PrinterHandler();

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        printerHandler = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }


    @Test
    void print_PrintsReceiptCorrectly() {
        Sale sale = new Sale();
        sale.closeSale();
        ReceiptDTO receipt = sale.enterPayment(100);
        printerHandler.print(receipt);
        String printout = printoutBuffer.toString();

        assertTrue(printout.contains(receipt.toString()), "Printout does not match receipt contents");
    }
}