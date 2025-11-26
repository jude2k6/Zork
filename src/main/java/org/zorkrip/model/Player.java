package org.zorkrip.model;


public class Player extends Character {
    private boolean enhancedSight;

    public Player(String name, Room startingRoom){
        super(name, startingRoom);

    }


    public boolean isEnhancedSight() {
        return enhancedSight;
    }

    public void setEnhancedSight(boolean enhancedSight) {
        this.enhancedSight = enhancedSight;
    }
}
