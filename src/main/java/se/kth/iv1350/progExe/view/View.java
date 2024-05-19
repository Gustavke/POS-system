package se.kth.iv1350.progExe.view;

import se.kth.iv1350.progExe.controller.Controller;
import se.kth.iv1350.progExe.integration.ItemDTO;
import se.kth.iv1350.progExe.integration.UnknownItemIDException;
import se.kth.iv1350.progExe.util.LogHandler;

import java.text.DecimalFormat;

/**
 * The View class is a placeholder for an actual view.
 * It contains a hardcoded execution of a sale for demonstration purposes.
 * This class is responsible for presenting information to the user.
 */
public class View {

    private final Controller contr;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger = new LogHandler();

    public View(Controller contr) {
        this.contr = contr;
        contr.addPaymentObserver(new TotalRevenueView());
        contr.addPaymentObserver(new TotalRevenueFileOutput());
    }


    private static void printItemDetails(ItemDTO item, double runningTotalIncVAT) {
        System.out.println("\tItem:         " + item.getItemDescription());
        System.out.println("\tPrice:        " + item.getPrice() + " EUR");
        System.out.println("\tVAT:          " + item.getVAT() * 100 + " %");
        System.out.println("\tRunning total (incl. VAT): " + runningTotalIncVAT + " EUR\n");
    }

    /**
     * Simulates entering items and prints their details along with the running total
     */
    private void simulateEnterItems() {
        double runningTotalIncVAT = 0;
        for (int i = -1; i < 67; i += 11) {
            try {
                ItemDTO enteredItem = contr.enterItem(i, 1);
                System.out.println("Add 1 item with item id " + i + " :");
                runningTotalIncVAT += enteredItem.getPrice() + enteredItem.getPrice() * enteredItem.getVAT();
                printItemDetails(enteredItem, Math.ceil(runningTotalIncVAT * 100) / 100);
            } catch (UnknownItemIDException e) {
                errorMsgHandler.showErrorMsg("Item with ID "
                        + e.getItemIDThatIsUnknown() + " does not exist.");
            }
            catch(Exception exception){
                writeToLogAndUI("Failed to enter item, please try again.", exception);
            }
        }
    }


    /**
     * Simulates a complete sale taking place at the POS-system.
     */
    public void runFakeExecution() {
        DecimalFormat df = new DecimalFormat("#0.00");
        contr.startSale();
        System.out.println("A sale has been started. \n");

        simulateEnterItems();

        System.out.println("End Sale:");
        double totalPriceInclVAT = contr.closeSale();
        System.out.println("Total price (incl. VAT): " + df.format(totalPriceInclVAT) + " EUR\n");

        System.out.println("Enter payment: ");
        double amountPaid = 20;
        System.out.println("Customer pays: " + df.format(amountPaid) + " EUR\n");
        double change = contr.enterPayment(amountPaid);

        System.out.println("Change to give the customer : " + df.format(change) + " EUR\n");
    }

    private void writeToLogAndUI(String uiMsg, Exception exception){
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exception);
    }

}
