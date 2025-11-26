package org.zorkrip.persistence;


import org.zorkrip.model.Player;
import org.zorkrip.model.Room;

import java.io.*;
import java.util.Map;

public class Save {


    public static void saveGame(Map<String, Room> rooms, Player player, String path) {




        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path+File.separator +"map.ser"))) {
            out.writeObject(rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path+File.separator +"player.ser"))) {
            out.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
