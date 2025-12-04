package org.zorkrip.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Cashier extends Npc{


    public Cashier(String name, Room startingRoom) {
        super(name, startingRoom, null);
    }


    @Override
    public String talk(Player player){

        boolean hasAll = new HashSet<>(Collections.singletonList(player.getInventory()))
                .containsAll(Arrays.asList(((Note)player.getItem("note")).getrequiredItems()));


        if (hasAll){
            player.setWin(true);

            return "Thanks for shopping im sure your wife will love all this.\n";
        }
        return "You need tio get everything on your wifes list.\n";
    }
}
