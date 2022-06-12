package entity;

import main.*;
import objects.*;
import projectiles.*;

import javax.imageio.*;
import java.awt.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This is the Inner Demon class that acts as the enemy attacking the player in Level 2.
 * The Inner Demon is a representation of the player's own internal struggles while dealing
 * with drug addiction.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>InnerDemon class - Kevin Zhan</li>
 * <li>InnerDemon artwork - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class InnerDemon extends Entity {
    /**
     * the timer until the inner demon dies
     */
    int deathTimer = 90;

    /**
     * whether the inner demon is dying
     */
    public boolean dying = false;

    /**
     * constructor for the InnerDemon class
     *
     * @param lvl the level the inner demon is on
     */
    public InnerDemon(Level lvl) {
        super(lvl);
        int drawWidth = lvl.tileSize * 5;
        int drawHeight = lvl.tileSize * 9;
        worldX = lvl.worldWidth - (int) (lvl.tileSize * 5.5);
        worldY = (int) (lvl.tileSize * 1.5);
        name = "Inner Demon";
        try {
            if (lvl.levelNum == 2) {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/entities/innerDemon/innerDemon1.png")));
                down1 = scaleImage(down1, drawWidth, drawHeight);
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

    /**
     * updates the inner demon's current information such as status and projectiles
     */
    public void update() {
        if ((lvl.startTime - lvl.time) % 60 == 0 && lvl.player.worldX > lvl.tileSize * 8) {
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
                    down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/innerDemon/innerDemon2.png")));
                    down1 = scaleImage(down1, drawWidth, drawHeight);
                }
            } catch (Exception e) {
            }
            if (deathTimer <= 0) {
                lvl.entities.remove(this);
                lvl.complete = true;
            }
        }
    }

    /**
     * the inner demon sends out projectiles to hit the player
     */
    public void sendProjectiles() {
        Projectile p = new Projectile(lvl);
        Projectile p2 = new Projectile(lvl);
        int rand = (int) (Math.random() * 3);
        if (rand == 0) {
            p = new PillProjectile(lvl);
            p2 = new AlcoholProjectile(lvl);
        } else if (rand == 1) {
            p = new NeedleProjectile(lvl);
            p2 = new PillProjectile(lvl);
        } else if (rand == 2) {
            p = new AlcoholProjectile(lvl);
            p2 = new NeedleProjectile(lvl);
        }
        int randY = (int) (Math.random() * (lvl.maxWorldRows - 3.5) * lvl.tileSize) + lvl.tileSize;
        p.set(lvl.player.worldX + 12 * lvl.tileSize, randY, "left", true, this);
        p2.set(lvl.player.worldX + 12 * lvl.tileSize, lvl.player.worldY - 20, "left", true, this);
        lvl.projectiles.add(p);
        lvl.projectiles.add(p2);
    }
}
