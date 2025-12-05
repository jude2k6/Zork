package org.zorkrip.model;

public class PushableItem extends Item {

    private final Item hiddenItem;

    public PushableItem(String name, String description, Item hiddenItem) {
        super(name, description, true, false);
        this.hiddenItem = hiddenItem;

        // Mark this item as
        setPushable(true);
    }

    @Override
    public String use(Player player) {

        // If this item reveals something
        if (hiddenItem != null) {
            player.getCurrentRoom().addItem(hiddenItem);
            return "You push the " + getName() +
                    ". It topples over and reveals a " +
                    hiddenItem.getName() + ".\n";
        }

        // Otherwise fallback behavior
        return "You push the " + getName() + ".\n";
    }
}
