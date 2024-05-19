package se.kth.iv1350.progExe.controller;

public class OperationFailedException extends Exception {
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);

    }
}
