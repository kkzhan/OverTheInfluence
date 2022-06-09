package npc;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class UnconsciousStranger extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;


    /**
     * constructor for the Stranger class
     *
     * @param lvl the level
     * @param x   the x coordinate
     * @param y   the y coordinate
     */
    public UnconsciousStranger(Level lvl, int x, int y) {
        super(lvl);
        setDialogues();
        direction = "down";
        name = "Unconscious Stranger";
        collision = true;
        down1 = setup("entities/NPC/unconscious");
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);
        if (lvl.levelNum != 2) {
            Entity e = this;
            trigger = new TriggerBlock(lvl.assetSetter, 4 * lvl.tileSize, 3 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    lvl.ui.showMessage("Press E to interact with the unconscious stranger", 20);
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
            dialogue.add("Unconscious Person#*This person seems to have blacked out after taking too many drugs.*");
            dialogue.add("Unconscious Person#*They seem to be in a state of euphoria#but they also don’t seem to be in a good place in life.*");
        } else if (lvl.levelNum == 3) {
            dialogue.add("Unconscious Person#*This person seems to have blacked out after taking too many drugs.*");
            dialogue.add("Unconscious Person#*They seem to be in a state of euphoria#but they also don’t seem to be in a good place in life.*");
            dialogue.add("Unconscious Person#*The realization sinks in that this is what you looked like to others#before you got help.*");
            dialogue.add("Unconscious Person#*You feel bad for the person, but also happy that you've grown as a person.*");
        }
    }
}
