package overtheinfluence.main;

import javax.swing.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class will operate one full game.</p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Game {
    /**
     * whether or not the game has been started
     */
    private boolean started;

    /**
     * the launcher that starts the game
     */
    private final Launcher launcher;

    private Level[] levels;

    /**
     * constructor for Game
     *
     * @param launcher the launcher that starts the game
     */
    public Game(Launcher launcher) {
        this.launcher = launcher;
        levels = new Level[3];
        levels[0] = new Exploration();
        levels[1] = new InnerDemons();
        levels[2] = new Recovery();
    }

    /**
     * is the level complete
     *
     * @return whether or not the level has been completed
     */
    public boolean levelComplete(int levelNum) {
        return levels[levelNum - 1].isComplete();
    }

    //main method for testing purposes
    public static void main(String[] args) {
        JFrame window = new JFrame("Over the Influence");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //needs a confirmation
        window.setResizable(false);

        Exploration lvl1 = new Exploration();
        window.add(lvl1);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        lvl1.startThread();
    }
}
