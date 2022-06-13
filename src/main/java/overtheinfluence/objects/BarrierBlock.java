package objects;

import entity.*;
import main.AssetSetter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>Barrier blocks prevent players from moving past them and act as invisible walls.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>BarrierBlock class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class BarrierBlock extends Entity {
    /**
     * the constructor for the BarrierBlock class
     *
     * @param assetSetter the asset setter
     * @param x      the x coordinate of the BarrierBlock
     * @param y      the y coordinate of the BarrierBlock
     * @param onTile whether the BarrierBlock is fully on a tile
     */
    public BarrierBlock(AssetSetter assetSetter, int x, int y, boolean onTile, boolean visible) {
        super(assetSetter.lvl);
        name = "Barrier";
        collision = true;
        int drawWidth = 48;
        int drawHeight = 48;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
        if (onTile) {
            worldX = x * drawWidth;
            worldY = y * drawHeight;
        } else {
            worldX = x;
            worldY = y;
        }
        if(visible) {
            try {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/barrier.png")));
                down1 = scaleImage(down1, drawWidth, drawHeight);
            } catch (Exception ignored) {
            }
        }
    }
}
