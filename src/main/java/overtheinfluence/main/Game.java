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

    /**
     * constructor for Game
     *
     * @param launcher the launcher that starts the game
     */
    public Game(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * is the level complete
     *
     * @return whether or not the game has been started
     */
    public boolean levelComplete(int levelNum) {
        return true;
    }

    //main method for testing purposes
    public static void main(String[] args) {
        JFrame window = new JFrame("Over the Influence");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        Exploration lvl1 = new Exploration();
        window.add(lvl1);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        lvl1.startThread();
    }
}
