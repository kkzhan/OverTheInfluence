package objects;

import java.awt.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>Teleportation blocks transport the player to new locations when interact with or move upon.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>TeleportationBlock class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public abstract class TeleportationBlock extends GameObject {
    /**
     * whether the block requires the player to press a button to interact with it
     */
    public boolean requireInteract;

    /**
     * the x and y coordinates of the target teleport destination
     */
    public int targetX, targetY;

    /**
     * the constructor for the TeleportationBlock class
     *
     * @param width           the width of the block
     * @param height          the height of the block
     * @param x               the x coordinate of the block
     * @param y               the y coordinate of the block
     * @param targetX         the x coordinate of the target teleport destination
     * @param targetY         the y coordinate of the target teleport destination
     * @param requireInteract whether the block requires the player to press a button to interact with it
     */
    public TeleportationBlock(int width, int height, int x, int y, int targetX, int targetY, boolean requireInteract) {
        name = "TeleportationBlock";
        area = new Rectangle(x, y, width, height);
        this.targetX = targetX;
        this.targetY = targetY;
        this.requireInteract = requireInteract;
        collision = false;
        this.setPosition(x, y);
    }

    /**
     * teleports the player to the target teleport destination
     */
    public abstract void teleport();
}
