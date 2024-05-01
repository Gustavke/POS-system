package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testSetAmountPaid() {
        double totalPrice = 100.0;
        double totalVAT = 25.0;
        double totalToPay = totalPrice + totalVAT;
        double amountPaid = 150.0;

        Payment payment = new Payment(totalPrice, totalVAT, totalToPay);
        payment.setAmountPaid(amountPaid);

        assertEquals(amountPaid, payment.getAmountPaid(), "Amount paid is not set correctly");
        assertEquals(amountPaid - totalToPay, payment.getChange(), "Change is not calculated correctly");
    }

}