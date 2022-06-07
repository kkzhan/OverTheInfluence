package NPC;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class UnconsciousStranger extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;

    public boolean active = true;


    /**
     * constructor for the Stranger class
     *
     * @param lvl the level
     * @param x   the x coordinate
     * @param y   the y coordinate
     */
    public UnconsciousStranger(Level lvl, int x, int y) {
        super(lvl);
        direction = "down";
        name = "Unconscious Stranger";
        collision = true;
        down1 = setup("entities/NPC/unconscious");
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);
        if (lvl.levelNum == 1) {
            active = false;
            trigger = new TriggerBlock(lvl.assetSetter, 4 * lvl.tileSize, 3 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    if (active) {
                        lvl.ui.showMessage("Press E to interact", 20);
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
