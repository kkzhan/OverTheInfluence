package objects;

import entity.*;
import main.AssetSetter;

import javax.imageio.ImageIO;
import java.awt.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>Trigger blocks perform certain behaviour when the player interacts with or collides with it.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>TriggerBlock class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public abstract class TriggerBlock extends Entity {

    /**
     * the constructor for the TriggerBlock class
     *
     * @param assetSetter the asset setter
     * @param width       the width of the block
     * @param height      the height of the block
     * @param x           the x-coordinate of the block
     * @param y           the y-coordinate of the block
     * @param visible     whether or not the block is visible
     */
    public TriggerBlock(AssetSetter assetSetter, int width, int height, int x, int y, boolean visible) {
        super(assetSetter.lvl);
        name = "TriggerBlock";
        area = new Rectangle(x, y, width, height);
        collision = false;
        worldX = x;
        worldY = y;
        if (visible) {
            try {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/finishLine.png"));
                down1 = util.scaleImage(down1, 48, 48);
            } catch (Exception e) {
            }
        }
    }

    /**
     * the method that is called when the player interacts with the block
     */
    public abstract void trigger();
}
