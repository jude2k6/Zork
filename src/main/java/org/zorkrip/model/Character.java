package org.zorkrip.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


abstract class Character implements Serializable, Inventory {
    private final String name;
    private Room currentRoom;
    private ArrayList<Item> inventory;


    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }

    public String getName() {
        return name;
    }


    /// MOVEMENT
    public Room getCurrentRoom() {
        return currentRoom;
    }


    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }


    /// INVENTORY

    @Override
    public List<Item> getInventory() {
        if (inventory == null) inventory = new ArrayList<>();
        return inventory;
    }


}
