package org.zorkrip.model;

import java.io.Serializable;

public class Exit implements Serializable {

    private final String direction;
    private final Room neighbour;
    private boolean locked;
    private String key;
    private boolean isVisible;



    public Exit(String direction, Room neighbour,boolean isVisible) {
        this.direction = direction;
        this.neighbour = neighbour;
        this.isVisible = isVisible;
    }

    public Exit(String direction, Room neighbour,boolean isVisible, boolean locked, String key) {
        this.direction = direction;
        this.neighbour = neighbour;
        this.isVisible = isVisible;
        this.locked = locked;
        this.key = key;
    }

    public Room getNeighbour() {
        return neighbour;
    }

    public boolean isLocked() {
        return !locked;
    }

    public boolean isVisible() {
        return isVisible;
    }
    public void setVisible(boolean b){isVisible=true;}

    public String getKey() {
        return key;
    }

    public void unlock() {
        locked = false;
    }
}
