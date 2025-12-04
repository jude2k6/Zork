package org.zorkrip.model;

import java.io.Serializable;

public class Item implements Serializable {
    private boolean pushable;
    public boolean push;
    private String description;
    private String name;
    private String location;
    private int id;
    private boolean isVisible;
    private boolean isCollectable;

    public Item(String name, String description, boolean isVisible,boolean isCollectable) {
        this.name = name;
        this.description = description;
        this.isCollectable = isCollectable;
        this.isVisible = isVisible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }


    public String use(Player player) {

        return null;
    }

    public boolean isCollectable() {
        return isCollectable;
    }
    public void setCollectable(boolean b) {
         isCollectable = b;
    }




    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }

}
