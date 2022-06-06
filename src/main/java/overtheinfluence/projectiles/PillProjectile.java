package projectiles;

import main.*;

import java.awt.*;

public class PillProjectile extends Projectile {
    Level lvl;

    public PillProjectile(Level lvl) {
        super(lvl);
        this.lvl = lvl;
        int drawWidth = 45;
        int drawHeight = 21;
        name = "Needle";
        life = maxLife;
        speed = 20;
        getImage();
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = util.scaleImage(left1, drawWidth, drawHeight);
    }

    public void getImage() {
        left1 = setup("objects/projectiles/pill");
    }
}
