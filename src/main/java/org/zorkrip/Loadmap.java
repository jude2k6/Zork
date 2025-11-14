package org.zorkrip;
import java.io.*;
import java.util.Map;


public class Loadmap {


    public static Map<String,Room> loadmap( ) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("map.ser"))) {
            Map<String,Room> map = (Map<String,Room>) in.readObject();

            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static Map<String,Room> loadmap(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path +File.separator+"map.ser"))) {
            Map<String,Room> map = (Map<String,Room>) in.readObject();

            return map;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    }

