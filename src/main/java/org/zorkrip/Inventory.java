package org.zorkrip;


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


    default String printItems() {
        StringBuilder sb = new StringBuilder();

        if (getInventory().isEmpty()) {
            System.out.println();
            return"";
        }

        for (int i = 0; i < getInventory().size(); i++) {
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


    default int getNumberItems() {
        try {
            return getInventory().toArray().length;
        } catch (Exception e) {
            return 0;
        }
    }

}