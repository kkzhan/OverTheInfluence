package entity;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;

public class InnerDemon extends Entity {

    int deathTimer = 30;

    public boolean dying = false;
    public InnerDemon(Level lvl) {
        super(lvl);
        int drawWidth = lvl.tileSize * 5;
        int drawHeight = lvl.tileSize * 9;
        int x = 0; //behind finish line
        int y = 0; //below the wall
        name = "Inner Demon";
        try {
            if(lvl.levelNum == 2) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/"));
            }
        } catch (Exception e) {
        }
        collision = true;
        area = new Rectangle(x, y, drawWidth, drawHeight);
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
    }

    public void update() {
        if(dying) {
            lvl.projectiles.clear();
            deathTimer--;
            //if(deathTimer <= certain amount) {
            //down1 = dead eyes + cloud forming
            //} else if(deathTimer <= certain amount) {
            //down1 = engulfed in cloud
            //} else if(deathTimer <= certain amount) {
            //down1 = poof
            //}
            if(deathTimer <= 0) {
                lvl.entities.remove(this);
                lvl.completed = true;
            }
        }
    }
}
