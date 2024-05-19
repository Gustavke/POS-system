package se.kth.iv1350.progExe.integration;

/**
 * This class represents an exception that is thrown when there is an issue with the inventory system.
 */
public class InventorySystemException extends Exception {
    /**
     * Creates a new instance of the exception with a specified error message.
     *
     * @param msg The error message describing the reason for the exception.
     */
    public InventorySystemException(String msg) {
        super(msg);
    }
}
