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

import org.zorkrip.model.*;
import org.zorkrip.persistence.LoadCharacter;
import org.zorkrip.persistence.Loadmap;

import java.util.Map;

import static org.zorkrip.persistence.Save.saveGame;

public class ZorkEngine implements GameEngine {
    private static Player player;
    private static Map<String, Room> rooms;
    private final Parser parser;


    public ZorkEngine() {
        rooms = Loadmap.loadmap();
        player = new Player("Player", rooms.get("Changing-Rooms"));

        parser = new Parser();

    }

    public ZorkEngine(String path) {
        rooms = Loadmap.loadmap(path);
        player = LoadCharacter.loadPlayer(path);

        parser = new Parser();


    }

    @Override
    public String getWelcomeMessage() {
        return printWelcome();
    }

    @Override
    public String handleInput(String input) {
        return play(input);
    }


    @Override
    public String viewInventory() {
        return player.viewInventory();
    }

    @Override
    public String viewRoom() {
        return player.getCurrentRoom().getName();
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

        return "You wake up in a cramped Penneys changing room,\n" + "head throbbing, pockets empty,\n" + "and a crumpled shopping list from your wife stuck to your shoe.\n\n" + "You have no money, no plan,\n" + "and absolutely no idea how you got here.\n\n" + "Type 'help' if you need help.\n" + "Exits: " + player.getCurrentRoom().getExitString() + "\n";

    }

    private String processCommand(Command command) {

        String commandWord = command.commandWord();


        return switch (commandWord) {
            case "help" -> printHelp(command);
            case "go", "move" -> goRoom(command);
            case "take", "pick-up" -> take(command);
            case "drop" -> drop(command);
            case "open" -> open(command);
            case "use", "eat" -> use(command);
            case "inspect" -> inspect(command);
            case "talk" -> talk(command);
            case "push" -> push(command);
            default -> "I don't know what you mean...\n";
        };
    }

    private String talk(Command command) {
        if (!command.hasSecondWord()) {
            return "Talk to who?\n";
        }
        Npc npc = player.getCurrentRoom().getNpc(command.secondWord());
        if (npc == null) {
            return "There is no " + command.secondWord() + " to talk to.\n";

        }
        return npc.talk(player);

    }

    private String inspect(Command command) {
        if (!command.hasSecondWord()) {
            return "Inspect what?\n";

        }

        String item = command.secondWord();
        if (player.getItem(item) == null) {
            return "There is no " + command.secondWord() + " to inspect.\n";

        }

        return item + ": " + player.getItem(item).getDescription() + "\n";

    }

    private String use(Command command) {
        String item = command.secondWord();
        if (player.getItem(item) == null) {
            return item + " is not in your inventory.\n";

        }

        player.getItemAtIndex(player.getIndexOfItem(item)).use(player);
        player.removeItem(player.getIndexOfItem(item));
        return "";
    }

    private String printHelp(Command command) {
        if (command.hasSecondWord()) {
            String commandDescription = parser.getCommandDescription(command.secondWord());
            if (commandDescription == null) {
                return command.secondWord() + " is not a command.\n";

            }
            return commandDescription;

        }


        return "You are confused, under pressure, and definitely being judged.\n" + parser.showCommands() + "\n" + "Type 'help <command>' to learn what a specific command does.\n" + "When in doubt, read your list, keep moving, and avoid eye contact.\n";


    }

    private String goRoom(Command command) {
        if (!command.hasSecondWord()) {

            return "Go where?\n";

        }

        String direction = command.secondWord();
        Exit exit = player.getCurrentRoom().getExit(direction);

        if (exit == null) {
            return "There is no door!\n";
        } else if (!exit.isVisible()) {
            return "There is no door!\n";
        } else {
            Room nextRoom = exit.getNeighbour();
            if (player.getCurrentRoom().getExit(direction).isLocked()) {
                if (nextRoom.getName() == "Exits") {
                    if (!player.getWin()) {
                        return "You dont have all the items required to win.";
                    }
                }
                player.setCurrentRoom(nextRoom);

                return player.getCurrentRoom().getRoomDescription(player);
            } else {
                return "The door is locked.\n";
            }


        }

    }

    private String take(Command command) {
        if (!command.hasSecondWord()) {
            return "Pick up what?\n";
        }
        String item = command.secondWord();
        Room room = player.getCurrentRoom();
        if (room.getVisableItem(item, player) == null) {
            return "There is no " + item + " to take.\n";
        }
        player.addItem(room.getItem(item));
        room.removeItem(room.getIndexOfItem(item));
        return "";
    }


    private String drop(Command command) {
        if (!command.hasSecondWord()) {
            return "Drop what?\n";
        }
        String item = command.secondWord();
        Room room = player.getCurrentRoom();
        if (player.getItem(item) == null) {
            return "You don't have " + item + " to drop.\n";
        }
        room.addItem(player.getItem(item));
        player.removeItem(player.getIndexOfItem(item));
        return "";
    }

    private String push(Command command) {
        if (!command.hasSecondWord()) {
            return "Push what?\n";
        }

        String itemName = command.secondWord();

        // Check room first
        Item item = player.getCurrentRoom().getItem(itemName);
        if (item == null) {
            return "There is no " + itemName + " to push.\n";
        }

        if (!item.isPushable()) {
            return "You can't push the " + itemName + ".\n";
        }

        item.use(player);

        return "You push the " + itemName + ". It moves slightly.\n";
    }



    private String open(Command command) {
        if (!command.hasSecondWord()) {
            return "Drop what?\n";
        } else {
            String target = command.secondWord();
            Exit exit = player.getCurrentRoom().getExit(target);
            if (exit == null) {
                return "There is no " + target + " to open.\n";
            }
            if (exit.isLocked()) {
                return "That door isn’t locked.\n";
            } else if (player.getIndexOfItem(exit.getKey()) != -1) {
                exit.unlock();
                player.removeItem(player.getIndexOfItem(exit.getKey()));
            } else {
                return "You don't have the right key.\n";
            }
        }
        return "";
    }
}
