package projectiles;

import main.*;

import java.awt.*;

public class PillProjectile extends Projectile {
    /**
     * the level the projectile is in
     */
    Level lvl;

    /**
     * constructor for PillProjectile class
     * @param lvl the level
     */
    public PillProjectile(Level lvl) {
        super(lvl);
        this.lvl = lvl;
        int drawWidth = 45;
        int drawHeight = 21;
        name = "Needle";
        life = maxLife;
        speed = 10;
        left1 = setup("objects/projectiles/pill");
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = util.scaleImage(left1, drawWidth, drawHeight);
    }
}
