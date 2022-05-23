package overtheinfluence;

/**
 * <p>This abstract class stores the general information and behaviour of a level.</p>
 */

public abstract class Level {
    /**
     * completion status of the level
     */
    boolean complete;

    /**
     * constructor for Level
     */
    public Level() {
        complete = false;
    }

    /**
     * this method is called to run the level and return upon completion
     */
    public abstract boolean run();

    /**
     * this method returns the completion status of the level
     */
    public boolean isComplete() {
    	return complete;
    }
}
