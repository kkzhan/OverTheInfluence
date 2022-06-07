package NPC;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class Brother extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;

    public boolean active = true;

    /**
     * constructor for the brother class
     *
     * @param lvl the level the brother is in
     * @param x   the x coordinate of the brother
     * @param y   the y coordinate of the brother
     */
    public Brother(Level lvl, int x, int y) {
        super(lvl);
        direction = "down";
        name = "Brother";
        collision = true;
        down1 = setup("entities/NPC/brother");
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);

        if (lvl.levelNum == 1) {
            active = false;
            trigger = new TriggerBlock(lvl.assetSetter, 2 * lvl.tileSize, 2 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    if (active) {
                        lvl.ui.showMessage("Press E to talk to your brother", 20);
                        if (lvl.keyIn.interact) {
                            lvl.objects.remove(arrow);
                            //run dialogue
                        }
                    } else {
                        lvl.ui.showMessage("Thereis someone else you need to talk to first", 10);
                    }
                }
            };
        }
    }
}
