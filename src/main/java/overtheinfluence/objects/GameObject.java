package objects;

import main.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This is the superclass for all objects in the game.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>GameObject class - Kevin Zhan</li>
 *     <li>Drawing of objects - Kevin Zhan</li>
 *     <li>Image reflection - Kevin Zhan</li>
 *     <li>Object artwork design - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class GameObject {
    /**
     * the image of the object in the game
     */
    public BufferedImage image;
    /**
     * the name of the object
     */
    public String name;
    /**
     * whether the object can be collided with and blocks the player and other objects
     */
    public boolean collision;
    /**
     * the x and y coordinates of the object in the world
     */
    public int worldX, worldY;
    /**
     * the rectangular area that represents the hitbox of the object
     */
    public Rectangle area = new Rectangle(0, 0, 48, 48);
    /**
     * the default x and y coordinates of the object in the world
     */
    public int areaDefaultX = 0, areaDefaultY = 0;
    /**
     * determines the dimensions of the displayed image of the object
     */
    public int drawWidth = 48, drawHeight = 48;

    /**
     * updates the position of the object in the world
     *
     * @param x the new x position of the object
     * @param y the new y position of the object
     */
    public void setPosition(int x, int y) {
        worldX = x;
        worldY = y;
    }

    /**
     * draws the object
     *
     * @param g2D the graphics object used to draw the object
     * @param lvl the level the object is being drawn for
     */
    public void draw(Graphics2D g2D, Level lvl) {
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

    /**
     * reflects the image of the object
     *
     * @param image the image of the object
     * @return the reflected image
     */
    public BufferedImage reflectImage(BufferedImage image) {
        AffineTransform trans = AffineTransform.getScaleInstance(-1, 1);
        trans.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }
}
