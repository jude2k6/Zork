package org.zorkrip;

import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private final Map<String, String> validCommands;

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("look", "Look around");
        validCommands.put("eat", "Eat something");
        validCommands.put("take","Pickup something");
        validCommands.put("drop","Drop something");
        validCommands.put("open","Opens door");
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public String showAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("Valid commands are: ");
        for (String command : validCommands.keySet()) {
            sb.append(command).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
