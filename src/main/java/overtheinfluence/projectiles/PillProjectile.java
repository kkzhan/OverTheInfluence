package projectiles;

import main.*;

import java.awt.*;

/**
 * <p>This class is for drug projectiles that are in the form of pills.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>PillProjectile class - AlexanderPeng</li>
 * <li>PillProjectile artwork - AlexanderPeng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class PillProjectile extends Projectile {

    /**
     * constructor for PillProjectile class
     * @param lvl the level
     */
    public PillProjectile(Level lvl) {
        super(lvl);
        int drawWidth = 45;
        int drawHeight = 21;
        name = "Needle";
        life = maxLife;
        speed = 10;
        left1 = setup("objects/projectiles/pill");
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = scaleImage(left1, drawWidth, drawHeight);
    }
}
