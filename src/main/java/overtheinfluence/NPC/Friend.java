package NPC;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class Friend extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;

    public boolean active = true;

    /**
     * constructor for the Friend class
     *
     * @param lvl the level the friend is in
     * @param x   the x coordinate of the friend
     * @param y   the y coordinate of the friend
     * @num the number of the friend
     */
    public Friend(Level lvl, int x, int y, int num) {
        super(lvl);
        direction = "down";
        name = "Friend";
        collision = true;
        down1 = setup("entities/NPC/friend" + num);
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);
        if (lvl.levelNum == 1) {
            active = false;
            if (num == 1) {
                trigger = new TriggerBlock(lvl.assetSetter, 4 * lvl.tileSize, 3 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                    @Override
                    public void trigger() {
                        if (active) {
                            lvl.ui.showMessage("Press E to talk to your friends", 20);
                            if (lvl.keyIn.interact) {
                                lvl.objects.remove(arrow);
                                //run dialogue
                            }
                        } else {
                            lvl.ui.showMessage("There  is  someone  else  you need  to  talk  to  first", 10);
                        }
                    }
                };
            }
        }
    }
}
