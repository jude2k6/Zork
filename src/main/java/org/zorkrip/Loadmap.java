package org.zorkrip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;


public class Loadmap {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
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

