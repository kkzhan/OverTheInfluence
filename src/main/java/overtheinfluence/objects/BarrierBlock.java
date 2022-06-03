package objects;

import java.awt.*;

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

public class BarrierBlock extends GameObject {
    /**
     * the constructor for the BarrierBlock class
     *
     * @param x      the x coordinate of the BarrierBlock
     * @param y      the y coordinate of the BarrierBlock
     * @param onTile whether the BarrierBlock is fully on a tile
     */
    public BarrierBlock(int x, int y, boolean onTile) {
        name = "Barrier";
        collision = true;
        drawWidth = 48;
        drawHeight = 48;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
        if (onTile) {
            this.setPosition(x * 48, y * 48);
        } else {
            this.setPosition(x, y);
        }
    }
}
