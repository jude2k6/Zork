package org.zorkrip.model;

public class PushableItem extends Item {

    private Item hiddenItem;

    public PushableItem(String name, String description, Item hiddenItem) {
        super(name, description, true, false);
        this.hiddenItem = hiddenItem;
    }

    @Override
    public String use(Player player) {
        // Reveal the hidden item to the room
        player.getCurrentRoom().addItem(hiddenItem);

        return "You push the " + getName() +
                ". It topples over and reveals a " +
                hiddenItem.getName() + ".\n";
    }
}
