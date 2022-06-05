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
 * <p>This class represents Kitchen Table objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>KitchenTable class - Kevin Zhan</li>
 * </ul></p>
 *
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class KitchenTable extends Entity {
    /**
     * the constructor for KitchenTable objects
     *
     * @param assetSetter the asset setter used to set the kitchen table in the world
     */
    public KitchenTable(AssetSetter assetSetter) {
        super(assetSetter.lvl);
        int drawWidth = 64;
        int drawHeight = 64;
        name = "KitchenTable";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1KitchenTable.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3KitchenTable.png"));
            }
        } catch (IOException e) {
        }
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}

