package org.zorkrip.persistence;


import org.zorkrip.model.Player;
import org.zorkrip.model.Room;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class save {


    public static void saveGame(Map<String, Room> rooms, Player player) {

        Scanner input2 = new Scanner(System.in);
        System.out.println("Enter your save directory ");
        String path = input2.nextLine();
        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Failed to create directory: " + path);
                return;
            }
        }


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
        System.out.println("Saved");


    }
}
