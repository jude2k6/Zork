package org.zorkrip.model;

import java.util.HashMap;
import java.util.Random;


public class Npc extends Character {

    private final Quest quest;
    private final HashMap<DialogueCondition, String[]> dialogue;
    private DialogueCondition dialogueCondition;


    public Npc(String name, Room startingRoom, Quest quest) {
        super(name, startingRoom);
        this.quest = quest;
        dialogue = new HashMap<>();
        dialogueCondition = DialogueCondition.DEFAULT;

    }


    public void addDialogue(DialogueCondition access, String[] lines) {
        dialogue.put(access, lines);

    }

    public void reward(Player player) {
        if (quest.reward == null) {
            return;
        }
        player.addItem(quest.reward);
    }

    public String talk(Player player) {

        if (player.getItem(quest.questItem) != null) {
            dialogueCondition = DialogueCondition.RETURNINGITEM;
        }
        String[] dialogueString = dialogue.get(dialogueCondition);
        if (dialogueCondition == DialogueCondition.RETURNINGITEM) {
            dialogueCondition = DialogueCondition.QUESTFINISHED;
            player.removeItem(player.getIndexOfItem(quest.questItem));
            reward(player);

        }
        Random random = new Random();
        int max = dialogueString.length - 1;
        if (max == 0) {
            max += 1;
        }
        int randint = random.nextInt(0, max);
        return dialogueString[randint];


    }


}
