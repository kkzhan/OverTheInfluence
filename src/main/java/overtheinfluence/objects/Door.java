package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class creates doors that players can open and walk through.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Door class - Kevin Zhan</li>
 * </ul></p>
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Door extends Entity {

    /**
     * a teleportation block that will transport the player when in contact with it
     */
    public TriggerBlock teleport;

    /**
     * the constructor for Door objects
     *
     * @param assetSetter the asset setter used to set the door in the world
     * @param x           the x coordinate of the door
     * @param y           the y coordinate of the door
     * @param teleport    whether or not the door has a functioning teleportation block
     * @param targetX     the x coordinate of the teleportation block's target destination
     * @param targetY     the y coordinate of the teleportation block's target destination
     */
    public Door(AssetSetter assetSetter, int x, int y, boolean teleport, int targetX, int targetY) {
        super(assetSetter.lvl);
        int drawWidth = 48;
        int drawHeight = 48;
        worldX = x;
        worldY = y;
        name = "Door";
        try {
            if(assetSetter.lvl.levelNum == 1) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/lvl1Door.png"));
            } else if(assetSetter.lvl.levelNum == 3) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/lvl3Door.png"));
            }
        } catch (IOException e) {
        }
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
        collision = true;
        if(teleport) {
            this.teleport = new TriggerBlock(assetSetter,48, 18, x, y + 30, false) {
                @Override
                public void trigger() {
                    assetSetter.lvl.player.worldX = targetX;
                    assetSetter.lvl.player.worldY = targetY;
                }
            };
        }
        area = new Rectangle(x, y, drawWidth, drawHeight);
    }
}
