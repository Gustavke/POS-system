package se.kth.iv1350.progExe.view;

import se.kth.iv1350.progExe.model.PaymentObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * A class that keeps track of total revenue and writes it to a text file.
 * Implements the PaymentObserver interface to get notified about new payments.
 */
public class TotalRevenueFileOutput implements PaymentObserver {
    private double totalRevenue;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    private static final String FILE_PATH = "totalRevenue.txt";

    /**
     * Called when a new payment is made. Updates the total revenue and writes the current state to a text file.
     *
     * @param payment The amount of the new payment.
     */
    @Override
    public void newPayment(double payment) {
        this.totalRevenue += payment;
        writeRevenueToFile();
    }

    /**
     * Writes the current total revenue to a text file.
     */
    private void writeRevenueToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            LocalDateTime timeStamp = LocalDateTime.now();
            writer.write("[" + timeStamp + "] Total revenue: " + decimalFormat.format(this.totalRevenue) + " EUR\n");
        } catch (IOException ioe) {
            System.out.println("Failed to log totalRevenue to file.");
            ioe.printStackTrace();
        }
    }
}

