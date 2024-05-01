package se.kth.iv1350.progExe.view;

import se.kth.iv1350.progExe.controller.Controller;
import se.kth.iv1350.progExe.integration.ItemDTO;

import java.text.DecimalFormat;

/**
 * The View class is a placeholder for an actual view.
 * It contains a hardcoded execution of a sale for demonstration purposes.
 * This class is responsible for presenting information to the user.
 */
public class View {

    private final Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }


    private static void printItemDetails(ItemDTO item, double runningTotalIncVAT) {
        System.out.println("\tItem:         " + item.getItemDescription());
        System.out.println("\tPrice:        " + item.getPrice()+" EUR");
        System.out.println("\tVAT:          " + item.getVAT()*100 +" %");
        System.out.println("\tRunning total (incl. VAT): " + runningTotalIncVAT + " EUR\n");
    }

    /**
     * Simulates entering items and prints their details along with the running total
     */
    private void simulateEnterItems() throws Exception {
        double runningTotalIncVAT = 0;
        for (int i = 0; i < 67; i += 11) {
            
            ItemDTO enteredItem = contr.enterItem(i, 1);
            System.out.println("Add 1 item with item id " + i + " :");

            if(enteredItem != null){
                runningTotalIncVAT += enteredItem.getPrice() + enteredItem.getPrice()*enteredItem.getVAT();
                printItemDetails(enteredItem, Math.ceil(runningTotalIncVAT * 100) / 100);
            } else {
                System.out.println("\tItem not found.\n");
            }
        }
    }


    /**
     * Simulates a complete sale taking place at the POS-system.
     */
    public void runFakeExecution() throws Exception {
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

}
