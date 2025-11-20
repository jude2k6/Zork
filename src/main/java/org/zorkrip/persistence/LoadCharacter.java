package org.zorkrip.persistence;


import org.zorkrip.model.Player;

import java.io.*;



public class LoadCharacter {




    public static Player loadPlayer(String path) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + File.separator+"player.ser"))) {
            return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

