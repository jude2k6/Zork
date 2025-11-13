package org.zorkrip;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;


public class LoadCharacter {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/org/zorkrip/player.json")) {
            Gson gson = new Gson();

            Character player = gson.fromJson(reader,Character.class);
            System.out.println(player.getName());
            
        } catch (Exception e) {
            System.out.println("shits broken");
            e.printStackTrace(); // This shows the real error

        }


    }

    public static Character loadPlayer() {
        try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/org/zorkrip/player.json")) {

            Gson gson = new Gson();
            Character player = gson.fromJson(reader,Character.class);
            System.out.println(player.getName());

            return player;

        } catch (Exception e) {
            System.out.println("shits null");
            e.printStackTrace(); // This shows the real error
            return null;
        }


    }
    public static Character loadPlayer(String path) {
        try (Reader reader = new FileReader(path+"/character.json")) {
            Gson gson = new Gson();
            Character player = gson.fromJson(reader,Character.class);
            System.out.println(player.getName());

            return player;


        } catch (Exception e) {
            System.out.println("shits null");
            e.printStackTrace(); // This shows the real error
            return null;
        }


    }






}

