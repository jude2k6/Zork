package org.zorkrip.model;

import org.zorkrip.model.Player;
import org.zorkrip.persistence.WorkerNpc;

public class Mannequin extends PushableItem {

    public Mannequin(String name, String description) {
        super(name, description, null);
    }

    @Override
    public String use(Player player) {
        WorkerNpc worker = (WorkerNpc) player.getCurrentRoom().getNpc("Worker");

        if (worker != null) {
            worker.setDistracted(true);
        }

        // Also reveal hidden item
        player.getCurrentRoom().addItem(new Item("Keycard", "Unlocks staff doors", true, true));

        return "You push the mannequin over. The Worker yelps and drops her keycard!\n";
    }
}
