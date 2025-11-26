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

import java.util.Map;

import static org.zorkrip.persistence.Save.saveGame;

public class ZorkEngine implements GameEngine {
    private final Parser parser;
    private static Player player;
    private static Map<String, Room> rooms;
    private final boolean running;


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

    @Override
    public String getWelcomeMessage() {
        return printWelcome();
    }

    @Override
    public String handleInput(String input) {
        return play(input);
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public String viewInventory() {
        return player.viewInventory();
    }

    @Override
    public void saveGameInterface(String path) {

        saveGame(rooms, player, path);
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
                player.getCurrentRoom().getRoomDescription(player) + "\n";
    }

    private String processCommand(Command command) {
        String commandWord = command.commandWord();

        if (commandWord == null) {
            return "I don't understand your command...\n";
        }

        return switch (commandWord) {
            case "help" -> printHelp();
            case "go" -> goRoom(command);
            case "take" -> take(command);
            case "drop" -> drop(command);
            case "open" -> open(command);
            case "use" -> use(command);
            default -> "I don't know what you mean...";
        };

    }

    private String use(Command command) {
        String item = command.secondWord();
        if (player.getItem(item) == null) {
            return item + " is not in your Inventory";
        }

        player.getItemAtIndex(player.getIndexOfItem(item)).use(player);
        player.removeItem(player.getIndexOfItem(item));
        return "";
    }

    private String printHelp() {
        return "You are lost. You are alone. You wander around the university.\n" +
                "Your command words are: " +
                parser.showCommands() + "\n";
    }

    private String goRoom(Command command) {
        if (!command.hasSecondWord()) {

            return "Go where?+\n";
        }

        String direction = command.secondWord();

        Exit exit = player.getCurrentRoom().getExit(direction);

        if (exit == null) {
            return "There is no door!" + "\n";
        } else {
            Room nextRoom = exit.getNeighbour();
            if (player.getCurrentRoom().getExit(direction).isLocked()) {
                player.setCurrentRoom(nextRoom);

                return player.getCurrentRoom().getRoomDescription(player);
            } else {
                return "door is locked" + "\n";
            }


        }

    }

    private String take(Command command) {
        if (!command.hasSecondWord()) {
            return "Pickup what\n";

        }

        String item = command.secondWord();

        Room room = player.getCurrentRoom();

        if (room.getVisableItem(item,player)==null){return "There is no " + item + " to take\n";}
        player.addItem(room.getItem(item));
        room.removeItem(room.getIndexOfItem(item));

        return "";
    }


    private String drop(Command command) {
        if (!command.hasSecondWord()) {
            return "drop up what\n";

        }

        String item = command.secondWord();
        Room room = player.getCurrentRoom();

        if (player.getItem(item) == null ) {
            return "There is no" + command.secondWord() + "to take\n";
        }
        room.addItem(player.getItem(item));
        player.removeItem(player.getIndexOfItem(item));
        return "";
    }

    private String open(Command command) {
        if (!command.hasSecondWord()) {
            return "Open what\n";

        } else {
            String target = command.secondWord();
            Exit exit = player.getCurrentRoom().getExit(target);
            if (exit.isLocked()) {
                return "Door is not locked\n";
            } else if (player.getIndexOfItem(exit.getKey()) != -1) {
                exit.unlock();
                player.removeItem(player.getIndexOfItem(exit.getKey()));
            } else {
                return "You don't have the right key\n";
            }

        }


        return "There is no" + command.secondWord() + "to open\n";
    }


}
