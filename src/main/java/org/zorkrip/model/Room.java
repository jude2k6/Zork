package org.zorkrip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Room implements Serializable, Inventory {
    private final String description;
    private final Map<String, Exit> exits; // Map direction to neighboring Room
    private ArrayList<Item> inventory;
    private ArrayList<Npc> npcs;

    public Room(String description) {


        exits = new LinkedHashMap<>();
        inventory = new ArrayList<>();
        npcs = new ArrayList<>();
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

    public void addNpc(Npc npc) {
        npcs.add(npc);
    }

    public Npc getNpc(String npcName) {
        for (Npc npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                return npc;
            }
        }
        return null;
    }

    public String getNpcs() {
        StringBuilder sb = new StringBuilder();
        if(npcs == null){return "";}
        for (Npc npc: npcs){
            sb.append(npc.getName()).append(" ");
        }

        return sb.toString();
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }


    public String getRoomDescription(Player player) {
        return getLongDescription() +
                "\n" +
                "Items: " + printItems(player) + "\n";
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

