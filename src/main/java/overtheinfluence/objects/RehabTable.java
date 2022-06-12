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
 * <p>This class represents Rehab Table objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>KitchenTable class - Kevin Zhan</li>
 * </ul></p>
 *
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class RehabTable extends Entity {
    /**
     * the constructor for RehabTable objects
     *
     * @param assetSetter the asset setter used to set the kitchen table in the world
     */
    public RehabTable(AssetSetter assetSetter) {
        super(assetSetter.lvl);
        int drawWidth = 640;
        int drawHeight = 64;
        name = "Rehab Table";
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/rehab/diningTable.png"));
        } catch (IOException e) {
        }
        down1 = scaleImage(down1, drawWidth, drawHeight);
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}

