package overtheinfluence.tiles;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * A single tile on the map.
 */

public class Tile {
    public BufferedImage image;
    boolean collision;

    public Tile(String path, boolean collision) {
        this.collision = collision;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile(String path) {
        this(path, false);
    }
}
