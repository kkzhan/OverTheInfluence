package tiles;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.util.Objects;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents individual tiles in the world map of the game.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>create Tile - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Tile {
    /**
     * the image of the tile
     */
    public BufferedImage image;
    /**
     * whether entities will collide with this tile
     */
    public boolean collision;

    /**
     * constructor for Tile
     *
     * @param path path to image file
     * @param collision whether or not the tile has collision
     */
    public Tile(String path, boolean collision) {
        this.collision = collision;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * constructor for Tile that automatically sets collision to false
     */
    public Tile(String path) {
        this(path, false);
    }
}
