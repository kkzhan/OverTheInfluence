package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

/**
 * <p>This class represents Dumpster objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Dumpster class - Kevin Zhan</li>
 * <li>Dumpster artwork - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Dumpster extends Entity {
    /**
     * the constructor for Dumpster objects
     *
     * @param assetSetter the asset setter used to set Dumpster objects in the world
     */
    public Dumpster(AssetSetter assetSetter) {
        super(assetSetter.lvl);
        int drawWidth = 144;
        int drawHeight = 96;
        name = "Garbage Bag";
        try {
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/dumpster.png")));
            down1 = scaleImage(down1, drawWidth, drawHeight);
        } catch (IOException ignored) {
        }
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
