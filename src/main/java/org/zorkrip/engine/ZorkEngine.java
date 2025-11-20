package org.zorkrip.engine;
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

import org.zorkrip.model.Exit;
import org.zorkrip.model.Player;
import org.zorkrip.model.Room;
import org.zorkrip.persistence.LoadCharacter;
import org.zorkrip.persistence.Loadmap;
import org.zorkrip.persistence.save;

import java.util.Map;

public class ZorkEngine {
    private final Parser parser;
    private static Player player;
    private static Map<String, Room> rooms;
    private boolean running;


    public ZorkEngine() {
        rooms = Loadmap.loadmap();
        player = new Player("Player", rooms.get("Outside"));
        parser = new Parser();
        running = true;
    }

    public ZorkEngine(String path) {
        rooms = Loadmap.loadmap(path);
        player = LoadCharacter.loadPlayer(path);
        parser = new Parser();
        running = true;

    }

    public boolean isRunning() {
        return running;
    }

    public String play(String s) {


        Command command = parser.getCommand(s);
        return processCommand(command);


    }

    public String printWelcome() {

        return "\n" +
                "Welcome to the University adventure!\n" +
                "Type 'help' if you need help." +
                "\n" +
                player.getCurrentRoom().getRoomDescription()+"\n";
    }

    private String processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            return "I don't understand your command...";
        }

        switch (commandWord) {
            case "help":
                return printHelp();

            case "go":
                return goRoom(command);
            case "take": {
                return take(command);

            }
            case "drop": {
                return drop(command);

            }

            case "open": {
                return open(command);

            }
            case "quit":
                if (command.hasSecondWord()) {

                    return "Quit what?";
                } else {

                    save.saveGame(rooms, player);

                    running = false;
                    return "QUIT"; // signal to quit
                }

            default:
                return "I don't know what you mean...";

        }

    }

    private String  printHelp() {
        return "You are lost. You are alone. You wander around the university.\n" +
                "Your command words are: " +
                parser.showCommands();
    }

    private String goRoom(Command command) {
        if (!command.hasSecondWord()) {

            return "Go where?";
        }

        String direction = command.getSecondWord();

        Exit exit = player.getCurrentRoom().getExit(direction);

        if (exit == null) {
            return "There is no door!"+"\n";
        } else {
            Room nextRoom = exit.getNeighbour();
            if (player.getCurrentRoom().getExit(direction).isLocked()) {
                player.setCurrentRoom(nextRoom);

                return player.getCurrentRoom().getRoomDescription();
            } else {
                return "door is locked"+"\n";
            }


        }

    }

    private String take(Command command) {
        if (!command.hasSecondWord()) {
            return "Pickup what\n";

        }

        String item = command.getSecondWord();

        Room room = player.getCurrentRoom();
        for (int i = 0; i < room.getNumberItems(); i++) {
            if (room.getItemAtIndex(i).getName().equalsIgnoreCase(item)) {
                player.addItem(room.getItemAtIndex(i));
                room.removeItem(i);

            }
        }
        return "There is no" + command.getSecondWord() + "to take\n";
    }


    private String drop(Command command) {
        if (!command.hasSecondWord()) {
            return "drop up what\n";

        }

        String item = command.getSecondWord();

        Room room = player.getCurrentRoom();
        for (int i = 0; i < player.getNumberItems(); i++) {
            if (player.getItemAtIndex(i).getName().equalsIgnoreCase(item)) {
                room.addItem(player.getItemAtIndex(i));
                player.removeItem(i);

            }
        }
        return "There is no" + command.getSecondWord() + "to drop\n";
    }

    private String open(Command command) {
        if (!command.hasSecondWord()) {
            return "Open what";

        } else {
            String target = command.getSecondWord();
            Exit exit = player.getCurrentRoom().getExit(target);
            if (exit.isLocked()) {
                return "Door is not locked";
            } else if (player.getIndexOfItem(exit.getKey()) != -1) {
                exit.unlock();
                player.removeItem(player.getIndexOfItem(exit.getKey()));
            } else {
                return "You dont have the right key\n";
            }

        }


        return "There is no" + command.getSecondWord() + "to open\n";
    }


//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("Load");
//        String choice = input.nextLine();
//
//        if (choice.equals("load")) {
//            System.out.println("Enter your save folder path");
//            String path = input.nextLine();
//            rooms = Loadmap.loadmap(path);
//            player = LoadCharacter.loadPlayer(path);
//            System.out.println(player.getCurrentRoom());
//
//        } else {
//            rooms = Loadmap.loadmap();
//            player = new Player("Player", rooms.get("Outside"));
//            player.setCurrentRoom(rooms.get("Outside"));
//
//
//
//
//
//        }
//
//
//        ZorkEngine game = new ZorkEngine();
//
//
//        game.play();
//    }
}
