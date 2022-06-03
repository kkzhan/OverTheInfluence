package objects;

import java.awt.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>Trigger blocks perform certain behaviour when the player interacts with or collides with it.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>TriggerBlock class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public abstract class TriggerBlock extends GameObject {
    /**
     * whether the block requires the player to press a button to interact with it
     */
    public boolean requireInteract;
    /**
     * the message to display when the player interacts with the block
     */
    public String message;

    /**
     * the constructor for the TriggerBlock class
     *
     * @param width the width of the block
     * @param height the height of the block
     * @param x the x-coordinate of the block
     * @param y the y-coordinate of the block
     * @param requireInteract whether the block requires the player to press a button to interact with it
     */
    public TriggerBlock(int width, int height, int x, int y, boolean requireInteract) {
        name = "TriggerBlock";
        area = new Rectangle(x, y, width, height);
        this.requireInteract = requireInteract;
        collision = false;
    }

    /**
     * the method that is called when the player interacts with the block
     */
    public abstract void trigger();
}
