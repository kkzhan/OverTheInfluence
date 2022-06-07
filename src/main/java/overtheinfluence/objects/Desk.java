package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents Desk objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Desk class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Desk extends Entity {

    public TriggerBlock trigger;

    public IndicateArrow arrow;

    public boolean active = true;

    /**
     * the constructor for Desk objects
     *
     * @param assetSetter the asset setter used to set Desk objects in the world
     * @param x the x coordinate of the Desk object in the world
     * @param y the y coordinate of the Desk object in the world
     */
    public Desk(AssetSetter assetSetter, int x, int y) {
        super(assetSetter.lvl);
        setDialogues();
        int drawWidth = 30;
        int drawHeight = 64;
        name = "Desk";
        worldX = x;
        worldY = y;
        try {
            if (assetSetter.lvl.levelNum == 1) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1Desk.png"));
            } else if (assetSetter.lvl.levelNum == 3) {
                down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3Desk.png"));
            }
            down1 = util.scaleImage(down1, drawWidth, drawHeight);
        } catch (IOException e) {
        }
        collision = true;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);

        if(lvl.levelNum == 1) {
            active = false;
            Desk desk = this;
            trigger = new TriggerBlock(assetSetter, drawWidth + lvl.tileSize / 2, drawHeight, worldX, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    if (active) {
                        lvl.ui.showMessage("Press E to talk to your brother", 20);
                        if (lvl.keyIn.interact) {
                            lvl.objects.remove(arrow);
                            //run dialogue
                        }
                    } else {
                        lvl.ui.showMessage("There  is  someone  else  you need  to  talk  to  first", 10);
                        if(lvl.keyIn.interact) {
                            desk.speak();
                        }
                    }
                }
            };
        }
    }

    public void setDialogues() {
        dialogue.add("I'm not sure what to say");
        dialogue.add("I'm not sure what to say about this");
    }
}
