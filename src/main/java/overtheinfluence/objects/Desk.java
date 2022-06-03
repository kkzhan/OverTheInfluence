package objects;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents Desk objects in the world.</p>
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

public class Desk extends GameObject {
    /**
     * the constructor for Desk objects
     *
     * @param assetSetter the asset setter used to set Desk objects in the world
     */
    public Desk(AssetSetter assetSetter) {
        name = "Desk";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1Desk.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3Desk.png"));
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 32;
        drawHeight = 64;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
