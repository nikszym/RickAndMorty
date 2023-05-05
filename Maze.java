

import java.util.ArrayList;
import java.util.HashMap;

public class Maze {
private ArrayList<String> locations;
private HashMap<String, Meeseeks> meeseeksInRooms;
private HashMap<String, ArrayList<Item>> itemsInRooms;
private HashMap<String, ArrayList<Item>> requiredItems;
private int currentLocationIndex;
public Maze() {
    // Initialize instance variables
    locations = new ArrayList<>();
    meeseeksInRooms = new HashMap<>();
    itemsInRooms = new HashMap<>();
    requiredItems = new HashMap<>();
    currentLocationIndex = 0;

    // Add locations to the maze
    locations.add("Maze Entrance");
    locations.add("Puzzle Room 1");
    locations.add("Puzzle Room 2");
    locations.add("Puzzle Room 3");
    locations.add("Maze Center");

    // Add Meeseeks to some rooms
    meeseeksInRooms.put("Maze Entrance", new Meeseeks("lazy"));
    meeseeksInRooms.put("Puzzle Room 1", new Meeseeks("helpful"));
    meeseeksInRooms.put("Puzzle Room 2", new Meeseeks("mischievous"));
    meeseeksInRooms.put("Puzzle Room 3", new Meeseeks("playful"));

    // Add items to rooms
    ArrayList<Item> entranceItems = new ArrayList<>();
    entranceItems.add(new Item("Portal Gun"));
    itemsInRooms.put("Maze Entrance", entranceItems);

    ArrayList<Item> room1Items = new ArrayList<>();
    room1Items.add(new Item("Key Card"));
    itemsInRooms.put("Puzzle Room 1", room1Items);

    ArrayList<Item> room2Items = new ArrayList<>();

    // Add required items to progress through rooms
    ArrayList<Item> requiredForEntrance = new ArrayList<>();
    requiredForEntrance.add(new Item("Gemstone"));
    requiredItems.put("Maze Entrance", requiredForEntrance);

    ArrayList<Item> requiredForRoom1 = new ArrayList<>();
    requiredForRoom1.add(new Item("Key Card"));
    requiredItems.put("Puzzle Room 1", requiredForRoom1);

    ArrayList<Item> requiredForRoom2 = new ArrayList<>();
    requiredForRoom2.add(new Item("Lock"));
    requiredItems.put("Puzzle Room 2", requiredForRoom2);

    ArrayList<Item> requiredForRoom3 = new ArrayList<>();
    requiredForRoom3.add(new Item("Laser Cutter"));
    requiredForRoom3.add(new Item("Super Key Card")); // Update the required item for Puzzle Room 3
    requiredItems.put("Puzzle Room 3", requiredForRoom3);
}
//method to get current location
public String getCurrentLocation() {
    return locations.get(currentLocationIndex);
}
//method to move to next location
public void moveToNextLocation() {
    if (currentLocationIndex < locations.size() - 1) {
        currentLocationIndex++;
    } else {
        System.out.println("You have reached the end of the maze.");
    }
}
//method to check if meeseeks is in room
public boolean hasMeeseeks(String location) {
    return meeseeksInRooms.containsKey(location);
}
//method to get meeseeks
public Meeseeks getMeeseeks(String location) {
    return meeseeksInRooms.get(location);
}
//method to get items in room
public ArrayList<Item> getItemsInRoom(String location) {
    return itemsInRooms.getOrDefault(location, new ArrayList<>());
}
//method to pick up item
public Item pickUpItem(String location, String itemName) {
    ArrayList<Item> itemsInRoom = getItemsInRoom(location);
    for (Item item : itemsInRoom) {
        if (item.getName().equalsIgnoreCase(itemName)) {
            itemsInRoom.remove(item);
            return item;
        }
    }
    return null;
}
//method to check if player can move to next location
public boolean canMoveToNextLocation(Inventory inventory) {
    ArrayList<Item> requiredItemsForCurrentLocation = requiredItems.get(getCurrentLocation());
    // If there are no required items for the current location, return true
    if (requiredItemsForCurrentLocation == null) {
        return true;
    }
    // If the player has all the required items for the current location, return true

    for (Item requiredItem : requiredItemsForCurrentLocation) {
        // If the player does not have the required item, return false
        if (!inventory.hasItem(requiredItem.getName())) {
            return false;
        }
    }
    return true;
}
//method to get required item
public Item getRequiredItem(String location) {
    ArrayList<Item> requiredItemsForCurrentLocation = requiredItems.get(location);
    return requiredItemsForCurrentLocation.get(0);

}
//method to move to previous location
public void moveToPreviousLocation(){
    if (currentLocationIndex > 0) {
        currentLocationIndex--;
    } 
}
//method to check if player can move to previous location
public boolean canMoveToPreviousLocation(){
    return currentLocationIndex > 0;
}

}