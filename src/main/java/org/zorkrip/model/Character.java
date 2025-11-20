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


    /// MOVMENT
    public Room getCurrentRoom() {
        return currentRoom;
    }


    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /*public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You moved to: " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way!");
        }
    }*/


    /// INVENTORY

    @Override
    public List<Item> getInventory() {
        if (inventory == null) inventory = new ArrayList<>();
        return inventory;
    }



}
