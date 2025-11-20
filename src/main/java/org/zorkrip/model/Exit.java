package org.zorkrip.model;

import java.io.Serializable;

public class Exit implements Serializable {

    private String direction;
    private final Room neighbour;
    private boolean locked;
    private String key;


    public Exit(String direction, Room neighbour) {
        this.direction = direction;
        this.neighbour = neighbour;
    }

    public Exit(String direction, Room neighbour, boolean locked, String key) {
        this.direction = direction;
        this.neighbour = neighbour;
        this.locked = locked;
        this.key = key;
    }

    public Room getNeighbour() {
        return neighbour;
    }

    public boolean isLocked() {
        return !locked;
    }

    public String getKey(){return key;}

    public void unlock(){
        locked = false;
    }
}
