package projectiles;

import main.*;

import java.awt.*;

public class NeedleProjectile extends Projectile {
    /**
     * the level the projectile is in
     */
    Level lvl;

    /**
     * constructor for the NeedleProjectile class
     * @param lvl the level
     */
    public NeedleProjectile(Level lvl) {
        super(lvl);
        this.lvl = lvl;
        int drawWidth = 80;
        int drawHeight = 15;
        name = "Needle";
        life = maxLife;
        speed = 6;
        left1 = setup("objects/projectiles/needle");
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = util.scaleImage(left1, drawWidth, drawHeight);
    }
}
