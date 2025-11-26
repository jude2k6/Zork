/*
package org.zorkrip.engine;

import java.util.Scanner;

public class Game implements ZorkInterface {

    public static void main(String[] args) {
        ZorkEngine game;
        Scanner input = new Scanner(System.in);
        System.out.println("Load");
        String choice = input.nextLine();

        if (choice.equals("load")) {
            System.out.println("Enter your Save folder path");
            String path = input.nextLine();
            game = new ZorkEngine(path);
        } else {

            game = new ZorkEngine();
            System.out.println(game.printWelcome());
        }

        while (game.isRunning()) {
            String s = input.nextLine();
            System.out.println(game.play(s));

        }
    }


}
*/
