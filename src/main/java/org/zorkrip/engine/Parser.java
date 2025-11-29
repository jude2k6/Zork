package org.zorkrip.engine;

public class Parser {
    private final CommandWords commands;


    public Parser() {
        commands = new CommandWords();

    }

    public Command getCommand(String s) {


        String word1 = null;
        String word2 = null;

        s=s.toLowerCase();
        String[] commandArray = s.split(" ", -1);

        if (!commandArray[0].isEmpty()) {
            word1 = commandArray[0];
            if (commandArray.length == 2) {
                word2 = commandArray[1];
            }
        }
        return new Command(word1, word2);

    }

    public String showCommands() {
        return commands.showAll();
    }

    public String getCommandDescription(String command) {
        return commands.getCommandDescription(command);
    }
}
