package org.zorkrip;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Room implements Serializable, Inventory {
    private String description;
    private Map<String, Room> exits; // Map direction to neighboring Room
    private ArrayList<Item> inventory;

    public Room(String description) {

        exits = new HashMap<>();
        inventory = new ArrayList<>();
        this.description=description;

    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public void removeExits() {
        exits = new HashMap<>();
    }

    public Room getExit(String direction) {
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


