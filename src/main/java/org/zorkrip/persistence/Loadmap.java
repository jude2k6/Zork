package org.zorkrip.persistence;
import org.zorkrip.model.Room;

import java.io.*;
import java.util.Map;


public class Loadmap {


    public static Map<String, Room> loadmap() {
        try (InputStream is = Loadmap.class.getClassLoader().getResourceAsStream("map.ser")) {

            if (is == null) {
                throw new FileNotFoundException("map.ser not found in resources");
            }

            ObjectInputStream in = new ObjectInputStream(is);

            return (Map<String, Room>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }





    public static Map<String,Room> loadmap(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path +File.separator+"map.ser"))) {

            return (Map<String,Room>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    }

