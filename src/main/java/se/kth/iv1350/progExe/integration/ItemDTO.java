package se.kth.iv1350.progExe.integration;

/**
 * The ItemDTO class represents items.
 */
public class ItemDTO {
    private int itemID;
    private double price;
    private String itemDescription;
    private double VAT;


    /**
     * Constructs an ItemDTO object with the specified attributes.
     *
     * @param itemID The ID of the item.
     * @param price The price of the item.
     * @param itemDescription The description of the item.
     * @param VAT The VAT (Value Added Tax) rate applicable to the item.
     */
    public ItemDTO(int itemID, double price, String itemDescription, double VAT) {
        this.itemID = itemID;
        this.price = price;
        this.itemDescription = itemDescription;
        this.VAT = VAT;
    }

    public int getItemID() {
        return itemID;
    }

    public double getPrice() {
        return price;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getVAT() {
        return VAT;
    }

}
