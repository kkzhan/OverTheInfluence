package projectiles;

import main.*;

import java.awt.*;

/**
 * <p>This class is for drug projectiles that are in the form of syringe needles.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>NeedleProjectile class - AlexanderPeng</li>
 * <li>NeedleProjectile artwork - AlexanderPeng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class NeedleProjectile extends Projectile {

    /**
     * constructor for the NeedleProjectile class
     * @param lvl the level
     */
    public NeedleProjectile(Level lvl) {
        super(lvl);
        int drawWidth = 80;
        int drawHeight = 15;
        name = "Needle";
        life = maxLife;
        speed = 8;
        left1 = setup("objects/projectiles/needle");
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = scaleImage(left1, drawWidth, drawHeight);
    }
}
