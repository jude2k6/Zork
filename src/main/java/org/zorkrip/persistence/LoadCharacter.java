package org.zorkrip.persistence;


import org.zorkrip.model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class LoadCharacter {


    public static Player loadPlayer(String path) {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path + File.separator + "player.ser"))) {
            return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

