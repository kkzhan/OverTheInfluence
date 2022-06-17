package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

/**
 * <p>This class represents Desk objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 * <li>Desk class - Kevin Zhan</li>
 * <li>Desk artwork - Alexander Peng</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Desk extends Entity {
    /**
     * the trigger block that will activate when the player is near
     */
    public TriggerBlock trigger;

    /**
     * the arrow indicating that this object can be interacted with
     */
    public IndicateArrow arrow;

    /**
     * the constructor for Desk objects
     *
     * @param assetSetter the asset setter used to set Desk objects in the world
     * @param x           the x coordinate of the Desk object in the world
     * @param y           the y coordinate of the Desk object in the world
     * @param rehab       whether the Desk object is in the rehabilitation facility
     */
    public Desk(AssetSetter assetSetter, int x, int y, boolean rehab) {
        super(assetSetter.lvl);
        setDialogues();
        int drawWidth = 30;
        int drawHeight = 64;
        name = "Desk";
        worldX = x;
        worldY = y;
        try {
            if (assetSetter.lvl.levelNum == 1) {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/furniture/lvl1Desk.png")));
            } else if (assetSetter.lvl.levelNum == 3) {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/furniture/lvl3Desk.png")));
            }
            if (rehab) {
                drawWidth = 48;
                drawHeight = 96;
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/objects/rehab/psychiatristDesk.png")));
            }
            down1 = scaleImage(down1, drawWidth, drawHeight);
        } catch (IOException ignored) {
        }
        collision = true;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);

        if (lvl.levelNum == 1) {
            Entity e = this;
            trigger = new TriggerBlock(assetSetter, drawWidth + lvl.tileSize / 2, drawHeight, worldX, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    lvl.ui.showMessage("Press E to use the computer on your desk", 20);
                    if (lvl.keyIn.interact) {
                        lvl.objects.remove(arrow);
                        lvl.entities.remove(arrow);
                        e.speak();
                        lvl.lvl1Sequence.remove(e);
                    }
                }
            };
            arrow = new IndicateArrow(lvl.assetSetter, worldX + 1, worldY - lvl.tileSize);
            lvl.objects.add(arrow);
        }
        if (lvl.levelNum == 3 && rehab) {
            Entity e = this;
            trigger = new TriggerBlock(assetSetter, drawWidth + (int) (lvl.tileSize * 2.5), drawHeight, worldX - lvl.tileSize * 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    lvl.ui.showMessage("Press E to talk to the psychiatrist", 20);
                    if (lvl.keyIn.interact) {
                        setDialogues();
                        e.speak();
                        if(lvl.player.yogaChallenge) {
                            lvl.therapyStarted = true;
                        }
                    }
                }
            };
        }
    }

    /**
     * sets the dialogues for the Desk object
     */
    public void setDialogues() {
        if (lvl.levelNum == 1) {
            dialogue.add("Searching up#What is drug addiction?");
            dialogue.add("Computer#Drug addiction is a chronic disease that affects one’s brain and behaviour.#Drug addiction is defined as when one is unable to resist the urge to use#drugs, no matter how much harm they may cause.");
            dialogue.add("Searching up#What are the dangers of being a drug addict?");
            dialogue.add("Computer#Dangers of drug addiction include but are not limited to: injury and#potential death, dependency on the drug, involvement in criminal activity,#and damage to relationships with friends and family.");
            dialogue.add("Searching up#How does a drug addiction develop?");
            dialogue.add("Computer#Initially, the decision to take drugs is usually voluntary.#Since most drugs create a sense of euphoria, many drug abusers#continue taking the drug to try to reach that state again.");
            dialogue.add("Computer#Repeated use of the drug leads to changes in the brain that challenges#one’s self-control and ability to resist the urge to use the drug.");
            dialogue.add("Searching up#How can somebody overcome drug addiction?");
            dialogue.add("Computer#Seek professional help.An addict can also set reasonable and measurable#goals for themselves. Also, They can inform their family and friends#about their decision and ask for their support.");
            dialogue.add("Computer#Finally, they can continuously remind themselves of why they wish to#overcome their addiction.");
        } else if (lvl.levelNum == 3) {
            dialogue.clear();
            if(!lvl.player.yogaChallenge) {
                dialogue.add("Dr. Dockter#Please participate in a yoga session before coming back to speak#with me.");
            } else if(!lvl.player.therapyChallenge) {
                dialogue.add("Dr. Dockter#Hello William, how are you feeling?#How is your rehabilitation going?");
                dialogue.add("You#Good and good");
                dialogue.add("Dr. Dockter#I see. That's good.");
                dialogue.add("Dr. Dockter#Well I'm going to ask you some questions.#Try your best to answer them correctly.");
            }
        }
    }
}
