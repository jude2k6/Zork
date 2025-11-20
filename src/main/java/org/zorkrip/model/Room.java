package org.zorkrip.model;

import java.io.Serializable;
import java.util.*;

public class Room implements Serializable, Inventory {
    private final String description;
    private final Map<String, Exit> exits; // Map direction to neighboring Room
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


    public String getRoomDescription(){
        return getLongDescription() +
                "\n" +
                "Items: " + printItems();
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

