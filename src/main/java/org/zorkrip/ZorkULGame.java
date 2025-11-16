package org.zorkrip;
/* This game is a classic text-based adventure set in a university environment.
   The player starts outside the main entrance and can navigate through different rooms like a 
   lecture theatre, campus pub, computing lab, and admin office using simple text commands (e.g., "go east", "go west").
    The game provides descriptions of each location and lists possible exits.

Key features include:
Room navigation: Moving among interconnected rooms with named exits.
Simple command parser: Recognizes a limited set of commands like "go", "help", and "quit".
Player character: Tracks current location and handles moving between rooms.
Text descriptions: Provides immersive text output describing the player's surroundings and available options.
Help system: Lists valid commands to guide the player.
Overall, it recreates the classic Zork interactive fiction experience with a university-themed setting, 
emphasizing exploration and simple command-driven gameplay
*/

import java.util.Map;
import java.util.Scanner;

public class ZorkULGame {
    private Parser parser;
    private static Character player;
    private static Map<String, Room> rooms;

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {

        // create the player character and start outside


    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the University adventure!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.print("Items: ");
        player.getCurrentRoom().printItems();
        System.out.print("player inv: ");
        player.printItems();

    }

    private boolean processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "take":{
                take(command);
                break;
            }
            case "drop":{
                drop(command);
                break;
            }
            case "quit":
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                } else {

                    save.saveGame(rooms, player);


                    return true; // signal to quit
                }

            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander around the university.");
        System.out.print("Your command words are: ");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
            System.out.print("Items: ");
            player.getCurrentRoom().printItems();
            System.out.print("player inv: ");
            player.printItems();

        }
    }
    private void take(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("Pick up what");
            return;
        }

        String item = command.getSecondWord();

        Room room = player.getCurrentRoom();
            for(int i =0;i<room.getNumberItems();i++){
                if(room.getItemAtIndex(i).getName().equalsIgnoreCase(item)){
                    player.addItem(room.getItemAtIndex(i));
                    room.removeItem(i);

                }
            }
    }




    private void drop(Command command){
        if (!command.hasSecondWord()) {
            System.out.println("drop up what");
            return;
        }

        String item = command.getSecondWord();

        Room room = player.getCurrentRoom();
        for(int i =0;i<player.getNumberItems();i++){
            if(player.getItemAtIndex(i).getName().equalsIgnoreCase(item)){
                room.addItem(player.getItemAtIndex(i));
                player.removeItem(i);

            }
        }
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Load");
        String choice = input.nextLine();

        if (choice.equals("load")) {
            System.out.println("Enter your save folder path");
            String path = input.nextLine();
            rooms = Loadmap.loadmap(path);
            player = LoadCharacter.loadPlayer(path);
            System.out.println(player.getCurrentRoom());

        } else {
            rooms = Loadmap.loadmap();
            player = new Player("Player", rooms.get("Outside"));
            player.setCurrentRoom(rooms.get("Outside"));

            rooms.get("Outside").addItem(new Item("bread","yujmy"));

        }


        ZorkULGame game = new ZorkULGame();


        game.play();
    }
}
