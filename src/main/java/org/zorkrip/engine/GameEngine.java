package org.zorkrip.engine;


public interface GameEngine {
    String getWelcomeMessage();

    String handleInput(String input);

    String viewInventory();
    String viewRoom();

    void saveGameInterface(String path);
}
