package org.zorkrip;

import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;



public class openfile {

    public static void main(String[] args) {

        try (Reader reader =  new FileReader(System.getProperty("user.dir")+ "src/main/java/resources/map.json")) {

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
