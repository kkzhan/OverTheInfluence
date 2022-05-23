package overtheinfluence.tiles;

import overtheinfluence.main.*;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    Level lvl;
    Tile[] tile; //types of tiles
    int[][] tileMap;

    public TileManager(Level lvl, String mapName) {
        this.lvl = lvl;
        tile = new Tile[10];
        tileMap = new int[lvl.maxWorldCols][lvl.maxWorldRows];
        tileImg();
        processMap(mapName);
    }

    public void tileImg() {
        tile[0] = new Tile("/tiles/grass.png");
        tile[1] = new Tile("/tiles/wall.png", true);
        tile[2] = new Tile("/tiles/water.png");
        tile[3] = new Tile("/tiles/earth.png");
        tile[4] = new Tile("/tiles/tree.png", true);
        tile[5] = new Tile("/tiles/sand.png");
    }

    public void processMap(String mapName) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/map/" + mapName + ".txt"))));
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
