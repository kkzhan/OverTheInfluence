package overtheinfluence.entity;

import java.awt.image.*;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCnt = 0;
    public int spriteNum = 1;
}
