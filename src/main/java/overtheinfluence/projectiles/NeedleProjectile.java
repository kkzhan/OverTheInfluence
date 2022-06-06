package projectiles;

import main.*;

import java.awt.*;

public class NeedleProjectile extends Projectile {
    Level lvl;

    public NeedleProjectile(Level lvl) {
        super(lvl);
        this.lvl = lvl;
        int drawWidth = 80;
        int drawHeight = 15;
        name = "Needle";
        life = maxLife;
        speed = 15;
        getImage();
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = util.scaleImage(left1, drawWidth, drawHeight);
    }

    public void getImage() {
        left1 = setup("objects/projectiles/needle");
    }
}
