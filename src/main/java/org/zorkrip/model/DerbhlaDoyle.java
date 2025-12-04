package org.zorkrip.model;

import java.util.Random;

public class DerbhlaDoyle extends Npc {

    private int talkCount = 0;
    private final String[] defaultDialogue = new String[]{
            "The exit’s open. Use it. Preferably at speed.\n",
            "Still here? What are you, lost in the lace again?\n",
            "If you touch anything else, I’m calling security. Or my manager. Whichever arrives faster.\n",
            "Men wander in here like deer on a motorway… absolutely clueless.\n",
            "If you’re looking for thanks, try the homeware section. They’re nicer there.\n",
            "No, I’m not helping with sizes. You barely survived the mannequins.\n",
            "Are you browsing? No. No, you’re not. Off you go.\n",
            "Honestly, you’re doing well. Most men faint by now.\n",
            "If you get stuck again, please do not come back here. Ever.\n"
    };


    public DerbhlaDoyle(Room startingRoom) {
        super("Derbhla-Doyle", startingRoom, null);
    }

    @Override
    public String talk(Player player) {
        talkCount++;
        switch (talkCount) {
            case 1:
                return "You’re in the wrong aisle, aren’t you? Hands to yourself, please.\n";
            case 2:
                return "Seriously? Again? Just… look around, maybe try not to break anything.\n";
            case 3:
                getCurrentRoom().getExit("west").setVisible(true);
                getCurrentRoom().getExit("east").setVisible(true);
                getCurrentRoom().getExit("south").setVisible(true);
                return "Fine. Since you clearly can’t manage, the exits are " + getCurrentRoom().getExitString() + " Don’t blame me for the mannequins.\n";
            default:

                int number = new Random().nextInt(8);
                return defaultDialogue[number];


        }
    }

}
