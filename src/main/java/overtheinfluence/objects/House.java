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
 * <p>This class represents House objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>House class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class House extends Entity {
    /**
     * a trigger block within the door
     */
    public TriggerBlock triggerDoor;

    /**
     * Constructor for House objects
     *
     * @param assetSetter the asset setter that sets the house into the game
     * @param targetX     the x coordinate of the house's teleportation block's target position
     * @param targetY     the y coordinate of the house's teleportation block's target position
     */
    public House(AssetSetter assetSetter, int targetX, int targetY, boolean teleport) {
        super(assetSetter.lvl);
        int drawWidth = 160;
        int drawHeight = 215;
        name = "House";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/buildings/lvl1House.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/buildings/lvl3House.png"));
            }
        } catch (IOException e) {
        }
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
        if (teleport) {
            triggerDoor = new TriggerBlock(assetSetter, 40, 45, area.x + 85, area.y + 150) {
                @Override
                public void trigger() {
                    String message = "Press E to enter";
                    assetSetter.lvl.ui.showMessage(message);
                    if (assetSetter.lvl.player.keyIn.interact) {
                        assetSetter.lvl.player.worldX = targetX;
                        assetSetter.lvl.player.worldY = targetY;
                    }
                }
            };
        } else {
            triggerDoor = new TriggerBlock(assetSetter, 40, 45, area.x + 85, area.y + 150) {
                @Override
                public void trigger() {
                    String message = "This is not your house you cannot enter";
                    assetSetter.lvl.ui.showMessage(message);
                }
            };
        }
    }

    public void setPosition(int x, int y) {
        worldX = x;
        worldY = y;
        triggerDoor.worldX = x + 85;
        triggerDoor.worldY = y + 150;
    }
}
