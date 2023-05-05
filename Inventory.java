

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;

//declare an ArrayList of items
    public Inventory() {
        items = new ArrayList<>();
    }
//method to add item to arraylist of items
    public void addItem(Item item) {
        items.add(item);
    }
//method to remove item from arraylist of items
    public void removeItem(Item item) {
        Item itemToRemove = null;
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(item.getName())) {
                itemToRemove = i;
                break;
            }
        }
        if (itemToRemove != null) {
            items.remove(itemToRemove);
        }
    }
    //method to display items in inventory
    public void showInventory() {
        System.out.println("Inventory:");
        for (Item item : items) {
            System.out.println(" - " + item.getName());
        }
    }
    //method to check if item is in inventory
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
    //method to get item from inventory
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }


    
}