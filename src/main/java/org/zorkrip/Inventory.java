package org.zorkrip;


import java.util.List;

interface Inventory{

    List<Item> getInventory();



    default  Item getItemAtIndex(int j){
        return getInventory().get(j);
    }

    default  void addItem(Item item){
        getInventory().add(item);
    }

    default  void removeItem(int index){
        getInventory().remove(index);
    }


    default void printItems(){
        if(getInventory().isEmpty()){
            System.out.println();
            return;
        }

        for(int i =0; i<getInventory().size();i++){
            System.out.print( getItemAtIndex(i).getName());
            if (i+1<getInventory().size()){
                System.out.println(",");
            }
        }
        System.out.println();
    }

    default int getNumberItems(){
        try {
            return getInventory().toArray().length;
        }
        catch (Exception e){
            return 0;
        }
    }

}