package org.zorkrip.model;

public class Candle extends Item {


    public Candle(String name, String description, boolean isVisable) {
        super(name, description, isVisable);
    }


    @Override
    public void use(Player player) {
        Thread thread = new Thread(new useCandle(player));
        thread.start();
    }


}

class useCandle implements Runnable {
    final Player player;

    public useCandle(Player player) {
        this.player = player;

    }

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
