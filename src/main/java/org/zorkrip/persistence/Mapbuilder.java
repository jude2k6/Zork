package org.zorkrip.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.zorkrip.model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Map;


public class Mapbuilder {

    public static void main(String[] args) {

        try (
                InputStream is = Mapbuilder.class.getResourceAsStream("/map.json")) {
            if (is == null) throw new FileNotFoundException("map.json not found in resources!");

            Reader reader = new InputStreamReader(is);

            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Room>>() {
            }.getType();
            Map<String, Room> rooms = gson.fromJson(reader, type);

            /// npcs
            //husband
            Npc husband = new Npc("Deshevilded-Husband", rooms.get("Menswear-Sanctuary"), new Quest("Sunglasses", new Item("Blue-medium-hoody", "A soft blue hoody labeled 'Medium-ish'.\n Smells faintly of deodorant samples and existential dread.", true, true)));
            String husbandIntro = """
                    The man looks up at you with exhausted eyes.
                    "Mate… I can’t even look at the fluorescent lights right now."
                    "If you find any thing to help in this cursed place,bring it me."
                    "Then I’ll help you with whatever you're after.\"""";
            String husbandTrade = """
                    He grabs the sunglasses like they’re holy relics.
                    "Oh thank god… I can finally open my eyes again."
                    He puts them on, sighs deeply, then hands you the hoody.
                    "Here. Take it. And may the retail gods guide you.\"""";

            String[] husbandAfter = {
                    "He adjusts his sunglasses. \"I'm staying here until my wife finds me—or the store collapses. Whichever comes first.\"",
                    "He nods at you. \"These sunglasses are the only thing keeping me alive in this lighting.\"",
                    "He sits back against the wall. \"If you see my wife, tell her I made it to Homewear. That should buy me ten minutes.\"",
                    "He waves lazily. \"Go on, hero. Escape while you still can.\"",
                    "\"Don't worry about me,\" he says, lying clearly worried about himself.",
                    "\"If I don't make it out, tell my kids I fought bravely against the slipper pile.\""
            };

            String[] dialogue = new String[]{husbandIntro};
            husband.addDialogue(DialogueCondition.DEFAULT, dialogue);
            dialogue = new String[]{husbandTrade};
            husband.addDialogue(DialogueCondition.RETURNINGITEM, dialogue);
            husband.addDialogue(DialogueCondition.QUESTFINISHED, husbandAfter);

            Npc cashier = new Cashier("Mary-Walsh", rooms.get("Cashiers"));
            WorkerNpc worker = new WorkerNpc("Sales-assistant", rooms.get("Homewear-valley"));


            Chest<Item> devChest = new Chest<>();


            devChest.add(new Item("€3-Socks",
                    "Thick, aggressively ordinary black socks, tagged €3.",
                    true, true));
            devChest.add(new Item("Baby-bib", "A small baby bib with a worn-out giraffe design.\n"
                    , true, true));
            devChest.add(new Candle("Forest-Regret-candle", "A candle labeled 'Forest Regret'. It smells like pine, moss, and every bad decision you've made on a Sunday evening.\n", true));
            devChest.add(new Item("Blue-medium-hoody", "A soft blue hoody labeled 'Medium-ish'.\n Smells faintly of deodorant samples and existential dread.", true, true));
            devChest.add(new Item("Fluffy-slippers", "\"Soft pair of slippers with slightly worn soles.\"\n", true, true));
            rooms.get("Changing-Rooms").setDevChest(devChest);


            // Changingrooms
            rooms.get("Changing-Rooms").setExit("east", new Exit("west", rooms.get("Lingerie-Labyrinth"), true));
            rooms.get("Changing-Rooms").setExit("south", new Exit("south", rooms.get("Menswear-Sanctuary"), true));


            // menswear
            rooms.get("Menswear-Sanctuary").setExit("north", new Exit("north", rooms.get("Changing-Rooms"), true));
            rooms.get("Menswear-Sanctuary").setExit("east", new Exit("east", rooms.get("Womenswear"), true));
            rooms.get("Menswear-Sanctuary").addNpc(husband);


            //Lingerie-Labyrinth
            rooms.get("Lingerie-Labyrinth").setExit("north", new Exit("north", rooms.get("Storeroom"), false, true, "keycard"));
            rooms.get("Lingerie-Labyrinth").setExit("east", new Exit("east", rooms.get("€3-Sock-Bin"), false));
            rooms.get("Lingerie-Labyrinth").setExit("south", new Exit("south", rooms.get("Womenswear"), false));
            rooms.get("Lingerie-Labyrinth").setExit("west", new Exit("west", rooms.get("Changing-Rooms"), false));
            rooms.get("Lingerie-Labyrinth").addNpc(new DerbhlaDoyle(rooms.get("Lingerie-Labyrinth")));


            //Storeroom
            rooms.get("Storeroom").setExit("south", new Exit("south", rooms.get("Lingerie-Labyrinth"), true));
            rooms.get("Storeroom").addItem(new Item("Fluffy-slippers", "\"Soft pair of slippers with slightly worn soles.\"\n", true, true));


            // Womanswear
            rooms.get("Womenswear").setExit("north", new Exit("north", rooms.get("Lingerie-Labyrinth"), true));
            rooms.get("Womenswear").setExit("east", new Exit("east", rooms.get("Baby-Section-Maze"), true));
            rooms.get("Womenswear").setExit("south", new Exit("south", rooms.get("Candle-Grotto"), true));
            rooms.get("Womenswear").setExit("west", new Exit("west", rooms.get("Menswear-Sanctuary"), true));
            rooms.get("Womenswear").addItem(new Hanger("Hanger", "Bent hanger that looks like it could be used to grab somethingz\n"));

            // candle grotto
            rooms.get("Candle-Grotto").setExit("north", new Exit("north", rooms.get("Womenswear"), true));
            rooms.get("Candle-Grotto").setExit("south", new Exit("south", rooms.get("Homewear-valley"), true));
            rooms.get("Candle-Grotto").addItem(new Candle("Forest-Regret-candle", "A candle labeled 'Forest Regret'. It smells like pine, moss, and every bad decision you've made on a Sunday evening.\n", true));
            rooms.get("Candle-Grotto").addItem(new Candle(
                    "Laundry-Day-candle",
                    "A candle that smells like freshly folded laundry and the lie you tell yourself about 'keeping on top of things'.\n",
                    true));
            rooms.get("Candle-Grotto").addItem(new Candle(
                    "Midnight-Tesco-Run-candle",
                    "A candle with the unsettling scent of fluorescent aisles, reduced stickers, and questionable life choices.\n",
                    true));


            // Homewear
            rooms.get("Homewear-valley").setExit("north", new Exit("north", rooms.get("Candle-Grotto"), true));
            rooms.get("Homewear-valley").setExit("east", new Exit("east", rooms.get("Cashiers"), true));
            rooms.get("Homewear-valley").setExit("south", new Exit("south", rooms.get("Shoe-Swamplands"), true));
            rooms.get("Homewear-valley").addItem(new Mannequin("Mannequin", ""));
            rooms.get("Homewear-valley").addNpc(worker);

            //shoe
            rooms.get("Shoe-Swamplands").setExit("north", new Exit("north", rooms.get("Homewear-valley"), true));
            rooms.get("Shoe-Swamplands").setExit("south", new Exit("south", rooms.get("Exits"), true));
            rooms.get("Shoe-Swamplands").addItem(new Item("Sunglasses", "A cheap pair of plastic sunglasses. Slightly crooked, but good enough to survive Pennys lighting.\n", false, true));

            //exits
            rooms.get("Exits").setExit("north", new Exit("north", rooms.get("Shoe-Swamplands"), true));
            rooms.get("Exits").setExit("east", new Exit("east", rooms.get("Outside"), true));
            rooms.get("Exits").setExit("west", new Exit("west", rooms.get("Menswear-Sanctuary"), true));

            //sockpit
            rooms.get("€3-Sock-Bin").setExit("south", new Exit("south", rooms.get("Baby-Section-Maze"), true));
            rooms.get("€3-Sock-Bin").setExit("west", new Exit("west", rooms.get("Lingerie-Labyrinth"), true));

            //baby
            rooms.get("Baby-Section-Maze").setExit("north", new Exit("north", rooms.get("€3-Sock-Bin"), true));
            rooms.get("Baby-Section-Maze").setExit("south", new Exit("south", rooms.get("Cashiers"), true));
            rooms.get("Baby-Section-Maze").setExit("west", new Exit("west", rooms.get("Womenswear"), true));
            rooms.get("Baby-Section-Maze").addItem(new PushableItem("Pram", "", new Item("Baby-bib", "A small baby bib with a worn-out giraffe design.\n"
                    , true, true)));

            //till
            rooms.get("Cashiers").setExit("north", new Exit("north", rooms.get("Baby-Section-Maze"), true));
            rooms.get("Cashiers").setExit("west", new Exit("west", rooms.get("Homewear-valley"), true));
            rooms.get("Cashiers").addNpc(cashier);


            //outside
            rooms.get("Outside").setExit("west", new Exit("west", rooms.get("Exits"), true));


            Serialize.serialiseRoom(rooms);
        } catch (Exception e) {
            System.out.println("shits broken");
            e.printStackTrace(); // This shows the real error

        }


    }


    public static class Serialize {
        public static void serialiseRoom(Map<String, Room> room) {


            // Serialize the object to a file
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("map.ser"))) {
                out.writeObject(room);
                System.out.println(" Serialised to map.ser");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

