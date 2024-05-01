package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {

    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        cashRegister = new CashRegister();
    }

    @AfterEach
    void tearDown() {
        cashRegister = null;
    }

    @Test
    void testUpdateBalance() {
        double totalPrice = 20;
        cashRegister.updateBalance(totalPrice);
        cashRegister.updateBalance(totalPrice);

        assertEquals(totalPrice * 2, cashRegister.getBalance(), "Incorrect balance");
    }
}