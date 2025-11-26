package org.zorkrip.engine;


public interface GameEngine {
    String getWelcomeMessage();
    String handleInput(String input);
    boolean isRunning();
    String viewInventory();
    Void saveGameInterface(String path);
}
