package se.kth.iv1350.progExe.controller;

import se.kth.iv1350.progExe.integration.AccountingSystemHandler;
import se.kth.iv1350.progExe.integration.DiscountDatabaseHandler;
import se.kth.iv1350.progExe.integration.InventorySystemHandler;
import se.kth.iv1350.progExe.integration.PrinterHandler;
import se.kth.iv1350.progExe.model.CashRegister;

public class Controller {

    private PrinterHandler prnt;
    private AccountingSystemHandler acc;
    private DiscountDatabaseHandler disc;
    private InventorySystemHandler inv;
    private CashRegister cashReg;

    public Controller(PrinterHandler prnt, AccountingSystemHandler acc, DiscountDatabaseHandler disc, InventorySystemHandler inv) {
        this.prnt = prnt;
        this.acc = acc;
        this.disc = disc;
        this.inv = inv;
        this.cashReg = new CashRegister();
    }
}
