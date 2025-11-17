package org.zorkrip;

import java.io.Serializable;
import java.util.*;

public class Room implements Serializable, Inventory {
    private String description;
    private Map<String, Exit> exits; // Map direction to neighboring Room
    private ArrayList<Item> inventory;

    public Room(String description) {

        exits = new LinkedHashMap<>();
        inventory = new ArrayList<>();
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Exit neighbor) {
        exits.put(direction, neighbor);
    }

    public void removeExits() {
        exits = new HashMap<>();
    }

    public Exit getExit(String direction) {
        return exits.get(direction);
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }

    public String getLongDescription() {
        return "You are " + description + ".\nExits: " + getExitString();
    }


    @Override
    public List<Item> getInventory() {
        if (inventory == null) inventory = new ArrayList<>();
        return inventory;
    }


}

class Exit implements Serializable {

    private String direction;
    private Room neighbour;
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
        return locked;
    }

    public String getKey(){return key;}

    public void unlock(){
        locked = false;
    }
}
