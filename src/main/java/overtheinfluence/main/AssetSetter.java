package main;

import objects.*;

import java.io.*;
import java.util.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class sets objects and barrier blocks into the game.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>Object setting - Kevin Zhan</li>
 *     <li>Barrier block setting - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class AssetSetter {
    /**
     * The level the assets are being set into.
     */
    public Level lvl;

    /**
     * constructor for AssetSetter
     *
     * @param lvl the level the assets are being set into
     */
    public AssetSetter(Level lvl) {
        this.lvl = lvl;
    }

    /**
     * sets the objects into the level.
     */
    public void setObject() {
        lvl.objects.add(new KitchenTable(this));
        lvl.objects.get(0).setPosition(12 * lvl.tileSize, (int) (17.5 * lvl.tileSize));
        lvl.objects.add(new Couch(this));
        lvl.objects.get(1).setPosition((int) (2.25 * lvl.tileSize), 19 * lvl.tileSize);
        lvl.objects.add(new Bed(this));
        lvl.objects.get(2).setPosition((int) (5.25 * lvl.tileSize), 3 * lvl.tileSize);
        lvl.objects.add(new Bed(this));
        lvl.objects.get(3).setPosition(3 * lvl.tileSize, 3 * lvl.tileSize);
        lvl.objects.add(new Bed(this));
        lvl.objects.get(4).setPosition((int) (8.25 * lvl.tileSize), 3 * lvl.tileSize);
        lvl.objects.add(new House(this, -1, -1));
        lvl.objects.get(5).setPosition((int) (11.85 * lvl.tileSize), (int) (31.5 * lvl.tileSize));
        lvl.objects.add(new House(this, -1, -1));
        lvl.objects.get(6).setPosition((int) (6.85 * lvl.tileSize), (int) (31.5 * lvl.tileSize));
        lvl.objects.add(new House(this, 8 * lvl.tileSize, 21 * lvl.tileSize));
        lvl.objects.get(7).setPosition((int) (1.85 * lvl.tileSize), (int) (31.5 * lvl.tileSize));
        lvl.objects.add(((House) lvl.objects.get(5)).triggerDoor);
        lvl.objects.add(((House) lvl.objects.get(6)).triggerDoor);
        lvl.objects.add(((House) lvl.objects.get(7)).teleportDoor);
        lvl.objects.add(new Door(this, 8 * lvl.tileSize, 23 * lvl.tileSize, true, (int) (1.8 * lvl.tileSize) + 85, (int) (36.25 * lvl.tileSize)));
        lvl.objects.add(((Door) (lvl.objects.get(11))).teleport);
        if (lvl instanceof Exploration) {
            lvl.objects.add(new Desk(this));
            lvl.objects.get(13).setPosition((int) (3.25 * lvl.tileSize), 11 * lvl.tileSize);
            lvl.objects.add(new IndicateArrow(0, 0));
            lvl.objects.get(14).setPosition((int) (3.32 * lvl.tileSize), (int)(9.8 * lvl.tileSize));
        } else if (lvl instanceof Recovery) {

        }
        objectBlockLayout();
    }

    /**
     * organizes the layout of barrier blocks in the level
     */
    public void objectBlockLayout() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/resources/map/objectLayout/layout" + lvl.levelNum + ".txt"))));
            String[] arr = br.readLine().split(" ");
            int maxCols = Integer.parseInt(arr[0]);
            int maxRows = Integer.parseInt(arr[1]);
            int col = 0;
            int row = 0;
            while (col < maxCols && row < maxRows) {
                String line = br.readLine();
                while (col < maxCols) {
                    String[] nums = line.split("");
                    if (nums[col].equals("x")) {
                        lvl.objects.add(new BarrierBlock(col, row, true));
                    }
                    col++;
                }
                if (col == maxCols) {
                    row++;
                    col = 0;
                }
            }
        } catch (IOException e) {
        }
    }
}
