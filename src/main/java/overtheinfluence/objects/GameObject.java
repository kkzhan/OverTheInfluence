package objects;

import main.*;

import java.awt.*;
import java.awt.image.*;

public class GameObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle area = new Rectangle(0, 0, 48, 48);
    public int areaDefaultX = 0;
    public int areaDefaultY = 0;

    public void draw(Graphics2D g2D, Level lvl) {
        int screenX = worldX - lvl.player.worldX + lvl.player.screenX;
        int screenY = worldY - lvl.player.worldY + lvl.player.screenY;

        //stop moving camera at edge
        if(lvl.player.screenX > lvl.player.worldX) {
            screenX = worldX;
        }
        if(lvl.player.screenY > lvl.player.worldY) {
            screenY = worldY;
        }
        int rightOffset = lvl.screenWidth - lvl.player.screenX;
        int bottomOffset = lvl.screenHeight - lvl.player.screenY;
        if(rightOffset > lvl.worldWidth - lvl.player.worldX) {
            screenX = lvl.screenWidth - lvl.worldWidth + worldX;
        }
        if(bottomOffset > lvl.worldHeight - lvl.player.worldY) {
            screenY = lvl.screenHeight - lvl.worldHeight + worldY;
        }

        //only draws when around player
        if (worldX + lvl.tileSize > lvl.player.worldX - lvl.player.screenX &&
                worldX - lvl.tileSize < lvl.player.worldX + lvl.player.screenX &&
                worldY + lvl.tileSize > lvl.player.worldY - lvl.player.screenY &&
                worldY - lvl.tileSize < lvl.player.worldY + lvl.player.screenY) {
            g2D.drawImage(image, screenX, screenY, lvl.tileSize, lvl.tileSize, null);
        } else if(lvl.player.screenX > lvl.player.worldX ||
                lvl.player.screenY > lvl.player.worldY ||
                rightOffset > lvl.worldWidth - lvl.player.worldX ||
                bottomOffset > lvl.worldHeight - lvl.player.worldY) {
            g2D.drawImage(image, screenX, screenY, lvl.tileSize, lvl.tileSize, null);
        }
        }
    }
