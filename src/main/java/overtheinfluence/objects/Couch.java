package objects;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.IOException;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents Couch objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>Couch class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Couch extends GameObject {
    /**
     * the constructor for Couch objects
     *
     * @param assetSetter the asset setter used to set the couch in the world
     */
    public Couch(AssetSetter assetSetter) {
        name = "Couch";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1Couch.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3Couch.png"));
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 32;
        drawHeight = 96;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
