package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class arrows that will hover above important object or NPCs in the game.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>IndicateArrow class - Kevin Zhan</li>
 * <li>Arrow animation - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class IndicateArrow extends Entity {

    /**
     * Constructor for the IndicateArrow class
     *
     * @param x the x coordinate of the arrow
     * @param y the y coordinate of the arrow
     */
    public IndicateArrow(AssetSetter assetSetter, int x, int y) {
        super(assetSetter.lvl);
        int drawWidth = 28;
        int drawHeight = 40;
        name = "IndicateArrow";
        collision = false;
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/arrow1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/arrow2.png"));
        } catch (Exception e) {
        }
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
        down2 = util.scaleImage(down2, drawWidth, drawHeight);
    }

    @Override
    public void draw(Graphics2D g2D) {
        spriteCnt++;
        if (spriteCnt > 30 && spriteNum == 1) {
            spriteNum = 2;
            spriteCnt = 0; //reset the sprite timer
        } else if (spriteCnt > 30 && spriteNum == 2) {
            spriteNum = 1;
            spriteCnt = 0; //reset the sprite timer
        }

        int screenX = worldX - lvl.player.worldX + lvl.player.screenX;
        int screenY = worldY - lvl.player.worldY + lvl.player.screenY;
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        //stop moving camera at edge
        if (lvl.player.screenX > lvl.player.worldX) {
            screenX = worldX;
        }
        if (lvl.player.screenY > lvl.player.worldY) {
            screenY = worldY;
        }

        int rightOffset = lvl.screenWidth - lvl.player.screenX;
        int bottomOffset = lvl.screenHeight - lvl.player.screenY;
        if (rightOffset > lvl.worldWidth - lvl.player.worldX) {
            screenX = lvl.screenWidth - lvl.worldWidth + worldX;
        }
        if (bottomOffset > lvl.worldHeight - lvl.player.worldY) {
            screenY = lvl.screenHeight - lvl.worldHeight + worldY;
        }

        //only draws when around player
        if (worldX + lvl.tileSize > lvl.player.worldX - lvl.player.screenX - 4 * lvl.tileSize &&
                worldX - lvl.tileSize < lvl.player.worldX + lvl.player.screenX + 4 * lvl.tileSize &&
                worldY + lvl.tileSize > lvl.player.worldY - lvl.player.screenY - 4 * lvl.tileSize &&
                worldY - lvl.tileSize < lvl.player.worldY + lvl.player.screenY + 4 * lvl.tileSize) {
            g2D.drawImage(image, screenX, screenY, null);
        } else if (lvl.player.screenX > lvl.player.worldX ||
                lvl.player.screenY > lvl.player.worldY ||
                rightOffset > lvl.worldWidth - lvl.player.worldX ||
                bottomOffset > lvl.worldHeight - lvl.player.worldY) {
            g2D.drawImage(image, screenX, screenY, null);
        }
    }
}
