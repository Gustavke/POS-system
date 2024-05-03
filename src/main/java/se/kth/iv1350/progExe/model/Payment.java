package se.kth.iv1350.progExe.model;

/**
 * The Payment class represents a payment made for a sale transaction.
 */
class Payment {
    private double totalPrice;
    private double totalVAT;
    private double totalToPay;
    private double amountPaid;
    private double change;

    /**
     * Constructs a Payment object with the specified total price, total VAT, and total amount to pay.
     *
     * @param totalPrice The total price of the items in the sale.
     * @param totalVAT The total VAT (Value Added Tax) of the items in the sale.
     * @param totalToPay The total amount to pay, including VAT.
     */
    public Payment(double totalPrice, double totalVAT, double totalToPay) {
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalToPay = totalToPay;
    }

    /**
     * Sets the amount paid by the customer and calculates the change.
     *
     * @param amountPaid The amount paid by the customer.
     */
    void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
        calculateChange();
    }

    void calculateChange() {
        change = amountPaid - totalToPay;
    }

    double getTotalPrice() {
        return totalPrice;
    }

    double getTotalVAT() {
        return totalVAT;
    }

    double getTotalToPay() {
        return totalToPay;
    }

    double getAmountPaid() {
        return amountPaid;
    }

    double getChange() {
        return change;
    }
}
