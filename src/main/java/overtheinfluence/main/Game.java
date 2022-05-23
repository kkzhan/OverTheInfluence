package overtheinfluence.main;

import javax.swing.*;

/**
 * <p>This class operates one game of Over the Influence.</p>
 */

public class Game {
    private boolean started;

    private final Launcher launcher;

    public Game(Launcher launcher) {
        this.launcher = launcher;
    }

    public boolean levelComplete(int levelNum) {
        return true;
    }

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
