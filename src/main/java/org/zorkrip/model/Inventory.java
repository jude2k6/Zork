package org.zorkrip.model;


import java.util.List;

interface Inventory {

    List<Item> getInventory();


    default Item getItemAtIndex(int j) {
        return getInventory().get(j);
    }

    default void addItem(Item item) {
        getInventory().add(item);
    }

    default void removeItem(int index) {
        getInventory().remove(index);
    }


    default String printItems(Player player) {
        StringBuilder sb = new StringBuilder();

        if (getInventory().isEmpty()) {
            System.out.println();
            return "";
        }

        for (int i = 0; i < getInventory().size(); i++) {
            if (!getItemAtIndex(i).isVisible()) {
                if (!player.isEnhancedSight()) continue;
            }
            sb.append(getItemAtIndex(i).getName());
            if (i + 1 < getInventory().size()) {
                sb.append(",");
            }
        }
        sb.append("\n");
        return sb.toString();
    }


    default int getIndexOfItem(String item) {
        for (int i = 0; i < getInventory().size(); i++) {
            if (getItemAtIndex(i).getName().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1;
    }


    default String viewInventory() {
        StringBuilder sb = new StringBuilder();

        if (getInventory().isEmpty()) {
            System.out.println();
            return "";
        }

        for (int i = 0; i < getInventory().size(); i++) {
            sb.append(getItemAtIndex(i).getName());
            if (i + 1 < getInventory().size()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    default Item getItem(String item) {
        int index = getIndexOfItem(item);
        if (index == -1) {
            return null;
        }
        return getItemAtIndex(index);
    }

    default Item getVisableItem(String itemName, Player player) {
        Item item = getItem(itemName);
        if (item == null) {
            return null;
        }

        if (!(item.isVisible())) {
            if (player.isEnhancedSight()) {
                return item;
            }
            return null;
        }
        return item;

    }


}