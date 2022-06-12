package npc;

import entity.*;
import main.*;

import java.awt.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class contains information about the NPC that is the player's psychiatrist in rehabilitation.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Psychiatrist class - Kevin Zhan</li>
 * <li>Psychiatrist artwork - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Psychiatrist extends Entity {
    /**
     * constructor for the Pyschiatrist class
     *
     * @param lvl the level the pyschiatrist is in
     * @param x   the x coordinate of the pyschiatrist
     * @param y   the y coordinate of the pyschiatrist
     */
    public Psychiatrist(Level lvl, int x, int y) {
        super(lvl);
        direction = "down";
        name = "Psychiatrist";
        collision = true;
        down1 = setup("entities/NPC/psychiatrist");
        down1 = scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);
    }
}