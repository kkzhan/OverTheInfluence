package npc;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class Friend extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;

    /**
     * constructor for the Friend class
     *
     * @param lvl the level the friend is in
     * @param x   the x coordinate of the friend
     * @param y   the y coordinate of the friend
     * @param num the number of the friend
     */
    public Friend(Level lvl, int x, int y, int num) {
        super(lvl);
        setDialogues();
        direction = "down";
        name = "Friend";
        collision = true;
        down1 = setup("entities/NPC/friend" + num);
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);
        if (lvl.levelNum == 1) {
            Entity e = this;
            if (num == 1) {
                trigger = new TriggerBlock(lvl.assetSetter, 4 * lvl.tileSize, 3 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                    @Override
                    public void trigger() {
                        lvl.ui.showMessage("Press E to talk to your friends", 20);
                        if (lvl.keyIn.interact) {
                            lvl.objects.remove(arrow);
                            lvl.entities.remove(arrow);
                            e.speak();
                            lvl.lvl1Sequence.remove(e);
                        }
                    }
                };
                arrow = new IndicateArrow(lvl.assetSetter, worldX + lvl.tileSize + 10, worldY - lvl.tileSize);
                lvl.objects.add(arrow);
            }
        }
    }

    public void setDialogues() {
        dialogue.add("You#Hey guys, wanna go out to eat?");
        dialogue.add("Billy#Uhh yeah, maybe…");
        dialogue.add("Billy#Oh sorry, my mom is calling me.");
        dialogue.add("Billy#Uh-huh. Yup. Yea. ");
        dialogue.add("Billy#Will’s inviting me to go out.");
        dialogue.add("Billy#What? Oh… Okay.");
        dialogue.add("Billy#Sorry bro, my mom says that I can’t go out with you.#Says that you’re a bad influence.");
        dialogue.add("Jenny#Yeah my mom said the same thing.");
        dialogue.add("You#Come on guys, no need to listen to your parents.");
        dialogue.add("Kate#Sorry, but I don't think we'll be going with you.");
        dialogue.add("Kate#Also no offence, but I kinda think our parents are right.");
        dialogue.add("Kate#You’ve been doing some really shady stuff recently.");
        dialogue.add("You#Like what?");
        dialogue.add("Jenny#Like stealing and lying to other people.");
        dialogue.add("Jenny#Maybe we can go out some day in the future. See you around.");
    }
}
