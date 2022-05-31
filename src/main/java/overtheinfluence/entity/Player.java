package entity;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This is a subclass of Entity that controls the movement and animation of the player.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Coordinate tracking - Alexander Peng</li>
 * <li>Image implementation - Kevin Zhan</li>
 * <li>Movement animatino - Kevin Zhan</li>
 * <li>Directional movement from keyboard input - Kevin Zhan & Alexander Peng</li>
 * <li>Character display - Kevin Zhan</li>
 * <li>Collision detection - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Player extends Entity {
    /**
     * the level the player is in
     */
    Level lvl;
    /**
     * the input monitor for the player's keys
     */
    public KeyInput keyIn;

    /**
     * the player's x and y coordinates on the screen
     */
    public final int screenX, screenY;

    /**
     * the Player constructor
     *
     * @param lvl   the level the player is in
     * @param keyIn the input monitor for the player's keys
     */
    public Player(Level lvl, KeyInput keyIn) {
        this.lvl = lvl;
        this.keyIn = keyIn;

        screenX = lvl.screenWidth / 2 - lvl.tileSize / 2;
        screenY = lvl.screenHeight / 2 - lvl.tileSize / 2;

        defaultValue();
        getPlayerImage();

        area = new Rectangle();
        area.x = 19;
        area.y = 30;
        areaDefaultX = area.x;
        areaDefaultY = area.y;
        area.width = 9;
        area.height = 13;
    }

    /**
     * sets the player's default values
     */
    public void defaultValue() {
        worldX = lvl.tileSize * lvl.maxWorldCols / 2;
        worldY = lvl.tileSize * lvl.maxWorldRows / 2;
        speed = 4;
        direction = "down";
    }

    /**
     * gets and stores the images for each direction of the player
     */
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_up_2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_up_3.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_left_2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_left_3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_right_2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_right_3.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_down_2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/player_down_3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updates the player's position using the key input
     */
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

            collidingB = false;
            collidingL = false;
            collidingR = false;
            collidingT = false;
            lvl.collisionDetect.tileCollide(this);

            //check object collision
            int objectIndex = lvl.collisionDetect.objectCollide(this, true);
            interactObject(objectIndex);

            if(collidingT) {
                worldY += speed;
            }
            if(collidingB) {
                worldY -= speed;
            }
            if(collidingL) {
                worldX += speed;
            }
            if (collidingR) {
                worldX -= speed;
            }


            spriteCnt++; //how long the sprite can be displayed for
            if (spriteCnt > 4) {
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
                } else if (spriteNum == 8 || spriteNum == 9) {
                    spriteNum = 1;
                }
                spriteCnt = 0; //reset the sprite timer
            }
        } else {
            spriteNum = 9;
        }
    }

    public void interactObject(int index) {
        if(index != -1) {
            String name = lvl.objects.get(index).name;
            switch (name) {
                case "Door":
                    if(keyIn.interact) {
                        lvl.objects.remove(index);
                        lvl.playSFX(2);
                    }
            }
        }
    }

    /**
     * draws the player
     *
     * @param g2D the graphics object which provides control over coordinate system and color
     */
    public void draw(Graphics2D g2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 5 || spriteNum == 6) {
                    image = up1;
                } else if (spriteNum == 3 || spriteNum == 4 || spriteNum == 7 || spriteNum == 8) {
                    image = up2;
                } else if (spriteNum == 9) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 5 || spriteNum == 6) {
                    image = down1;
                } else if (spriteNum == 3 || spriteNum == 4 || spriteNum == 7 || spriteNum == 8) {
                    image = down2;
                } else if (spriteNum == 9) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) {
                    image = left1;
                } else if (spriteNum == 5 || spriteNum == 6 || spriteNum == 7) {
                    image = left2;
                } else if (spriteNum == 4 || spriteNum == 8 || spriteNum == 9) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1 || spriteNum == 2 || spriteNum == 3) {
                    image = right1;
                } else if (spriteNum == 5 || spriteNum == 6 || spriteNum == 7) {
                    image = right2;
                } else if (spriteNum == 4 || spriteNum == 8 || spriteNum == 9) {
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
