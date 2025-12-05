package org.zorkrip.persistence;

import org.zorkrip.model.Npc;
import org.zorkrip.model.Room;

public class WorkerNpc extends Npc {
    private boolean distracted;

    public WorkerNpc(String name, Room startingRoom) {
        super(name, startingRoom, null);
    }


    public void setDistracted(boolean distracted) {
        this.distracted = distracted;
    }
}
