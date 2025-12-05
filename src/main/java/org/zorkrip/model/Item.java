package org.zorkrip.model;

import java.io.Serializable;

public class Item implements Serializable {

    private boolean pushable;     // stays here, but logic moves to PushableItem
    private String description;
    private String name;
    private String location;
    private int id;
    private boolean isVisible;
    private boolean isCollectable;

    public Item(String name, String description, boolean isVisible, boolean isCollectable) {
        this.name = name;
        this.description = description;
        this.isCollectable = isCollectable;
        this.isVisible = isVisible;
    }

    public String getDescription() {
        return description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isVisible() {
        return isVisible;
    }

    public String use(Player player) {
        return "";
    }


    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }
}
