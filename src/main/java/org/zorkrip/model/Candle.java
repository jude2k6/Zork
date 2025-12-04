package org.zorkrip.model;

public class Candle extends Item {


    public Candle(String name, String description, boolean isVisable) {
        super(name, description, isVisable,true);
    }


    @Override
    public String use(Player player) {
        Thread thread = new Thread(new useCandle(player));
        thread.start();
        return null;
    }


}

record useCandle(Player player) implements Runnable {

    @Override
    public void run() {
        player.setEnhancedSight(true);
        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        player.setEnhancedSight(false);
    }
}
