package org.zorkrip;

import com.google.gson.Gson;

import java.io.*;
import java.util.Map;


public class LoadCharacter {




    public static Character loadPlayer(String path) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + File.separator+"player.ser"))) {
            Character player = (Character) in.readObject();
            return player;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

