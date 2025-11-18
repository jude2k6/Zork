package org.zorkrip;

public class Parser {
    private final CommandWords commands;


    public Parser() {
        commands = new CommandWords();

    }

    public Command getCommand(String s) {


        String word1 = null;
        String word2 = null;


        String[] commandArray = s.split(" ",-1);

        if (!commandArray[0].isEmpty()) {
            word1 = commandArray[0];
            if (commandArray.length ==2) {
                word2 = commandArray[1];
            }
        }

        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    public String showCommands() {
        return commands.showAll();
    }
}
