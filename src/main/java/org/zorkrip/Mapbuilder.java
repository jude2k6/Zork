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

            Type type = new TypeToken<Map<String,Room>>(){}.getType();
            Map<String,Room> rooms = gson.fromJson(reader, type);

            rooms.get("Outside").setExit("east", rooms.get("Theatre"));
            rooms.get("Outside").setExit("south", rooms.get("Lab"));
            rooms.get("Outside").setExit("west", rooms.get("Pub"));

            rooms.get("Theatre").setExit("west", rooms.get("Outside"));

            rooms.get("Pub").setExit("east", rooms.get("Outside"));

            rooms.get("Lab").setExit("north", rooms.get("Outside"));
            rooms.get("Lab").setExit("east", rooms.get("Office"));

            rooms.get("Office").setExit("west", rooms.get("Lab"));
            Serialize.serialiseRoom(rooms);
        } catch (Exception e) {
            System.out.println("shits broken");
            e.printStackTrace(); // This shows the real error

        }


    }

    public static Map<String,Room> loadmap() {
        try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/org/zorkrip/map.json")) {
            Gson gson = new Gson();

            Type type = new TypeToken<Map<String,Room>>(){}.getType();
            Map<String,Room> rooms = gson.fromJson(reader, type);

            rooms.get("Outside").setExit("east", rooms.get("Theatre"));
            rooms.get("Outside").setExit("south", rooms.get("Lab"));
            rooms.get("Outside").setExit("west", rooms.get("Pub"));

            rooms.get("Theatre").setExit("west", rooms.get("Outside"));

            rooms.get("Pub").setExit("east", rooms.get("Outside"));

            rooms.get("Lab").setExit("north", rooms.get("Outside"));
            rooms.get("Lab").setExit("east", rooms.get("Office"));

            rooms.get("Office").setExit("west", rooms.get("Lab"));



            return rooms;

        } catch (Exception e) {
            System.out.println("shits null");
            e.printStackTrace(); // This shows the real error
            return null;
        }


    }

    public class Serialize {
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




    public static Map<String,Room> loadmap(String path) {
        try (Reader reader = new FileReader(path+"/map.json")) {
            Gson gson = new Gson();

            Type type = new TypeToken<Map<String,Room>>(){}.getType();
            Map<String,Room> rooms = gson.fromJson(reader, type);

            rooms.get("Outside").setExit("east", rooms.get("Theatre"));
            rooms.get("Outside").setExit("south", rooms.get("Lab"));
            rooms.get("Outside").setExit("west", rooms.get("Pub"));

            rooms.get("Theatre").setExit("west", rooms.get("Outside"));

            rooms.get("Pub").setExit("east", rooms.get("Outside"));

            rooms.get("Lab").setExit("north", rooms.get("Outside"));
            rooms.get("Lab").setExit("east", rooms.get("Office"));

            rooms.get("Office").setExit("west", rooms.get("Lab"));



            return rooms;

        } catch (Exception e) {
            System.out.println("shits null");
            e.printStackTrace(); // This shows the real error
            return null;
        }


    }






}

