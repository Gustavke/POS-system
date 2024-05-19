package se.kth.iv1350.progExe.view;

import se.kth.iv1350.progExe.model.PaymentObserver;

import java.text.DecimalFormat;

/**
 * A view class that displays the total revenue of payments.
 * Implements the PaymentObserver interface to get notified about new payments.
 */
public class TotalRevenueView implements PaymentObserver {
    private double totalRevenue;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    /**
     * Called when a new payment is made. Updates the total revenue and prints the current state.
     *
     * @param payment The amount of the new payment.
     */
    @Override
    public void newPayment(double payment) {
        this.totalRevenue += payment;
        printCurrentState();
    }

    /**
     * Prints the current state of the total revenue to the console.
     */
    private void printCurrentState() {
        System.out.println("#### Total revenue ####");
        System.out.println(decimalFormat.format(this.totalRevenue) + " EUR");
        System.out.println("######################");
    }
}
