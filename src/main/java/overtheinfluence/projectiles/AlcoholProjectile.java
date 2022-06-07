package projectiles;

import main.*;

import java.awt.*;

public class AlcoholProjectile extends Projectile {
    /**
     * the level the projectile is used in
     */
    Level lvl;

    /**
     * constructor for the AlcoholProjectile class
     * @param lvl the level
     */
    public AlcoholProjectile(Level lvl) {
        super(lvl);
        this.lvl = lvl;
        int drawWidth = 26;
        int drawHeight = 68;
        name = "Alcohol";
        life = maxLife;
        speed = 5;
        left1 = setup("objects/projectiles/alcoholBottle");
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = util.scaleImage(left1, drawWidth, drawHeight);
    }
}
