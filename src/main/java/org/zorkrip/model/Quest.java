package org.zorkrip.model;

import java.io.Serializable;

public class Quest implements Serializable {
    final String questItem;
    final Item reward;

    public Quest(String questItem, Item reward) {
        this.questItem = questItem;
        this.reward = reward;
    }


}
