package projectiles;

import entity.Entity;
import main.Level;

public class Projectile extends Entity {
    Entity user;

    public Projectile(Level lvl) {
        super(lvl);
        maxLife = 8000;
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        if (user == lvl.player) {
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
