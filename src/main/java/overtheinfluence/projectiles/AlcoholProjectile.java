package projectiles;

import main.*;

import java.awt.*;

/**
 * <p>This class is for drug projectiles that are in the form of bottles of alcohol.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>AlcoholProjectile class - Alexander Peng</li>
 * <li>AlcoholProjectile artwork - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class AlcoholProjectile extends Projectile {

    /**
     * constructor for the AlcoholProjectile class
     * @param lvl the level
     */
    public AlcoholProjectile(Level lvl) {
        super(lvl);
        int drawWidth = 26;
        int drawHeight = 68;
        name = "Alcohol";
        life = maxLife;
        speed = 7;
        left1 = setup("objects/projectiles/alcoholBottle");
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = scaleImage(left1, drawWidth, drawHeight);
    }
}
