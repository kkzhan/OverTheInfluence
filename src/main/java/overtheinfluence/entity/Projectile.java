package entity;

import main.Level;

public class Projectile extends Entity {
    Entity user;

    public Projectile(Level lvl) {
        super(lvl);
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
            if (contactPlayer) {
                lvl.projectiles.remove(this);
                lvl.player.barrierDebuffTimer += 1000;
                lvl.gameState = lvl.BARRIER_QUESTION_STATE;
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
