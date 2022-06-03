package objects;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class arrows that will hover above important object or NPCs in the game.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>IndicateArrow class - Kevin Zhan</li>
 *     <li>Arrow animation - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class IndicateArrow extends GameObject {
    /**
     * the counter for the frames of the arrows display animation
     */
    public int frameCount;
    /**
     * the frame of the arrows display animation
     */
    public int frame;
    /**
     * the first image of the arrows display animation
     */
    BufferedImage arrow1;
    /**
     * the second image of the arrows display animation
     */
    BufferedImage arrow2;

    /**
     * Constructor for the IndicateArrow class
     *
     * @param x the x coordinate of the arrow
     * @param y the y coordinate of the arrow
     */
    public IndicateArrow(int x, int y) {
        name = "IndicateArrow";
        collision = false;
        drawWidth = 28;
        drawHeight = 40;
        area = new Rectangle(x, y, drawWidth, drawHeight);
        frameCount = 0;
        try {
            arrow1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/arrow1.png"));
            arrow2 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/arrow2.png"));
        } catch (Exception e) {
        }
    }

    /**
     * Draws the arrow
     *
     * @param g2D the graphics object
     * @param lvl the level the arrow is in
     */
    public void draw(Graphics2D g2D, Level lvl) {
        frameCount++;
        if (frameCount > 20 && frame == 0) {
            image = arrow1;
            frame = 1;
            frameCount = 0;
        } else if (frameCount > 10 && frame == 1) {
            image = arrow2;
            frame = 0;
            frameCount = 0;
        }

        int screenX = worldX - lvl.player.worldX + lvl.player.screenX;
        int screenY = worldY - lvl.player.worldY + lvl.player.screenY;

        //stop moving camera at edge
        if (lvl.player.screenX > lvl.player.worldX) {
            screenX = worldX;
        }
        if (lvl.player.screenY > lvl.player.worldY) {
            screenY = worldY;
        }

        int rightOffset = lvl.screenWidth - lvl.player.screenX;
        int bottomOffset = lvl.screenHeight - lvl.player.screenY;
        if (rightOffset > lvl.worldWidth - lvl.player.worldX) {
            screenX = lvl.screenWidth - lvl.worldWidth + worldX;
        }
        if (bottomOffset > lvl.worldHeight - lvl.player.worldY) {
            screenY = lvl.screenHeight - lvl.worldHeight + worldY;
        }

        //only draws when around player
        if (worldX + lvl.tileSize > lvl.player.worldX - lvl.player.screenX - 4 * lvl.tileSize &&
                worldX - lvl.tileSize < lvl.player.worldX + lvl.player.screenX + 4 * lvl.tileSize &&
                worldY + lvl.tileSize > lvl.player.worldY - lvl.player.screenY - 4 * lvl.tileSize &&
                worldY - lvl.tileSize < lvl.player.worldY + lvl.player.screenY + 4 * lvl.tileSize) {
            g2D.drawImage(image, screenX, screenY, drawWidth, drawHeight, null);
        } else if (lvl.player.screenX > lvl.player.worldX ||
                lvl.player.screenY > lvl.player.worldY ||
                rightOffset > lvl.worldWidth - lvl.player.worldX ||
                bottomOffset > lvl.worldHeight - lvl.player.worldY) {
            g2D.drawImage(image, screenX, screenY, drawWidth, drawHeight, null);
        }
    }
}
