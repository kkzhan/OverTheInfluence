package main;

import objects.NeedleProjectile;

import javax.swing.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This is a subclass of Level that will operate the second level: "Inner Demons".</p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class InnerDemons extends Level {
    /**
     * default constructor for InnerDemons
     */
    public InnerDemons(Game game) {
        super(2, game);
    }


    public void dumbShitLol() {
        Level lvl = this;
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    NeedleProjectile needle = new NeedleProjectile(lvl);
                    needle.set(player.worldX + 200, player.worldY, "left", true, player);
                    projectiles.add(needle);
                    break;
                }
            }
        });
        newThread.start();
    }
}
