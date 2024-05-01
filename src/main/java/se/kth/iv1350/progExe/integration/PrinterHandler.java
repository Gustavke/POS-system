package se.kth.iv1350.progExe.integration;

import se.kth.iv1350.progExe.model.ReceiptDTO;

/**
 * The PrinterHandler class is responsible for handling interactions with the printer.
 */
public class PrinterHandler {

    /**
     * Prints the specified receipt.
     *
     * @param receipt The receipt DTO to be printed.
     */
    public void print(ReceiptDTO receipt) {
        System.out.println(receipt);
    }
}
