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
 * <p>This class represents Yoga Mat objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Desk class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class YogaMat extends Entity {

    public TriggerBlock trigger;

    public String color;

    /**
     * the constructor for YogaMat objects
     *
     * @param assetSetter the asset setter used to set YogaMat objects in the world
     * @param x           the x coordinate of the YogaMat object in the world
     * @param y           the y coordinate of the YogaMat object in the world
     * @param num         the number of the YogaMat object
     */
    public YogaMat(AssetSetter assetSetter, int x, int y, int num) {
        super(assetSetter.lvl);
        int drawWidth = 96;
        int drawHeight = 48;
        name = "Yoga Mat";
        worldX = x;
        worldY = y;
        try {
            if (num == 1) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/rehab/blueYogaMat.png"));
                color = "Blue";
            } else if (num == 2) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/rehab/greenYogaMat.png"));
                color = "Green";
            } else if (num == 3) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/rehab/pinkYogaMat.png"));
                color = "Pink";
            } else if (num == 4) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/rehab/purpleYogaMat.png"));
                color = "Purple";
            }
            down1 = scaleImage(down1, drawWidth, drawHeight);
        } catch (IOException e) {
        }
        collision = false;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);

        trigger = new TriggerBlock(assetSetter, lvl.tileSize, lvl.tileSize / 2, worldX + lvl.tileSize / 2, worldY + lvl.tileSize / 4, false) {
            @Override
            public void trigger() {
                if(color.equals(lvl.matColor)) {
                    lvl.matReached = true;
                    lvl.matCount++;
                }
            }
        };
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        trigger.setPosition(x + lvl.tileSize / 2, y + lvl.tileSize / 4);
    }
}
