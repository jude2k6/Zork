package org.zorkrip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Room implements Serializable, Inventory {
    private final String description;
    private final Map<String, Exit> exits; // Map direction to neighboring Room
    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Npc> npcs;
    private Chest<Item> devChest;


    public Room(String description, String name) {


        exits = new LinkedHashMap<>();
        inventory = new ArrayList<>();
        npcs = new ArrayList<>();
        this.description = description;

    }

    public void setExit(String direction, Exit neighbor) {
        exits.put(direction, neighbor);
    }

    public Exit getExit(String direction) {
        return exits.get(direction);
    }

    public void addNpc(Npc npc) {
        if (npcs == null) {
            npcs = new ArrayList<>();
        }
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
        if (npcs == null) {
            return "";
        }
        for (Npc npc : npcs) {
            sb.append(npc.getName()).append(" ");
        }

        return sb.toString();
    }

    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            if (exits.get(direction).isVisible()) {
                sb.append(direction).append(" ");
            }

        }
        return sb.toString().trim();
    }

    public String getRoomDescription(Player player) {
        return getLongDescription() +
                "\n" +
                "Items: " + printItems(player) + "\n" + "Npcs: " + player.getCurrentRoom().getNpcs() + "\n\n";
    }

    public String getLongDescription() {
        return description + ".\nExits: " + getExitString();
    }

    @Override
    public List<Item> getInventory() {
        if (inventory == null) inventory = new ArrayList<>();
        return inventory;
    }

    public String getName() {
        return name;
    }

    public Chest<Item> getDevChest() {
        return devChest;
    }

    public void setDevChest(Chest<Item> devChest) {
        this.devChest = devChest;
    }

}

