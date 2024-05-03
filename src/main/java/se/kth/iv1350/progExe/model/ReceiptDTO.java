package se.kth.iv1350.progExe.model;

import se.kth.iv1350.progExe.integration.ItemDTO;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * The ReceiptDTO class represents a receipt belonging to a sale.
 */
public class ReceiptDTO {
    private final LocalTime timeOfSale;
    private ArrayList<Sale.LineItem> itemsBought = new ArrayList<>();
    private final double totalPrice;
    private final double VAT;
    private final double amountPaid;
    private final double change;

    /**
     * Constructs a ReceiptDTO object with the specified sale and payment information.
     *
     * @param sale The sale object containing information about the transaction.
     * @param payment The payment object containing information about the payment.
     */
    ReceiptDTO(Sale sale, Payment payment) {
        this.timeOfSale = sale.getTimeOfSale();
        this.itemsBought = sale.getItemsInSale();
        this.totalPrice = payment.getTotalToPay();
        this.VAT = payment.getTotalVAT();
        this.amountPaid = payment.getAmountPaid();
        this.change = payment.getChange();
    }

    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }

    public ArrayList<Sale.LineItem> getItemsBought() {
        return itemsBought;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getVAT() {
        return VAT;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        StringBuilder sb = new StringBuilder();
        sb.append("------------- Receipt -------------\n");
        sb.append("Time of Sale: ").append(timeOfSale.truncatedTo(ChronoUnit.SECONDS)).append("\n");
        sb.append("----------------------------------\n");
        sb.append("Items Bought:\n");
        for (Sale.LineItem item : itemsBought) {
            sb.append(" - ").append(item.getItemDTO().getItemDescription())
                    .append(":    ").append(item.getQuantity()).append(" x ")
                    .append(df.format(item.getItemDTO().getPrice())).
                    append("    ").append(df.format(item.getItemDTO().getPrice() * item.getQuantity()))
                    .append(" EUR\n");
        }
        sb.append("----------------------------------\n");
        sb.append("Total Price: ").append(df.format(totalPrice)).append(" EUR\n");
        sb.append("Total VAT: ").append(df.format(VAT)).append(" EUR\n");
        sb.append("Amount Paid: ").append(df.format(amountPaid)).append(" EUR\n");
        sb.append("Change: ").append(df.format(change)).append(" EUR\n");
        sb.append("----------------------------------\n");
        return sb.toString();
    }

}
