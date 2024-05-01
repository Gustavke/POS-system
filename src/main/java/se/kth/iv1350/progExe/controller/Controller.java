package se.kth.iv1350.progExe.controller;

import se.kth.iv1350.progExe.integration.*;
import se.kth.iv1350.progExe.model.CashRegister;
import se.kth.iv1350.progExe.model.ReceiptDTO;
import se.kth.iv1350.progExe.model.Sale;

/**
 * The Controller class is responsible for coordinating interactions between the view, model, and external systems.
 */
public class Controller {

    private final PrinterHandler prnt;
    private final AccountingSystemHandler acc;
    private final DiscountDatabaseHandler disc;
    private final InventorySystemHandler inv;
    private final CashRegister cashReg;

    private Sale sale;

    /**
     * Constructs a new Controller with the specified handlers for external systems.
     *
     * @param prnt The handler for the printer system.
     * @param acc  The handler for the accounting system.
     * @param disc The handler for the discount database system.
     * @param inv  The handler for the inventory system.
     */
    public Controller(PrinterHandler prnt, AccountingSystemHandler acc,
                      DiscountDatabaseHandler disc, InventorySystemHandler inv) {
        this.prnt = prnt;
        this.acc = acc;
        this.disc = disc;
        this.inv = inv;
        this.cashReg = new CashRegister();
    }

    /**
     * Starts a new sale. This method is called before items can be added to a sale.
     */
    public void startSale() {
        sale = new Sale();

    }

    /**
     * Enters an item into the sale.
     * If the item is already entered in the sale, its quantity is incremented.
     * If the item is not yet entered in the sale, its details are retrieved from the inventory system and added to the sale.
     *
     * @param itemID The ID of the item to enter.
     * @param quantity The quantity of the item to enter.
     * @return The entered item DTO if successful; otherwise, null.
     */
    public ItemDTO enterItem(int itemID, int quantity) throws Exception {
        ItemDTO enteredItemResult;

        if (sale.isAlreadyEntered(itemID)) {
            enteredItemResult = sale.incrementItemQuantity(itemID);
        } else {
            enteredItemResult = inv.getItemDetails(itemID);
            if (enteredItemResult != null) {
                sale.addItem(enteredItemResult, quantity);
            }
        }
        return enteredItemResult;
    }

    /**
     * Closes the current sale and returns the total price including VAT.
     *
     * @return The total price of the sale including VAT.
     */
    public double closeSale() {
        double totalPriceInclVAT = sale.closeSale();
        return totalPriceInclVAT;
    }

    /**
     * Enters the payment amount for the current sale, generates a receipt, and performs updates external systems.
     *
     * @param amount The amount paid by the customer.
     * @return The change to be returned to the customer.
     */
    public double enterPayment(double amount){
        ReceiptDTO receipt = sale.enterPayment(amount);

        prnt.print(receipt);
        cashReg.updateBalance(receipt.getTotalPrice());
        inv.updateInventory(receipt);
        acc.uppdateAccounting(receipt);

        return receipt.getChange();
    }

    public Sale getSale() {
        return sale;
    }
}


