package org.zorkrip;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room {
    private String description;
    private Map<String, Room> exits; // Map direction to neighboring Room
    private ArrayList<String> items;

    public Room(String description) {

        exits = new HashMap<>();
        items = new ArrayList<>();

    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public void removeExits(){
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
}
