package overtheinfluence;

/**
 * <p>This class operates one game of Over the Influence.</p>
 */

public class Game {
    private Level[] lvlList;

    private boolean started;

    private final Launcher launcher;

    public Game(Launcher launcher) {
        this.launcher = launcher;
        lvlList = new Level[3];
        lvlList[0] = new Exploration();
        lvlList[1] = new InnerDemons();
        lvlList[2] = new Recovery();
    }

    public boolean levelComplete(int levelNum) {
        return lvlList[levelNum - 1].isComplete();
    }
}
