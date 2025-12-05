package org.zorkrip.model;

public class Hanger extends Item {

    public Hanger(String name, String description) {
        super(name, description, true, true);
    }


    @Override
    public String use(Player player) {
        if (player.getCurrentRoom().getName().equals("€3-Sock-Bin")) {
            player.addItem(new Item("€3-socks", "Thick, aggressively ordinary black socks, unevenly folded, faintly dusty, tagged €3 — style may vary (and has).", true, true));
            return "You use the hanger to pull the socks out of the vortex";
        }
        return "";
    }
}
