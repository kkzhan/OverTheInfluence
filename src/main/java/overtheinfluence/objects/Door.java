package objects;

import main.*;

import javax.imageio.*;
import java.io.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class creates doors that players can open and walk through.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>Door class - Kevin Zhan</li>
 * </ul></p>
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Door extends GameObject {

    /**
     * a teleportation block that will transport the player when in contact with it
     */
    public TeleportationBlock teleport;

    /**
     * the constructor for Door objects
     * @param assetSetter the asset setter used to set the door in the world
     * @param x the x coordinate of the door
     * @param y the y coordinate of the door
     * @param teleport whether or not the door has a functioning teleportation block
     * @param targetX the x coordinate of the teleportation block's target destination
     * @param targetY the y coordinate of the teleportation block's target destination
     */
    public Door(AssetSetter assetSetter, int x, int y, boolean teleport, int targetX, int targetY) {
        setPosition(x, y);
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/door.png"));
        } catch (IOException e) {
        }
        collision = true;
        this.teleport = new TeleportationBlock(48, 18, x, y + 30, targetX, targetY, false){
            @Override
            public void teleport() {
                assetSetter.lvl.player.worldX = targetX;
                assetSetter.lvl.player.worldY = targetY;
            }
        };
    }
}
