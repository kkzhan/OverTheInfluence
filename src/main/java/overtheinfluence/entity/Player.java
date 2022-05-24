package overtheinfluence.entity;

import overtheinfluence.main.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Objects;

public class Player extends Entity {
    Level lvl;
    KeyInput keyIn;

    public final int screenX;
    public final int screenY;

    public Player(Level lvl, KeyInput keyIn) {
        this.lvl = lvl;
        this.keyIn = keyIn;

        screenX = lvl.screenWidth / 2 - lvl.tileSize / 2;
        screenY = lvl.screenHeight / 2 - lvl.tileSize / 2;

        defaultValue();
        getPlayerImage();
    }

    public void defaultValue() {
        worldX = lvl.tileSize * 23;
        worldY = lvl.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_3.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyIn.up || keyIn.down || keyIn.left || keyIn.right) {
            if (keyIn.up) {
                direction = "up";
                worldY -= speed;
            }
            if (keyIn.down) {
                direction = "down";
                worldY += speed;
            }
            if (keyIn.left) {
                direction = "left";
                worldX -= speed;
            }
            if (keyIn.right) {
                direction = "right";
                worldX += speed;
            }

            spriteCnt++; //how long the sprite can be displayed for
            if (spriteCnt > 8) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 5;
                } else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 7;
                } else if (spriteNum == 7) {
                    spriteNum = 8;
                } else if (spriteNum == 8) {
                    spriteNum = 1;
                }
                spriteCnt = 0; //reset the sprite timer
            }
        }
    }

    public void draw(Graphics2D g2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 5 || spriteNum == 6) {
                    image = up1;
                } else if (spriteNum == 3 || spriteNum == 4 || spriteNum == 7 || spriteNum == 8) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 5 || spriteNum == 6) {
                    image = down1;
                } else if(spriteNum == 3 || spriteNum == 4 || spriteNum == 7 || spriteNum == 8) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) {
                    image = left1;
                } else if(spriteNum == 5 || spriteNum == 6 || spriteNum == 7) {
                    image = left2;
                } else if(spriteNum == 4 || spriteNum == 8) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) {
                    image = right1;
                } else if (spriteNum == 5 || spriteNum == 6 || spriteNum == 7) {
                    image = right2;
                } else if (spriteNum == 4 || spriteNum == 8) {
                    image = right3;
                }
                break;
        }
        int x = screenX;
        int y = screenY;
        if (screenX > worldX) {
            x = worldX;
        }
        if (screenY > worldY) {
            y = worldY;
        }
        int rightOffset = lvl.screenWidth - screenX;
        if (rightOffset > lvl.worldWidth - worldX || rightOffset < 0 && x < 0) {
            x = lvl.screenWidth - (lvl.worldWidth - worldX);
        }
        int bottomOffset = lvl.screenHeight - screenY;
        if (bottomOffset > lvl.worldHeight - worldY || bottomOffset < 0) {
            y = lvl.screenHeight - (lvl.worldHeight - worldY);
        }

        g2D.drawImage(image, x, y, lvl.tileSize, lvl.tileSize, null);
    }
}
