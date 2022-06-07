package projectiles;

import entity.*;
import main.*;

public class Projectile extends Entity {
    /**
     * the user who is sending the projectiles
     */
    Entity user;

    /**
     * constructor for Projectile class
     *
     * @param lvl the level the projectile is being used in
     */
    public Projectile(Level lvl) {
        super(lvl);
        maxLife = 8000;
    }

    /**
     * sets the initial position of the projectile
     *
     * @param worldX the x coordinate
     * @param worldY the y coordinate
     * @param direction the direction the projectile is moving in
     * @param alive whether the projectile is initilly alive
     * @param user the user of the projectile
     */
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    /**
     * updates information regarding the projectile and its coordinates
     */
    public void update() {
        if (user != lvl.player) {
            boolean contactPlayer = lvl.collisionDetect.checkPlayer(this);
            if(lvl.collisionDetect.entityCollide(this, lvl.assetSetter.barriers, false) != -1) {
                alive = false;
            }
            if (contactPlayer) {
                lvl.projectiles.clear();
                lvl.gameState = lvl.BARRIER_QUESTION_STATE;
                if(lvl.player.barrierDebuffTimer == 0) {
                    lvl.assetSetter.barrierDebuff();
                }
                lvl.player.invincible = true;
                lvl.player.invincibleTimer = lvl.FPS * 5;
                lvl.repaint();
            }
        }

        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }
        life--;
        if (life <= 0) {
            alive = false;
        }
    }
}
