import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    // Instance variables
    private Maze maze;

    private Inventory inventory;
// Constructor
    public Game() {
        maze = new Maze();
        inventory = new Inventory();
    }
    //boolean to return if the player has won the game
    private boolean entrance1(Scanner scanner){
        String answer=scanner.nextLine();

        if(answer.equalsIgnoreCase("Y")){
            if(inventory.hasItem("Portal Gun")){
                inventory.removeItem(inventory.getItem("Portal Gun"));
                inventory.addItem(new Item("Gemstone"));
                System.out.println("Thank you!");
                System.out.println("Here is the Gemstone you need to get through to the next room!");
                return true;
            }
            else{
                System.out.println("It looks like you don't have the portal gun yet!");
                System.out.println("Get the item first, and then return to me when you have it!");
                return false;
            }
            
        }
        else{
            System.out.println("You're not going to help me?");
            System.out.println("I guess I'll have to help myself.");
            System.out.println("Guess you'll have to find another way to get through.\n\n\n");
            System.out.println("Game Over. You became stuck in the maze forever.");
            System.exit(0);
            return false;
        }
        
    }
//boolean to return if the player has won the game
    private boolean playMiniGame(Scanner scanner) {
        System.out.println("The Meeseeks challenges you to a mini-game!");
        int correctAnswers = 0;

        // Question 1
        System.out.println("Question 1: What is the color of the sky?");
        System.out.println("1. Red");
        System.out.println("2. Blue");
        System.out.println("3. Green");
        System.out.println("4. Yellow");
        System.out.print("Enter your answer (1, 2, 3, or 4): ");
        int answer = scanner.nextInt();

        if (answer == 2) {
            correctAnswers++;
        }

        // Question 2
        System.out.println("Question 2: What is 2 + 2?");
        System.out.println("1. 1");
        System.out.println("2. 2");
        System.out.println("3. 3");
        System.out.println("4. 4");
        System.out.print("Enter your answer (1, 2, 3, or 4): ");
        answer = scanner.nextInt();

        if (answer == 4) {
            correctAnswers++;
        }

        // Question 3
        System.out.println("Question 3: Which planet is closest to the sun?");
        System.out.println("1. Earth");
        System.out.println("2. Mars");
        System.out.println("3. Venus");
        System.out.println("4. Mercury");
        System.out.print("Enter your answer (1, 2, 3, or 4): ");
        answer = scanner.nextInt();

        if (answer == 4) {
            correctAnswers++;
        }

        return correctAnswers == 3;
    }
//boolean to return if the player has won the game
    private boolean laserCutter(Scanner scanner){
        System.out.println("Hi I'm Mr. Meeseeks!");
        System.out.println("I can help you get through the final room. You just need a laser cutter, right?");
        System.out.println("I will give it to you, but only if you can answer my question.");
        System.out.println("What type of lion never roars?");
        System.out.println("1. Mountain Lion");
        System.out.println("2. Dandelion");
        System.out.println("3. Lion Cub");
        System.out.println("4. A Captured Lion");
        int correctAnswers=0;
        int choice=scanner.nextInt();
        if(choice==2){
            correctAnswers++;
        }
        return correctAnswers==1;


    }
    //boolean to return if the player has won the game
    private boolean lock(Scanner scanner){
        System.out.println("Hello I am Mr. Meeseeks!");
        System.out.println("I can help you get through this maze, but I need you to help me first.");
        System.out.println("The door is missing a lock. I have the lock, but you need to help me with my java homework!");
        System.out.println("How do you declare and initialize a variable in java?");
        System.out.println("1. int variableName = 0;");
        System.out.println("2. int variableName = 0");
        System.out.println("3. variableName = 0");
        System.out.println("4. int variableName;");
        int correctAnswers=0;
        int choice=scanner.nextInt();
        if(choice==1){
            correctAnswers++;
        }
        System.out.println("How do you declare a method in java?");
        System.out.println("1. methodName()");
        System.out.println("2. public void methodName");
        System.out.println("3. public void methodName()");
        System.out.println("4. methodName");
        choice=scanner.nextInt();
        if(choice==3){
            correctAnswers++;
        }
        System.out.println("How do you initialize a scanner in java?");
        System.out.println("1. Scanner scanner = new Scanner(System.in)");
        System.out.println("2. Scanner scanner = new Scanner(System.in);");
        System.out.println("3. Scanner = new Scanner(System.in)");
        System.out.println("4. Scanner scanner = new (System.in)");
        choice=scanner.nextInt();

        if(choice==2){
            correctAnswers++;
        }
        scanner.nextLine();
        return correctAnswers==3;
    }

// Method to start the game
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Rick and Morty: Meeseeks Maze Madness!");
//while statement to keep the game running and let the player know what his or her options are
        while (true) {
            // Display current location
            String currentLocation = maze.getCurrentLocation();
            System.out.println("\nYou are currently in: " + currentLocation);
//if statement to check if the player has won the game
            if (currentLocation.equals("Maze Center")) {
                System.out.println("You've found the malfunctioning Meeseeks Box! Congratulations!");
                break;
            }

            Meeseeks meeseeks = null;


            // Interact with Meeseeks if there is one in the room
            if (maze.hasMeeseeks(currentLocation)) {
                meeseeks = maze.getMeeseeks(currentLocation);
                System.out.println("You encounter a Meeseeks with behavior: " + meeseeks.getBehavior());
            }

            // Interact with items in the room
            ArrayList<Item> itemsInRoom = maze.getItemsInRoom(currentLocation);
            if (!itemsInRoom.isEmpty()) {
                System.out.println("You see the following items in the room:");
                for (Item item : itemsInRoom) {
                    System.out.println(" - " + item.getName());
                }
            }

            // Display options
            System.out.println("What would you like to do?");
            System.out.println("1. Move to the next room");
            System.out.println("2. Check inventory");
            System.out.println("3. Pick up an item");
            System.out.println("4. Interact with Meeseeks");
            System.out.println("5. Go back to the previous room");
            System.out.print("Enter your choice (1, 2, 3, 4, or 5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            // If-else statement to check what the player wants to do

            if (choice == 1) {
                // Check if the player can move to the next location
                boolean canMove = maze.canMoveToNextLocation(inventory);
                if (canMove) {
                    //move to the next location, while also removing the item required for the location
                    inventory.removeItem(maze.getRequiredItem(currentLocation));
                    maze.moveToNextLocation();
                } else {
                    System.out.println("You need the required item(s) to move to the next room.");
                }
            } else if (choice == 2) {
                // Display inventory
                inventory.showInventory();
            } else if (choice == 3) {
                // Pick up an item
                if (!itemsInRoom.isEmpty()) {
                    System.out.print("Enter the name of the item you want to pick up: ");
                    String itemName = scanner.nextLine();
                    Item itemToPickUp = maze.pickUpItem(currentLocation, itemName);
                    // Add the item to the inventory if it exists
                    if (itemToPickUp != null) {
                        inventory.addItem(itemToPickUp);
                        System.out.println("You picked up the " + itemName + ".");
                    } else {
                        System.out.println("There is no such item in this room.");
                    }
                } else {
                    System.out.println("There are no items to pick up in this room.");
                }
                // Interact with Meeseeks
            }  else if (choice == 4) {
                if (meeseeks != null) {
                    String behavior = meeseeks.getBehavior();
                    // Display different messages based on the behavior of the Meeseeks
                    if (behavior.equals("helpful")) {
                        System.out.println("The helpful Meeseeks gives you some advice:");
                        System.out.println("The key card to move to the next room is an item in this room!");
                    } else if (behavior.equals("mischievous")) 
                    // Display different messages based on the behavior of the Meeseeks
                    {
                        System.out.println("The mischievous Meeseeks tries to confuse you:");
                        System.out.println("You should totally use the Portal Gun in the Maze Center!");
                    }
                    // Display different messages based on the behavior of the Meeseeks
                    else if(behavior.equals("lazy")){
                        System.out.println("The lazy Meeseeks just wants to go home.");
                    }
                    if (currentLocation.equals("Puzzle Room 1") && meeseeks != null) {
                        boolean wonMiniGame=false;
                        //do while loop to keep the game running until the player wins the game
                        
                        do{
                            wonMiniGame = playMiniGame(scanner);
                            if (wonMiniGame) {
                                System.out.println("Congratulations! You've won the mini-game! The Meeseeks gives you the required item to enter the next room.");
                                Item reward = new Item("Super Key Card");
                                //add the item to the inventory
                                inventory.addItem(reward);
                            } else {
                                System.out.println("You lost the mini-game. Try again!");
                            }

                        }while(!wonMiniGame);
                    }
                    //if statement to check if the player has won the game
                    if(currentLocation.equals("Puzzle Room 3")&& meeseeks!=null){
                        boolean wonMiniGame=false;
                        do{
                            //while statement to let the player try again if he or she loses the game
                            wonMiniGame=laserCutter(scanner);
                            if(wonMiniGame){
                                System.out.println("Congratulations! You can move on to the end of the maze.");
                                Item reward =new Item("Laser Cutter");
                                //add the item to the inventory
                                inventory.addItem(reward);
                            }else{
                                System.out.println("You have lost the mini game. Try again! ");

                            }
                        }while(!wonMiniGame);
                    }
                    //if statement to check the current location
                    if(currentLocation.equals("Puzzle Room 2")&& meeseeks !=null){
                        boolean wonMiniGame=false;
                        //while statement to let the player try again if he or she loses the game

                        do{
                            wonMiniGame=lock(scanner);
                            if(wonMiniGame){
                                System.out.println("Congratulations! You've won the mini-game! The Meeseeks gives you the required item to enter the next room.");
                                Item reward = new Item("Lock");
                                inventory.addItem(reward);
                            }
                            else{
                                System.out.println("You lost the mini-game.");
                                System.out.println("Game Over. You became stuck in the maze forever.");
                                System.exit(0);
                            }
                        }while(!wonMiniGame);
                    }
                    //if statement to check the current location
                    if(currentLocation.equals("Maze Entrance") && meeseeks !=null){
                        System.out.println("Hello I'm Mr. Meeseeks!");
                        System.out.println("I can help you get through this maze!");
                        System.out.println("I'll help you if you help me first.");
                        System.out.println("I need you to get me the portal gun over there. (Y/N)");
                        //boolean to start the mini game
                        boolean talkedToMeeseeks = entrance1(scanner);

                        if(talkedToMeeseeks){
                        }else{
                            continue;
                        }
                    }
                } else {
                    System.out.println("There is no Meeseeks in this room.");
                }
            }
            // Move to the previous location
            else if (choice == 5) {
                if(maze.canMoveToPreviousLocation()){
                    maze.moveToPreviousLocation();
                }else{
                    System.out.println("You are already at the beginning of the maze.");
                }
            }
        }
        scanner.close();
    }
}