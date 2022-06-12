package npc;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class contains information about the NPC that is the player's mother.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Mom class - Kevin Zhan</li>
 * <li>Mom artwork - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Mom extends Entity {
    /**
     * the trigger block that will activate when the player is near
     */
    public TriggerBlock trigger;

    /**
     * the arrow indicating that this entity can be interacted with
     */
    public IndicateArrow arrow;

    /**
     * constructor for the Mom class
     *
     * @param lvl the level the mom is in
     * @param x   the x coordinate of the mom
     * @param y   the y coordinate of the mom
     */
    public Mom(Level lvl, int x, int y) {
        super(lvl);
        setDialogues();
        direction = "down";
        name = "Mom";
        collision = true;
        down1 = setup("entities/NPC/lvl" + lvl.levelNum + "Mom");
        down1 = scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);
        if (lvl.levelNum != 2) {
            Entity e = this;
            trigger = new TriggerBlock(lvl.assetSetter, 2 * lvl.tileSize, 2 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    lvl.ui.showMessage("Press E to talk to your mom", 20);
                    if (lvl.keyIn.interact) {
                        lvl.objects.remove(arrow);
                        lvl.entities.remove(arrow);
                        e.speak();
                        lvl.lvl1Sequence.remove(e);
                        lvl.lvl3Sequence.remove(e);
                    }
                }
            };
            arrow = new IndicateArrow(lvl.assetSetter, worldX + 10, worldY - lvl.tileSize);
            lvl.objects.add(arrow);
        }
    }

    public void setDialogues() {
        if(lvl.levelNum == 1) {
            dialogue.add("Mom#*sniff* ");
            dialogue.add("Mom#What happened to you? You haven’t been yourself lately…");
            dialogue.add("Mom#The drugs must have messed with you somehow.");
            dialogue.add("Mom#You never spend time with us anymore and on the rare occasions that#you do, you never seem happy.");
            dialogue.add("Mom#You’re only 16 and the drugs have already taken over you… ");
            dialogue.add("Mom#*sniff*");
        } else if(lvl.levelNum == 3) {
            dialogue.add("Mom#I'm so happy that you overcame your drug addiction!");
            dialogue.add("Mom#It's like the old you is back again.");
            dialogue.add("Mom#You seem much happier and you've been spending much more time with#other people.");
            dialogue.add("Mom#Just remember to drink water and eat food so you don't relapse.");
            dialogue.add("Mom#You can always come back here if you ever need anything.");
        }
    }
}
