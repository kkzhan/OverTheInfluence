package projectiles;

import main.*;

import java.awt.*;

public class AlcoholProjectile extends Projectile {
    Level lvl;

    public AlcoholProjectile(Level lvl) {
        super(lvl);
        this.lvl = lvl;
        int drawWidth = 26;
        int drawHeight = 68;
        name = "Alcohol";
        life = maxLife;
        speed = 15;
        getImage();
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        left1 = util.scaleImage(left1, drawWidth, drawHeight);
    }

    public void getImage() {
        left1 = setup("objects/projectiles/alcoholBottle");
    }
}
