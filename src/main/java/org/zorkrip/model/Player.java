package org.zorkrip.model;


public class Player extends Character {
    private boolean enhancedSight;
    private boolean win;

    public Player(String name, Room startingRoom) {
        super(name, startingRoom);
        getInventory().add(new Note());
        win = false;
    }


    public boolean isEnhancedSight() {
        return enhancedSight;
    }

    public void setEnhancedSight(boolean enhancedSight) {
        this.enhancedSight = enhancedSight;
    }


    public boolean getWin() {
        return win;
    }

    public void setWin(boolean b) {
        win = b;
    }
}
