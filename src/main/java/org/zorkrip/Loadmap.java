package org.zorkrip;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;


public class Loadmap {

    public static void main(String[] args) {
        try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/org/zorkrip/map.json")) {
            Gson gson = new Gson();

            Type type = new TypeToken<Map<String,Room>>(){}.getType();

            Map<String,Room> r =  gson.fromJson(reader, type);
            System.out.println(r.get("Outside"));
        } catch (Exception e) {
            System.out.println("shits broken");
            e.printStackTrace(); // This shows the real error

        }


    }

    public static Map<String,Room> loadmap() {
        try (Reader reader = new FileReader(System.getProperty("user.dir") + "/src/main/java/org/zorkrip/map.json")) {
            Gson gson = new Gson();

            Type type = new TypeToken<Map<String,Room>>(){}.getType();

            return gson.fromJson(reader, type);

        } catch (Exception e) {
            System.out.println("shits broken");
            e.printStackTrace(); // This shows the real error
            return null;
        }


    }



}

