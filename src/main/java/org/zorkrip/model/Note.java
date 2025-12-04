package org.zorkrip.model;

public class Note extends Item {


    String[] requiredItems = {
            "Blue-medium-hoody",
            "Fluffy-slippers",
            "€3-socks",
            "Baby-bib",
            "Forest-Regret-candle"
    };

    public Note() {
        super("Note", "Morning.\nYou fell asleep again in Pennys.\nPlease grab these before you come home:\n– Blue medium hoody\n– Fluffy slippers\n– €3 socks\n– Baby bib\n– Forest Regret candle\nDo NOT forget this time.",
                true,true);
    }

    public String[] getrequiredItems(){
        return requiredItems;
    }


}
