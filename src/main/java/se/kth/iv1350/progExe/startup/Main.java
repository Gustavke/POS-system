package se.kth.iv1350.progExe.startup;

import se.kth.iv1350.progExe.controller.Controller;
import se.kth.iv1350.progExe.integration.AccountingSystemHandler;
import se.kth.iv1350.progExe.integration.DiscountDatabaseHandler;
import se.kth.iv1350.progExe.integration.InventorySystemHandler;
import se.kth.iv1350.progExe.integration.PrinterHandler;
import se.kth.iv1350.progExe.view.View;

/**
 * The Main class contains the main method to start the application.
 */
public class Main {
    /**
     * The entry point of the application.
     * Initializes the necessary handlers and controller, creates the view, and starts the execution.
     *
     * @param args The application does not take any command-line parameters.
     */
    public static void main(String[] args) throws Exception {

        PrinterHandler prnt = new PrinterHandler();
        AccountingSystemHandler acc = new AccountingSystemHandler();
        DiscountDatabaseHandler disc = new DiscountDatabaseHandler();
        InventorySystemHandler inv = new InventorySystemHandler();

        Controller contr = new Controller(prnt, acc, disc, inv);

        View view = new View(contr);

        view.runFakeExecution();
    }
}
