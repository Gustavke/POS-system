package se.kth.iv1350.progExe.integration;

import se.kth.iv1350.progExe.model.ReceiptDTO;

import java.io.File;
import java.util.Scanner;

/**
 * The InventorySystemHandler class is responsible for handling interactions with the inventory system.
 */
public class InventorySystemHandler {
    public static final int ITEM_ID_TO_SIMULATE_FAILED_CONNECTION = -1;


    /**
     * Retrieves details of an item from the inventory system based on the provided item ID.
     *
     * @param itemID The ID of the item to retrieve details for.
     * @return An ItemDTO object containing the details of the item.
     * @throws UnknownItemIDException If the item ID is not found in the inventory system.
     * @throws InventorySystemException If there is a failure connecting to the external inventory system.
     */
    public ItemDTO getItemDetails(int itemID) throws UnknownItemIDException, InventorySystemException {
        if (itemID == ITEM_ID_TO_SIMULATE_FAILED_CONNECTION) {
            throw new InventorySystemException("Could not connect to external inventory system");
        }
        Scanner scanner = new Scanner(INVENTORY_DATA);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            int currentItemID = Integer.parseInt(parts[0]);
            if (currentItemID == itemID) {
                double price = Double.parseDouble(parts[1]);
                String description = parts[2];
                double VAT = Double.parseDouble(parts[3]);
                scanner.close();
                return new ItemDTO(itemID, price, description, VAT);
            }
        }
        scanner.close();
        throw new UnknownItemIDException(itemID);
    }

    /**
     * Represents the external inventory data:
     * <ul>
     *     <li>Column 1: itemID (integer)</li>
     *     <li>Column 2: price (double)</li>
     *     <li>Column 3: itemDescription (string)</li>
     *     <li>Column 4: VAT (double)</li>
     * </ul>
     */
    private static final String INVENTORY_DATA =
                    "1,2.50,Bananas,0.05\n" +
                    "2,1.99,Apples,0.05\n" +
                    "3,3.75,Oranges,0.05\n" +
                    "4,0.99,Lettuce,0.05\n" +
                    "5,4.49,Tomatoes,0.05\n" +
                    "6,2.25,Potatoes,0.05\n" +
                    "7,5.99,Chicken Breast,0.05\n" +
                    "8,3.50,Salmon Fillet,0.05\n" +
                    "9,1.25,Milk,0.05\n" +
                    "10,2.99,Eggs,0.05\n" +
                    "11,1.75,Bread,0.05\n" +
                    "12,2.49,Butter,0.05\n" +
                    "13,3.99,Cheese,0.05\n" +
                    "14,1.50,Rice,0.05\n" +
                    "15,4.25,Pasta,0.05\n" +
                    "16,2.99,Ground Beef,0.05\n" +
                    "17,5.50,Frozen Peas,0.05\n" +
                    "18,0.75,Carrots,0.05\n" +
                    "19,3.25,Onions,0.05\n" +
                    "20,1.99,Canned Tuna,0.05\n" +
                    "21,2.99,Cereal,0.05\n" +
                    "22,1.25,Yogurt,0.05\n" +
                    "23,2.50,Applesauce,0.05\n" +
                    "24,1.99,Jam,0.05\n" +
                    "25,3.75,Honey,0.05\n" +
                    "26,0.99,Bread Rolls,0.05\n" +
                    "27,4.49,Pizza,0.05\n" +
                    "28,2.25,Ice Cream,0.05\n" +
                    "29,5.99,Chocolate,0.05\n" +
                    "30,3.50,Chips,0.05\n" +
                    "31,1.25,Soda,0.05\n" +
                    "32,2.99,Juice,0.05\n" +
                    "33,1.75,Coffee,0.05\n" +
                    "34,2.49,Tea Bags,0.05\n" +
                    "35,3.99,Bottled Water,0.05\n" +
                    "36,1.50,Beer,0.05\n" +
                    "37,4.25,Wine,0.05\n" +
                    "38,2.99,Whiskey,0.05\n" +
                    "39,5.50,Vodka,0.05\n" +
                    "40,0.75,Lemon,0.05\n" +
                    "41,3.25,Garlic,0.05\n" +
                    "42,1.99,Ginger,0.05\n" +
                    "43,3.99,Avocado,0.05\n" +
                    "44,1.25,Lime,0.05\n" +
                    "45,2.50,Cucumber,0.05\n" +
                    "46,1.99,Bell Pepper,0.05\n" +
                    "47,3.75,Broccoli,0.05\n" +
                    "48,0.99,Zucchini,0.05\n" +
                    "49,4.49,Strawberries,0.05\n" +
                    "50,2.25,Blueberries,0.05";

    /**
     * Updates the inventory system with information from the receipt.
     *
     * @param receipt The receipt DTO containing information about the sale.
     */
    public void updateInventory(ReceiptDTO receipt) {
        // Do something to update the inventory system with the receipt information
    }
}
