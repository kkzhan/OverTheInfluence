package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.IOException;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents Bed objects in the world.</p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Bed extends Entity {
    /**
     * the constructor for the Bed class
     *
     * @param assetSetter the asset setter used to add the bed to the world
     */
    public Bed(AssetSetter assetSetter) {
        super(assetSetter.lvl);
        int drawWidth = 84;
        int drawHeight = 96;
        name = "Bed";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1Bed.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3Bed.png"));
            }
        } catch (IOException e) {
        }
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
