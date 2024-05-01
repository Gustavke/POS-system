package se.kth.iv1350.progExe.startup;

import se.kth.iv1350.progExe.controller.Controller;
import se.kth.iv1350.progExe.integration.AccountingSystemHandler;
import se.kth.iv1350.progExe.integration.DiscountDatabaseHandler;
import se.kth.iv1350.progExe.integration.InventorySystemHandler;
import se.kth.iv1350.progExe.integration.PrinterHandler;
import se.kth.iv1350.progExe.view.View;

public class Main {
    public static void main(String[] args) {

        PrinterHandler prnt = new PrinterHandler();
        AccountingSystemHandler acc = new AccountingSystemHandler();
        DiscountDatabaseHandler disc = new DiscountDatabaseHandler();
        InventorySystemHandler inv = new InventorySystemHandler();

        Controller contr = new Controller(prnt, acc, disc, inv);

        View view = new View(contr);


        System.out.println("Hello world!");
    }
}