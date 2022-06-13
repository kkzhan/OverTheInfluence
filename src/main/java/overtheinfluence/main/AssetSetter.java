package main;

import npc.*;
import entity.*;
import objects.*;

import java.io.*;
import java.util.*;

/**
 * <p>This class sets objects and barrier blocks into the game.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Object setting - Kevin Zhan</li>
 * <li>Barrier block setting - Kevin Zhan</li>
 * <li>Shuffling yoga mats - Kevin Zhan</li>
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
    public final Level lvl;

    /**
     * stores added barriers
     */
    public final ArrayList<Entity> barriers = new ArrayList<>();

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
        if (lvl.levelNum == 1 || lvl.levelNum == 3) {
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
            lvl.objects.add(new House(this, -1, -1, false));
            lvl.objects.get(5).setPosition((int) (11.85 * lvl.tileSize), (int) (31.5 * lvl.tileSize));
            lvl.objects.add(new House(this, -1, -1, false));
            lvl.objects.get(6).setPosition((int) (6.85 * lvl.tileSize), (int) (31.5 * lvl.tileSize));
            lvl.objects.add(new House(this, 8 * lvl.tileSize, 21 * lvl.tileSize, true));
            lvl.objects.get(7).setPosition((int) (1.85 * lvl.tileSize), (int) (31.5 * lvl.tileSize));
            lvl.objects.add(new Door(this, 8 * lvl.tileSize, 23 * lvl.tileSize, true, (int) (1.8 * lvl.tileSize) + 85, (int) (36.25 * lvl.tileSize)));
            lvl.objects.add(((Door) (lvl.objects.get(8))).teleport);
            lvl.objects.add(((House) lvl.objects.get(5)).triggerDoor);
            lvl.objects.add(((House) lvl.objects.get(6)).triggerDoor);
            lvl.objects.add(((House) lvl.objects.get(7)).triggerDoor);
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.add(new GarbageBag(this));
            lvl.objects.get(13).setPosition((int) (62.25 * lvl.tileSize), (int) (23.3 * lvl.tileSize));
            lvl.objects.get(14).setPosition((int) (61.73 * lvl.tileSize), (int) (22.6 * lvl.tileSize));
            lvl.objects.get(15).setPosition((int) (61.1 * lvl.tileSize), (int) (23.2 * lvl.tileSize));
            lvl.objects.get(16).setPosition((int) (62.3 * lvl.tileSize), (int) (21.7 * lvl.tileSize));
            lvl.objects.get(17).setPosition((int) (60.85 * lvl.tileSize), (int) (21.9 * lvl.tileSize));
            lvl.objects.get(18).setPosition((int) (61.85 * lvl.tileSize), (int) (24.25 * lvl.tileSize));
            lvl.objects.get(19).setPosition((int) (62.35 * lvl.tileSize), (int) (20.925 * lvl.tileSize));
            lvl.objects.get(20).setPosition((int) (61.5 * lvl.tileSize), (int) (25.1 * lvl.tileSize));
            lvl.objects.add(new Dumpster(this));
            lvl.objects.add(new Dumpster(this));
            lvl.objects.get(21).setPosition(46 * lvl.tileSize, 27 * lvl.tileSize);
            lvl.objects.get(22).setPosition(46 * lvl.tileSize, 29 * lvl.tileSize);
            lvl.objects.add(new Car(this, 0));
            lvl.objects.add(new Car(this, 1));
            lvl.objects.add(new Car(this, 2));
            lvl.objects.add(new Car(this, 3));
            lvl.objects.get(23).setPosition(9 * lvl.tileSize, 55 * lvl.tileSize);
            lvl.objects.get(24).setPosition(48 * lvl.tileSize, (int) (51.5 * lvl.tileSize));
            lvl.objects.get(25).setPosition(64 * lvl.tileSize, 54 * lvl.tileSize);
            lvl.objects.get(26).setPosition(5 * lvl.tileSize, (int) (40.5 * lvl.tileSize));
            lvl.npcs.add(new Friend(lvl, (int) (lvl.tileSize * 5.5), (int) (lvl.tileSize * 45.5), 2));
            lvl.npcs.add(new Friend(lvl, lvl.tileSize * 7, lvl.tileSize * 45, 3));
            if (lvl.levelNum == 1) {
                setup1();
            } else {
                lvl.objects.add(new Desk(this, (int) (3.25 * lvl.tileSize), 11 * lvl.tileSize, false));
                setup3();
            }
        } else if (lvl.levelNum == 2) {
            for (int i = 1; i < lvl.maxWorldRows - 1; i++) {
                TriggerBlock finishLine = new TriggerBlock(this, lvl.tileSize, lvl.tileSize, (lvl.maxWorldCols - 7) * lvl.tileSize, i * lvl.tileSize, true) {
                    @Override
                    public void trigger() {
                        lvl.innerDemon.dying = true;
                    }
                };
                lvl.blocks.add(finishLine);
            }
        }
        objectBlockLayout();
    }

    /**
     * sets up level 1
     */
    public void setup1() {
        Desk desk = new Desk(this, (int) (3.25 * lvl.tileSize), 11 * lvl.tileSize, false);
        Friend friend = new Friend(lvl, lvl.tileSize * 5, lvl.tileSize * 45, 1);
        Mom mom = new Mom(lvl, lvl.tileSize * 11, lvl.tileSize * 18);
        Brother brother = new Brother(lvl, lvl.tileSize * 3, lvl.tileSize * 20);
        Stranger stranger1 = new Stranger(lvl, lvl.tileSize * 79, lvl.tileSize * 56, 1);
        Stranger stranger2 = new Stranger(lvl, lvl.tileSize * 47, lvl.tileSize * 21, 2);
        Stranger stranger3 = new Stranger(lvl, lvl.tileSize * 40, lvl.tileSize * 51, 3);
        UnconsciousStranger unconsciousStranger = new UnconsciousStranger(lvl, (int) (lvl.tileSize * 40.5), (int) (lvl.tileSize * 29.5));
        lvl.blocks.add(desk.trigger);
        lvl.blocks.add(friend.trigger);
        lvl.blocks.add(mom.trigger);
        lvl.blocks.add(brother.trigger);
        lvl.blocks.add(stranger1.trigger);
        lvl.blocks.add(stranger2.trigger);
        lvl.blocks.add(stranger3.trigger);
        lvl.blocks.add(unconsciousStranger.trigger);
        lvl.lvl1Sequence.add(desk);
        lvl.lvl1Sequence.add(friend);
        lvl.lvl1Sequence.add(mom);
        lvl.lvl1Sequence.add(brother);
        lvl.lvl1Sequence.add(stranger1);
        lvl.lvl1Sequence.add(stranger2);
        lvl.lvl1Sequence.add(stranger3);
        lvl.lvl1Sequence.add(unconsciousStranger);
        lvl.npcs.add(desk);
        lvl.npcs.add(friend);
        lvl.npcs.add(mom);
        lvl.npcs.add(brother);
        lvl.npcs.add(stranger1);
        lvl.npcs.add(stranger2);
        lvl.npcs.add(stranger3);
        lvl.npcs.add(unconsciousStranger);
        TriggerBlock endTrigger = new TriggerBlock(this, lvl.tileSize * 5, lvl.tileSize, 3 * lvl.tileSize, lvl.tileSize * 5, false) {
            @Override
            public void trigger() {
                if(lvl.lvl1Sequence.size() == 0) {
                    lvl.complete = true;
                }
            }
        };
        lvl.blocks.add(endTrigger);
    }

    /**
     * sets up level 3
     */
    public void setup3() {
        //objects
        Bed bed = new Bed(this);
        bed.setPosition(147 * lvl.tileSize, (int) (25.25 * lvl.tileSize));
        lvl.objects.add(bed);
        lvl.objects.add(new Door(this, 146 * lvl.tileSize, 29 * lvl.tileSize, false, -1, -1));
        lvl.objects.add(new Door(this, 141 * lvl.tileSize, 37 * lvl.tileSize, false, -1, -1));

        //yoga challenge
        YogaMat mat1 = new YogaMat(this, 100 * lvl.tileSize, 12 * lvl.tileSize, 1);
        YogaMat mat2 = new YogaMat(this, 106 * lvl.tileSize, 12 * lvl.tileSize, 2);
        YogaMat mat3 = new YogaMat(this, 112 * lvl.tileSize, 12 * lvl.tileSize, 3);
        YogaMat mat4 = new YogaMat(this, 118 * lvl.tileSize, 12 * lvl.tileSize, 4);
        lvl.blocks.add(mat1);
        lvl.blocks.add(mat2);
        lvl.blocks.add(mat3);
        lvl.blocks.add(mat4);
        lvl.blocks.add(mat1.trigger);
        lvl.blocks.add(mat2.trigger);
        lvl.blocks.add(mat3.trigger);
        lvl.blocks.add(mat4.trigger);
        //decorative dining tables
        RehabTable table1 = new RehabTable(this);
        table1.setPosition(lvl.tileSize * 112, lvl.tileSize * 24);
        RehabTable table2 = new RehabTable(this);
        table2.setPosition(lvl.tileSize * 112, lvl.tileSize * 29);
        RehabTable table3 = new RehabTable(this);
        table3.setPosition(lvl.tileSize * 112, lvl.tileSize * 34);
        RehabTable table4 = new RehabTable(this);
        table4.setPosition(lvl.tileSize * 128, lvl.tileSize * 24);
        RehabTable table5 = new RehabTable(this);
        table5.setPosition(lvl.tileSize * 128, lvl.tileSize * 29);
        RehabTable table6 = new RehabTable(this);
        table6.setPosition(lvl.tileSize * 128, lvl.tileSize * 34);
        lvl.objects.add(table1);
        lvl.objects.add(table2);
        lvl.objects.add(table3);
        lvl.objects.add(table4);
        lvl.objects.add(table5);
        lvl.objects.add(table6);

        //consumables
        lvl.objects.add(new Consumable(lvl, 139, 16, 0));
        lvl.objects.add(new Consumable(lvl, 142, 16, 1));
        lvl.objects.add(new Consumable(lvl, 11, 19, 0));
        lvl.objects.add(new Consumable(lvl, 11, 21, 1));

        //psychiatrist
        Desk psychDesk = new Desk(this, (int) (136.5 * lvl.tileSize), 38 * lvl.tileSize, true);
        lvl.blocks.add(psychDesk.trigger);
        lvl.npcs.add(psychDesk);
        Psychiatrist psych = new Psychiatrist(lvl, (int) (135.5 * lvl.tileSize), (int) (38.5 * lvl.tileSize));
        lvl.npcs.add(psych);

        //interactive npcs
        Friend friend = new Friend(lvl, lvl.tileSize * 5, lvl.tileSize * 45, 1);
        Mom mom = new Mom(lvl, lvl.tileSize * 11, lvl.tileSize * 18);
        Brother brother = new Brother(lvl, lvl.tileSize * 3, lvl.tileSize * 20);
        Stranger stranger1 = new Stranger(lvl, lvl.tileSize * 79, lvl.tileSize * 56, 1);
        Stranger stranger2 = new Stranger(lvl, lvl.tileSize * 47, lvl.tileSize * 21, 2);
        Stranger stranger3 = new Stranger(lvl, lvl.tileSize * 40, lvl.tileSize * 51, 3);
        UnconsciousStranger unconsciousStranger = new UnconsciousStranger(lvl, (int) (lvl.tileSize * 40.5), (int) (lvl.tileSize * 29.5));
        lvl.blocks.add(friend.trigger);
        lvl.blocks.add(mom.trigger);
        lvl.blocks.add(brother.trigger);
        lvl.blocks.add(stranger1.trigger);
        lvl.blocks.add(stranger2.trigger);
        lvl.blocks.add(stranger3.trigger);
        lvl.blocks.add(unconsciousStranger.trigger);
        lvl.lvl3Sequence.add(friend);
        lvl.lvl3Sequence.add(mom);
        lvl.lvl3Sequence.add(brother);
        lvl.lvl3Sequence.add(stranger1);
        lvl.lvl3Sequence.add(stranger2);
        lvl.lvl3Sequence.add(stranger3);
        lvl.lvl3Sequence.add(unconsciousStranger);
        lvl.npcs.add(friend);
        lvl.npcs.add(mom);
        lvl.npcs.add(brother);
        lvl.npcs.add(stranger1);
        lvl.npcs.add(stranger2);
        lvl.npcs.add(stranger3);
        lvl.npcs.add(unconsciousStranger);
    }

    /**
     * shuffles the positions of the level 3 yoga mats in rehabilitation
     */
    public void shuffleYogaMats() {
        ArrayList<Entity> mats = new ArrayList<>();
        for (Entity e : lvl.blocks) {
            if (e instanceof YogaMat) {
                mats.add(e);
            }
        }
        Collections.shuffle(mats);
        for (int i = 0; i < mats.size(); i++) {
            (mats.get(i)).setPosition(lvl.tileSize * (100 + i * 6), lvl.tileSize * 12);
        }
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
                        lvl.objects.add(new BarrierBlock(this, col, row, true, false));
                    }
                    col++;
                }
                if (col == maxCols) {
                    row++;
                    col = 0;
                }
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * debuffs the player by putting barriers in front of them
     */
    public void barrierDebuff() {
        for (int i = 1; i < lvl.maxWorldRows - 1; i++) {
            barriers.add(new BarrierBlock(this, lvl.player.worldX + (int) (lvl.tileSize * 1.25), i * lvl.tileSize, false, true));
            lvl.blocks.add(barriers.get(barriers.size() - 1));
        }
    }

    /**
     * clears the barriers in front of the player
     */
    public void barrierClear() {
        for (Entity barrier : barriers) {
            lvl.blocks.remove(barrier);
        }
        barriers.clear();
    }
}
