package tiles;

import main.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class is responsible for managing tiles to create a functioning map, as well as processing map data
 * from a text file.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>Process tile images - Kevin Zhan</li>
 *     <li>Process map - Kevin Zhan</li>
 *     <li>Draw tiles on map - Kevin Zhan</li>
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
    Level lvl;
    /**
     * the different types of tiles
     */
    public Tile[] tile;
    /**
     * the map layout of the tiles in the level
     */
    public int[][] tileMap;

    /**
     * constructor for the tile manager
     *
     * @param lvl the level that is being played
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
        tile[0] = new Tile("/resources/tiles/divider.png", true);
        tile[1] = new Tile("/resources/tiles/streetHor.png");
        tile[2] = new Tile("/resources/tiles/streetVer.png");
        tile[3] = new Tile("/resources/tiles/streetInt.png");
        tile[4] = new Tile("/resources/tiles/sidewalk.png");
        tile[5] = new Tile("/resources/tiles/grass1.png");
        tile[6] = new Tile("/resources/tiles/grass2.png");
        tile[7] = new Tile("/resources/tiles/houseWall.png", true);
        tile[8] = new Tile("/resources/tiles/lvl1Floor.png");
        tile[9] = new Tile("/resources/tiles/lvl1Asphalt.png");
        tile[10] = new Tile("/resources/tiles/lvl1Pavement.png");




        tile[10] = new Tile("/resources/tiles/lvl3Floor.png");
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
                    String[] nums = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    tileMap[col][row] = num;
                    col++;
                }
                if (col == lvl.maxWorldCols) {
                    row++;
                    col = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

            //only draws tiles that are visible around the player
            if (worldX + lvl.tileSize > lvl.player.worldX - lvl.player.screenX &&
                    worldX - lvl.tileSize < lvl.player.worldX + lvl.player.screenX &&
                    worldY + lvl.tileSize > lvl.player.worldY - lvl.player.screenY &&
                    worldY - lvl.tileSize < lvl.player.worldY + lvl.player.screenY) {
                g2D.drawImage(tile[tileMap[worldCol][worldRow]].image, screenX, screenY, lvl.tileSize, lvl.tileSize, null);
            } else if(lvl.player.screenX > lvl.player.worldX ||
                    lvl.player.screenY > lvl.player.worldY ||
                    rightOffset > lvl.worldWidth - lvl.player.worldX ||
                    bottomOffset > lvl.worldHeight - lvl.player.worldY) {
                g2D.drawImage(tile[tileMap[worldCol][worldRow]].image, screenX, screenY, lvl.tileSize, lvl.tileSize, null);
            }
            worldCol++;
            if (worldCol == lvl.maxWorldCols) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
