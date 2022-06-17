package tiles;

import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

/**
 * <p>This class is responsible for managing tiles to create a functioning map, as well as processing map data
 * from a text file.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Process tile images - Kevin Zhan</li>
 * <li>Process map - Kevin Zhan</li>
 * <li>Draw tiles on map - Kevin Zhan</li>
 * <li>Map design - Kevin Zhan and Alexander Peng</li>
 * <li>Map barrier layout - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class TileManager {
    /**
     * the level that is being played
     */
    final Level lvl;
    /**
     * the different types of tiles
     */
    public final Tile[] tile;
    /**
     * the map layout of the tiles in the level
     */
    public int[][] tileMap;

    /**
     * constructor for the tile manager
     *
     * @param lvl     the level that is being played
     * @param mapName the name of the map file
     */
    public TileManager(Level lvl, String mapName) {
        this.lvl = lvl;
        tile = new Tile[100];
        processMap(mapName);
        tileImg();
    }

    /**
     * processes the tile images
     */
    public void tileImg() {
        if (lvl.levelNum == 1) {
            setup(0, "divider", true);
            setup(1, "streetHor", false);
            setup(2, "streetVer", false);
            setup(3, "streetInt", false);
            setup(4, "sidewalk", false);
            setup(5, "lvl1Grass1", false);
            setup(6, "lvl1Grass2", false);
            setup(7, "pavement", false);
            setup(8, "lvl1Floor", false);
            setup(9, "asphalt", false);
            setup(10, "houseWall", true);
            setup(11, "lvl1Fence", true);
        } else if (lvl.levelNum == 2) {
            setup(0, "lvl2Floor", false);
            setup(1, "lvl2Walls", true);
        } else if (lvl.levelNum == 3) {
            setup(0, "divider", true);
            setup(1, "streetHor", false);
            setup(2, "streetVer", false);
            setup(3, "streetInt", false);
            setup(4, "sidewalk", false);
            setup(5, "lvl3Grass1", false);
            setup(6, "lvl3Grass2", false);
            setup(7, "pavement", false);
            setup(8, "lvl3Floor", false);
            setup(9, "asphalt", false);
            setup(10, "houseWall", true);
            setup(11, "lvl3Fence", true);
            setup(12, "whiteWall", true);
            setup(13, "rehabFloor", false);
            setup(14, "yogaRoomFloor", false);
        }
    }

    /**
     * helps setup the tiles
     *
     * @param index     the index of the tile
     * @param path      the path to the image
     * @param collision whether the tile is collidable
     */
    public void setup(int index, String path, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/tiles/" + path + ".png")));
            tile[index].image = scaleImage(tile[index].image, lvl.tileSize, lvl.tileSize);
            tile[index].collision = collision;
        } catch (IOException ignored) {
        }
    }

    /**
     * processes the map file into the map layout
     *
     * @param mapName the name of the map file
     */
    public void processMap(String mapName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/resources/map/" + mapName + ".txt"))));
            String[] arr = br.readLine().split(" ");
            lvl.maxWorldCols = Integer.parseInt(arr[0]);
            lvl.maxWorldRows = Integer.parseInt(arr[1]);
            tileMap = new int[lvl.maxWorldCols][lvl.maxWorldRows];
            int col = 0;
            int row = 0;
            while (col < lvl.maxWorldCols && row < lvl.maxWorldRows) {
                String line = br.readLine();
                while (col < lvl.maxWorldCols) {
                    String[] nums = line.split("");
                    int num = 0;
                    try {
                        num = Integer.parseInt(nums[col]);
                    } catch (Exception e) {
                        switch (nums[col]) {
                            case "a":
                                num = 10;
                                break;
                            case "b":
                                num = 11;
                                break;
                            case "c":
                                num = 12;
                                break;
                            case "d":
                                num = 13;
                                break;
                            case "e":
                                num = 14;
                                break;
                            case "f":
                                num = 15;
                                break;
                            case "g":
                                num = 16;
                                break;
                            case "h":
                                num = 17;
                                break;
                            case "i":
                                num = 18;
                                break;
                        }
                    }
                    tileMap[col][row] = num;
                    col++;
                }
                if (col == lvl.maxWorldCols) {
                    row++;
                    col = 0;
                }
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * draws the tiles on the map
     *
     * @param g2D the graphics object that provides control over drawing to the screen
     */
    public void draw(Graphics2D g2D) {
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < lvl.maxWorldCols && worldRow < lvl.maxWorldRows) {
            int worldX = worldCol * lvl.tileSize;
            int worldY = worldRow * lvl.tileSize;
            int screenX = worldX - lvl.player.worldX + lvl.player.screenX;
            int screenY = worldY - lvl.player.worldY + lvl.player.screenY;

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

            //only draws tiles that are visible around the player
            if (worldX + lvl.tileSize > lvl.player.worldX - lvl.player.screenX &&
                    worldX - lvl.tileSize < lvl.player.worldX + lvl.player.screenX &&
                    worldY + lvl.tileSize > lvl.player.worldY - lvl.player.screenY &&
                    worldY - lvl.tileSize < lvl.player.worldY + lvl.player.screenY) {
                g2D.drawImage(tile[tileMap[worldCol][worldRow]].image, screenX, screenY, null);
            } else if (lvl.player.screenX > lvl.player.worldX ||
                    lvl.player.screenY > lvl.player.worldY ||
                    rightOffset > lvl.worldWidth - lvl.player.worldX ||
                    bottomOffset > lvl.worldHeight - lvl.player.worldY) {
                g2D.drawImage(tile[tileMap[worldCol][worldRow]].image, screenX, screenY, null);
            }
            worldCol++;
            if (worldCol == lvl.maxWorldCols) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    /**
     * resizes the image to the specified size
     *
     * @param image  the image to be scaled
     * @param width  the width of the scaled image
     * @param height the height of the scaled image
     * @return the scaled image
     */
    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(image, 0, 0, width, height, null);
        g2D.dispose();
        return scaledImage;
    }
}
