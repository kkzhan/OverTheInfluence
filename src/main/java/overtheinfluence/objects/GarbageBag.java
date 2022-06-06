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
 * <p>This class represents Garbage Bag objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>GarbageBag class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class GarbageBag extends Entity {
    /**
     * the constructor for GarbageBag objects
     *
     * @param assetSetter the asset setter used to set GarbageBag objects in the world
     */
    public GarbageBag(AssetSetter assetSetter) {
        super(assetSetter.lvl);
        int drawWidth = 64;
        int drawHeight = 64;
        name = "Garbage Bag";
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/garbageBag.png"));
            down1 = util.scaleImage(down1, drawWidth, drawHeight);
        } catch (IOException e) {
        }
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
