package org.zorkrip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;


public class Mapbuilder {

    public static void main(String[] args) {

        try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/org/zorkrip/map.json")) {
            Gson gson = new Gson();

            Type type = new TypeToken<Map<String, Room>>() {
            }.getType();
            Map<String, Room> rooms = gson.fromJson(reader, type);

            // Outside
            rooms.get("Outside").setExit("east", new Exit("east", rooms.get("Theatre"), true, "rusty-key"));
            rooms.get("Outside").setExit("south", new Exit("south", rooms.get("Lab")));
            rooms.get("Outside").setExit("west", new Exit("west", rooms.get("Pub")));
            rooms.get("Outside").addItem(new Item("Bread", "yummy"));
            rooms.get("Outside").addItem(new Item("rusty-key", "f67"));

// Theatre
            rooms.get("Theatre").setExit("west", new Exit("west", rooms.get("Outside")));

// Pub
            rooms.get("Pub").setExit("east", new Exit("east", rooms.get("Outside")));

// Lab
            rooms.get("Lab").setExit("north", new Exit("north", rooms.get("Outside")));
            rooms.get("Lab").setExit("east", new Exit("east", rooms.get("Office")));

// Office
            rooms.get("Office").setExit("west", new Exit("west", rooms.get("Lab")));
            Serialize.serialiseRoom(rooms);
        } catch (Exception e) {
            System.out.println("shits broken");
            e.printStackTrace(); // This shows the real error

        }


    }


    public static class Serialize {
        public static void serialiseRoom(Map<String, Room> room) {


            // Serialize the object to a file
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("map.ser"))) {
                out.writeObject(room);
                System.out.println(" Serialised to map.ser");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

