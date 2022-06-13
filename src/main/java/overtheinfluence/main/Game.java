package main;

import javax.swing.*;
import java.awt.event.*;

/**
 * <p>This class will operate one full game of OverTheInfluence.</p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Game {
    /**
     * the launcher that starts the game
     */
    public final Launcher launcher;

    /**
     * array of levels to be played
     */
    final Level[] levels;

    /**
     * arroy of windows for the levels to be played on
     */
    private final JFrame[] window;

    /**
     * the level number of the current level
     */
    public int currLevel;

    /**
     * constructor for Game
     *
     * @param launcher the launcher that starts the game
     */
    public Game(Launcher launcher) {
        this.launcher = launcher;
        window = new JFrame[3];
        for (int i = 0; i < window.length; i++) {
            window[i] = new JFrame("Over the Influence");
        }
        levels = new Level[3];
        levels[0] = new Level(1, this);
        levels[1] = new Level(2, this);
        levels[2] = new Level(3, this);
    }

    /**
     * is the level complete
     *
     * @return whether the level has been completed
     */
    public boolean levelComplete(int levelNum) {
        return levels[levelNum - 1].isComplete();
    }

    /**
     * plays a specific level
     *
     * @param level the number of the level to be played
     */
    public void playLevel(int level) {
        currLevel = level;
        window[currLevel - 1].setResizable(false);
        window[currLevel - 1].add(levels[currLevel - 1]);
        window[currLevel - 1].setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //check if there is already a window listener
        if (window[currLevel - 1].getWindowListeners().length == 0) {
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
        if(level == 1) {
            levels[currLevel - 1].playMusic(0);
        } else {
            levels[currLevel - 1].playMusic(1);
        }
        window[currLevel - 1].pack();
        window[currLevel - 1].setLocationRelativeTo(null);
        window[currLevel - 1].setVisible(true);
        levels[currLevel - 1].startThread();
        if(!levels[currLevel - 1].started) {
            levels[currLevel - 1].setupLevel();
        }
    }

    /**
     * plays the level after the current one
     */
    public void nextLevel() {
        levels[currLevel - 1].gameThread = null;
        levels[currLevel - 1].stopMusic();
        window[currLevel - 1].dispose();
        currLevel++;
        playLevel(currLevel);
    }

    /**
     * ends the level
     *
     * @param retry whether to retry the level
     */
    public void endLevel(boolean retry) {
        closeLevel();

        if (retry) {
            levels[currLevel - 1] = new Level(currLevel, this);
            window[currLevel - 1] = new JFrame("Over the Influence");
            playLevel(currLevel);
        } else {
            launcher.window.remove(launcher.mainPanel);
            launcher.window.setVisible(true);
            launcher.mainMenu();
        }
    }

    /**
     * closes the current level
     */
    public void closeLevel() {
        levels[currLevel - 1].gameThread = null;
        levels[currLevel - 1].stopMusic();
        window[currLevel - 1].dispose();
    }
}
