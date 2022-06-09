package npc;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class Brother extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;

    /**
     * constructor for the brother class
     *
     * @param lvl the level the brother is in
     * @param x   the x coordinate of the brother
     * @param y   the y coordinate of the brother
     */
    public Brother(Level lvl, int x, int y) {
        super(lvl);
        setDialogues();
        direction = "down";
        name = "Brother";
        collision = true;
        down1 = setup("entities/NPC/brother");
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);

        if (lvl.levelNum != 2) {
            Entity e = this;
            trigger = new TriggerBlock(lvl.assetSetter, 2 * lvl.tileSize, 2 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    lvl.ui.showMessage("Press E to talk to your brother", 20);
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
        if (lvl.levelNum == 1) {
            dialogue.add("Younger Brother#Hey big bro?");
            dialogue.add("Younger Brother#When can we go out together again?");
            dialogue.add("Younger Brother#It’s been so long since we last spent time together…");
            dialogue.add("Younger Brother#Also, you haven’t been looking too good recently.#Why do you smell so weird and why is your face so dirty?\n");

        } else if (lvl.levelNum == 3) {
            dialogue.add("Younger Brother#Hey big bro, let's go out and play!");
            dialogue.add("You#Alright, let's go out later today.");
            dialogue.add("Younger Brother#Yay, we get to play together again!");
            dialogue.add("Younger Brother#It's been so long since we last played together.");
            dialogue.add("Younger Brother#I'm so glad you're here!");
        }
    }
}
