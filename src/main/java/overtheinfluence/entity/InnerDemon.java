package entity;

import main.*;
import objects.BarrierBlock;
import projectiles.AlcoholProjectile;
import projectiles.NeedleProjectile;
import projectiles.PillProjectile;
import projectiles.Projectile;

import javax.imageio.*;
import java.awt.*;

public class InnerDemon extends Entity {

    int deathTimer = 90;

    public boolean dying = false;

    public InnerDemon(Level lvl) {
        super(lvl);
        int drawWidth = lvl.tileSize * 5;
        int drawHeight = lvl.tileSize * 9;
        worldX = lvl.worldWidth - (int) (lvl.tileSize * 5.5);
        worldY = (int) (lvl.tileSize * 1.5);
        name = "Inner Demon";
        try {
            if (lvl.levelNum == 2) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/entities/innerDemon/innerDemon1.png"));
                down1 = util.scaleImage(down1, drawWidth, drawHeight);
            }
        } catch (Exception e) {
        }
        collision = true;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        for (int i = 1; i < lvl.maxWorldRows - 1; i++) {
            BarrierBlock barrier = new BarrierBlock(lvl.assetSetter, worldX, lvl.tileSize * i, false, false);
            lvl.objects.add(barrier);
        }
    }

    public void update() {
        if ((lvl.startTime - lvl.time) % 15 == 0 && lvl.player.worldX > lvl.tileSize * 8) {
            sendProjectiles();
        }
        if (dying) {
            int drawWidth = lvl.tileSize * 5;
            int drawHeight = lvl.tileSize * 9;
            lvl.projectiles.clear();
            deathTimer--;
            try {
                if (deathTimer < 45) {
                    down1 = null;
                } else if (deathTimer < 90) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/innerDemon/innerDemon2.png"));
                    down1 = util.scaleImage(down1, drawWidth, drawHeight);
                }
            } catch (Exception e) {
            }
            if (deathTimer <= 0) {
                lvl.entities.remove(this);
                lvl.completed = true;
            }
        }
    }

    public void sendProjectiles() {
        Projectile p = new Projectile(lvl);
        int rand = (int) (Math.random() * 3);
        if (rand == 0) {
            p = new PillProjectile(lvl);
        } else if (rand == 1) {
            p = new NeedleProjectile(lvl);
        } else if (rand == 2) {
            p = new AlcoholProjectile(lvl);
        }
        int randY = (int) (Math.random() * (lvl.maxWorldRows - 3.5) * lvl.tileSize) + lvl.tileSize;
        p.set(lvl.player.worldX + 12 * lvl.tileSize, randY, "left", true, this);
        lvl.projectiles.add(p);
    }
}
