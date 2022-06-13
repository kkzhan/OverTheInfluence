package objects;

import entity.*;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class embodies all consumable objects that can be picked up.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Consumable class - Kevin Zhan</li>
 * <li>Water and Food artwork - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Consumable extends Entity {
    /**
     * constructor for Consumable objects
     *
     * @param lvl the level the consumable is in
     * @param x   the x coordinate of the consumable
     * @param y   the y coordinate of the consumable
     * @param num the number of the consumable (whether it is food or water)
     */
    public Consumable(Level lvl, int x, int y, int num) {
        super(lvl);
        if (num == 0) {
            name = "Food";
            try {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/entities/consumables/food.png")));
            } catch (IOException ignored) {
            }
        } else if (num == 1) {
            name = "Water";
            try {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/entities/consumables/water.png")));
            } catch (IOException ignored) {
            }
        }
        int drawWidth = 36;
        int drawHeight = 36;
        down1 = scaleImage(down1, drawWidth, drawHeight);
        collision = false;
        worldX = x * lvl.tileSize + 6;
        worldY = y * lvl.tileSize + 6;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
    }
}
