package se.kth.iv1350.progExe.model;

import se.kth.iv1350.progExe.integration.ItemDTO;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 * The Sale class represents a sale transaction for one customer.
 */
public class Sale {
    private LocalTime timeOfSale;
    private ArrayList<LineItem> itemsInSale = new ArrayList<>();
    private double totalPrice;
    private double VAT;
    private Payment payment;


    /**
     * Constructs a new Sale object for one customer with the current time.
     * The timeOfSale attribute is set to the current system time when the sale is initiated.
     */
    public Sale() {
        timeOfSale = LocalTime.now();
    }

    /**
     * Checks if an item with the specified ID is already entered in the sale.
     *
     * @param searchedItemID The ID of the item to search for.
     * @return True if the item is already entered in the sale; otherwise, false.
     */
    public boolean isAlreadyEntered(int searchedItemID) {
        for (LineItem lineItem : itemsInSale) {
            if (lineItem.getItemDTO().getItemID() == searchedItemID) {
                return true;
            }
        }
        return false;
    }

    /**
     * Increments the quantity of an item with the specified ID in the sale.
     *
     * @param itemID The ID of the item whose quantity to increment.
     * @return The item DTO of the incremented item if found; otherwise, null.
     */
    public ItemDTO incrementItemQuantity(int itemID) {
        for (LineItem lineItem : itemsInSale) {
            if (lineItem.getItemDTO().getItemID() == itemID) {
                lineItem.incrementQuantity();

                ItemDTO currentItemDTO = lineItem.getItemDTO();
                updateTotalPriceAndVAT(currentItemDTO);

                return currentItemDTO;
            }
        }
        return null;
    }

    /**
     * Adds a new item to the sale with the provided item DTO and quantity.
     *
     * @param itemDTO  The DTO representing the item to add.
     * @param quantity The quantity of the item to add.
     */
    public void addItem(ItemDTO itemDTO, int quantity) {
        if(itemDTO != null && quantity > 0) {
            LineItem newItem = new LineItem(itemDTO, quantity);
            itemsInSale.add(newItem);
            updateTotalPriceAndVAT(itemDTO);
        }
    }


    /**
     * Closes the current sale, creates a payment object, and calculates the total price including VAT.
     *
     * @return The total price of the sale including VAT.
     */
    public double closeSale(){
        double totalPriceInclVAT = totalPrice + VAT;
        payment = new Payment(totalPrice, VAT, totalPriceInclVAT);
        return totalPriceInclVAT;
    }


    /**
     * Enters the payment amount and generates a receipt.
     *
     * @param amount The amount paid by the customer.
     * @return The receipt DTO containing information about the sale and payment.
     */
    public ReceiptDTO enterPayment(double amount){
        payment.setAmountPaid(amount);
        ReceiptDTO receipt = new ReceiptDTO(this, payment);
        return receipt;
    }

    /**
     * Updates the total price and VAT of the sale based on the provided itemDTO.
     *
     * @param itemDTO The ItemDTO object containing information about the item being added to the sale.
     */
    private void updateTotalPriceAndVAT(ItemDTO itemDTO) {
        totalPrice += itemDTO.getPrice();
        VAT += itemDTO.getPrice() * itemDTO.getVAT();
    }


    public ArrayList<LineItem> getItemsInSale() {
        return itemsInSale;
    }

    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }

    class LineItem {
        private final ItemDTO itemDTO;
        private int quantity;

        public LineItem(ItemDTO itemDTO, int quantity) {
            this.itemDTO = itemDTO;
            this.quantity = quantity;
        }

        public ItemDTO getItemDTO() {
            return itemDTO;
        }

        public int getQuantity() {
            return quantity;
        }

        public void incrementQuantity() {
            quantity++;
        }
    }
}
