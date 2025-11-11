package org.zorkrip;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

public class save {



    public static void saveGame(Map<String, Room> rooms){

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

        for(String key: rooms.keySet()){
            rooms.get(key).removeExits();
        }
        Gson gson = new Gson();
        String json = gson.toJson(rooms);
        try (FileWriter writer = new FileWriter(path+"/map.json")){


            writer.write(json);

            System.out.println(
                    "Data written to the file successfully.");
        }
        catch (Exception e){
            e.printStackTrace();        }
    }
}
