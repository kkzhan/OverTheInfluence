package overtheinfluence.entity;

import java.awt.image.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This is the superclass representing all entities.</p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Entity {
    /** the x and y coordinates of the entity in the world */
    public int worldX, worldY;
    /** the player's movement speed*/
    public int speed;

    /** images for the entity in each direction*/
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;

    /**the entity's current direction*/
    public String direction;

    /** sprite counter for the entity's animation*/
    public int spriteCnt = 0;
    /** the entity's current sprite number*/
    public int spriteNum = 1;
}
