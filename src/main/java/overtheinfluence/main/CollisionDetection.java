package main;

import entity.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * <p>This class is used for detection collision between entities, namely the player, and the tiles being moved over.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Tile Collision Detection - Kevin Zhan</li>
 * <li>Object Collision Detection - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class CollisionDetection {

    /**
     * the level using the collision detection
     */
    final Level lvl;

    /**
     * constructor for CollisionDetection
     *
     * @param level the level using collision detection
     */
    public CollisionDetection(Level level) {
        lvl = level;
    }

    /**
     * checks if an entity is colliding with a tile
     *
     * @param e the entity to check
     */
    public void tileCollide(Entity e) {
        int worldXLeft = e.worldX + e.area.x;
        int worldXRight = e.worldX + e.area.x + e.area.width;
        int worldYTop = e.worldY + e.area.y;
        int worldYBottom = e.worldY + e.area.y + e.area.height;

        int leftCol = worldXLeft / lvl.tileSize;
        int rightCol = worldXRight / lvl.tileSize;

        int tileUp1, tileUp2, tileDown1, tileDown2, tileLeft1, tileLeft2, tileRight1, tileRight2;

        int topRow = (worldYTop - e.speed) / lvl.tileSize;
        tileUp1 = lvl.tm.tileMap[leftCol][topRow];
        tileUp2 = lvl.tm.tileMap[rightCol][topRow];
        if (lvl.tm.tile[tileUp1].collision || lvl.tm.tile[tileUp2].collision) {
            e.collision = true;
        }
        topRow = worldYTop / lvl.tileSize;

        int bottomRow = (worldYBottom + e.speed) / lvl.tileSize;
        tileDown1 = lvl.tm.tileMap[leftCol][bottomRow];
        tileDown2 = lvl.tm.tileMap[rightCol][bottomRow];
        if (lvl.tm.tile[tileDown1].collision || lvl.tm.tile[tileDown2].collision) {
            e.collision = true;
        }
        bottomRow = worldYBottom / lvl.tileSize;

        leftCol = (worldXLeft - e.speed) / lvl.tileSize;
        tileLeft1 = lvl.tm.tileMap[leftCol][topRow];
        tileLeft2 = lvl.tm.tileMap[leftCol][bottomRow];
        if (lvl.tm.tile[tileLeft1].collision || lvl.tm.tile[tileLeft2].collision) {
            e.collision = true;
        }

        rightCol = (worldXRight + e.speed) / lvl.tileSize;
        tileRight1 = lvl.tm.tileMap[rightCol][topRow];
        tileRight2 = lvl.tm.tileMap[rightCol][bottomRow];
        if (lvl.tm.tile[tileRight1].collision || lvl.tm.tile[tileRight2].collision) {
            e.collision = true;
        }
    }

    /**
     * checks if an entity is colliding with any object
     *
     * @param e        the entity to check
     * @param isPlayer whether the entity is the player
     * @return the index of the entity being collided with
     */
    public int objectCollide(Entity e, boolean isPlayer) {
        int index = -1;
        for (int i = 0; i < lvl.objects.size(); i++) {
            e.area.x = e.worldX + e.area.x;
            e.area.y = e.worldY + e.area.y;
            lvl.objects.get(i).area.x = lvl.objects.get(i).worldX + lvl.objects.get(i).area.x;
            lvl.objects.get(i).area.y = lvl.objects.get(i).worldY + lvl.objects.get(i).area.y;

            if (isPlayer) {
                Rectangle nonsolid = new Rectangle(e.area.x - 7, e.area.y - 30, e.area.width + 14, e.area.height + 48);
                if (nonsolid.intersects(lvl.objects.get(i).area)) {
                    index = i;
                }
                if (((Player) e).keyIn.left) {
                    e.area.x -= e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        if (lvl.objects.get(i).collision) e.collision = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.right) {
                    e.area.x += e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        if (lvl.objects.get(i).collision) e.collision = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.up) {
                    e.area.y -= e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        if (lvl.objects.get(i).collision) e.collision = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.down) {
                    e.area.y += e.speed;
                    if (e.area.intersects(lvl.objects.get(i).area)) {
                        if (lvl.objects.get(i).collision) e.collision = true;
                        index = i;
                    }
                }
            } else {
                switch (e.direction) {
                    case "up" -> {
                        e.area.y -= e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collision = true;
                        }
                    }
                    case "down" -> {
                        e.area.y += e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collision = true;
                        }
                    }
                    case "left" -> {
                        e.area.x -= e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collision = true;
                        }
                    }
                    case "right" -> {
                        e.area.x += e.speed;
                        if (e.area.intersects(lvl.objects.get(i).area)) {
                            e.collision = true;
                        }
                    }
                }
            }
            e.area.x = e.areaDefaultX;
            e.area.y = e.areaDefaultY;
            lvl.objects.get(i).area.x = lvl.objects.get(i).areaDefaultX;
            lvl.objects.get(i).area.y = lvl.objects.get(i).areaDefaultY;
        }
        return index;
    }

    /**
     * checks whether an entity is colliding with other entities
     *
     * @param e        the entity to check
     * @param entities all the entities that the entity might be colliding with
     * @param isPlayer whether the entity is the player
     * @return the index of the entity being collided with
     */
    public int entityCollide(Entity e, ArrayList<Entity> entities, boolean isPlayer) {
        int index = -1;
        for (int i = 0; i < entities.size(); i++) {
            e.area.x = e.worldX + e.area.x;
            e.area.y = e.worldY + e.area.y;
            entities.get(i).area.x = entities.get(i).worldX + entities.get(i).area.x;
            entities.get(i).area.y = entities.get(i).worldY + entities.get(i).area.y;

            if (isPlayer) {
                Rectangle nonsolid = new Rectangle(e.area.x - 7, e.area.y - 30, e.area.width + 14, e.area.height + 48);
                if (nonsolid.intersects(entities.get(i).area)) {
                    index = i;
                }
                if (((Player) e).keyIn.left) {
                    e.area.x -= e.speed;
                    if (e.area.intersects(entities.get(i).area)) {
                        if (entities.get(i).collision) e.collision = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.right) {
                    e.area.x += e.speed;
                    if (e.area.intersects(entities.get(i).area)) {
                        if (entities.get(i).collision) e.collision = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.up) {
                    e.area.y -= e.speed;
                    if (e.area.intersects(entities.get(i).area)) {
                        if (entities.get(i).collision) e.collision = true;
                        index = i;
                    }
                    e.area.x = e.areaDefaultX + e.worldX;
                    e.area.y = e.areaDefaultY + e.worldY;
                }
                if (((Player) e).keyIn.down) {
                    e.area.y += e.speed;
                    if (e.area.intersects(entities.get(i).area)) {
                        if (entities.get(i).collision) e.collision = true;
                        index = i;
                    }
                }
            } else {
                switch (e.direction) {
                    case "up" -> {
                        e.area.y -= e.speed;
                        if (e.area.intersects(entities.get(i).area)) {
                            e.collision = true;
                        }
                    }
                    case "down" -> {
                        e.area.y += e.speed;
                        if (e.area.intersects(entities.get(i).area)) {
                            e.collision = true;
                        }
                    }
                    case "left" -> {
                        e.area.x -= e.speed;
                        if (e.area.intersects(entities.get(i).area)) {
                            e.collision = true;
                        }
                    }
                    case "right" -> {
                        e.area.x += e.speed;
                        if (e.area.intersects(entities.get(i).area)) {
                            e.collision = true;
                        }
                    }
                }
            }
            e.area.x = e.areaDefaultX;
            e.area.y = e.areaDefaultY;
            entities.get(i).area.x = entities.get(i).areaDefaultX;
            entities.get(i).area.y = entities.get(i).areaDefaultY;
        }
        return index;
    }

    /**
     * checks if another entity is colliding with the player
     *
     * @param e the entity to check
     * @return whether there is collision with the player
     */
    public boolean checkPlayer(Entity e) {
        e.area.x = e.worldX + e.area.x;
        e.area.y = e.worldY + e.area.y;
        Rectangle hitbox = new Rectangle(lvl.player.worldX, lvl.player.worldY, lvl.tileSize, lvl.tileSize);
        if (lvl.player.invincible) {
            return false;
        }
        switch (e.direction) {
            case "up" -> {
                e.worldY -= e.speed;
                if (e.area.intersects(hitbox)) {
                    return true;
                }
            }
            case "down" -> {
                e.worldY += e.speed;
                if (e.area.intersects(hitbox)) {
                    return true;
                }
            }
            case "left" -> {
                e.worldX -= e.speed;
                if (e.area.intersects(hitbox)) {
                    return true;
                }
            }
            case "right" -> {
                e.worldX += e.speed;
                if (e.area.intersects(hitbox)) {
                    return true;
                }
            }
        }
        e.area.x = e.areaDefaultX;
        e.area.y = e.areaDefaultY;
        lvl.player.area.x = lvl.player.areaDefaultX;
        lvl.player.area.y = lvl.player.areaDefaultY;
        return false;
    }
}
