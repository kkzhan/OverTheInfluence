package main;

import javax.swing.*;
import java.awt.event.*;

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

    private JFrame[] window;

    public int currLevel;

    /**
     * constructor for Game
     *
     * @param launcher the launcher that starts the game
     */
    public Game(Launcher launcher) {
        this.launcher = launcher;
        window = new JFrame[3];
        for(int i = 0; i < window.length; i++) {
             window[i] = new JFrame("Over the Influence");
        }
        levels = new Level[3];
        levels[0] = new Level(1, this);
        levels[1] = new Level(2, this);
        levels[2] = new Level(1, this); //fix to 3
    }

    /**
     * is the level complete
     *
     * @return whether or not the level has been completed
     */
    public boolean levelComplete(int levelNum) {
        return levels[levelNum - 1].isComplete();
    }

    public void playLevel(int level){
        currLevel = level;
        window[currLevel - 1].setResizable(false);
        window[currLevel - 1].add(levels[currLevel - 1]);
        window[currLevel - 1].setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //check if there is already a window listener
        if(window[currLevel - 1].getWindowListeners().length == 0) {
            window[currLevel - 1].addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    JFrame frame = (JFrame) e.getSource();
                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        closeLevel();
                        launcher.window.remove(launcher.mainPanel);
                        launcher.window.setVisible(true);
                        launcher.mainMenu();
                    }
                }
            });
        }
        window[currLevel - 1].pack();
        window[currLevel - 1].setLocationRelativeTo(null);
        window[currLevel - 1].setVisible(true);
        levels[currLevel - 1].setupLevel();
        levels[currLevel - 1].startThread();
    }

    public void nextLevel() {
        levels[currLevel - 1].gameThread = null;
        levels[currLevel - 1].stopMusic();
        window[currLevel - 1].dispose();
        currLevel++;
        playLevel(currLevel);
    }

    public void endLevel(boolean retry) {
        closeLevel();
        levels[currLevel - 1] = new Level(currLevel, this);
        window[currLevel - 1] = new JFrame("Over the Influence");

        if(retry) {
            playLevel(currLevel);
        } else {
            launcher.window.remove(launcher.mainPanel);
            launcher.window.setVisible(true);
            launcher.mainMenu();
        }
    }

    public void closeLevel() {
        levels[currLevel - 1].gameThread = null;
        levels[currLevel - 1].stopMusic();
        window[currLevel - 1].dispose();
    }
}
