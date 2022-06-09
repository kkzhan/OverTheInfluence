package npc;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;

public class Stranger extends Entity {
    public TriggerBlock trigger;

    public IndicateArrow arrow;

    /**
     * constructor for the Stranger class
     *
     * @param lvl the level the stranger is in
     * @param x   the x coordinate of the stranger
     * @param y   the y coordinate of the stranger
     * @param num the number of the stranger
     */
    public Stranger(Level lvl, int x, int y, int num) {
        super(lvl);
        setDialogues(num);
        direction = "down";
        name = "Stranger" + num;
        collision = true;
        down1 = setup("entities/NPC/stranger" + num);
        down1 = util.scaleImage(down1, lvl.tileSize, lvl.tileSize);
        worldX = x;
        worldY = y;
        area = new Rectangle(worldX, worldY, lvl.tileSize, lvl.tileSize);

        if (lvl.levelNum != 2) {
            Entity e = this;
            trigger = new TriggerBlock(lvl.assetSetter, 4 * lvl.tileSize, 3 * lvl.tileSize, worldX - lvl.tileSize / 2, worldY - lvl.tileSize / 2, false) {
                @Override
                public void trigger() {
                    lvl.ui.showMessage("Press E to talk to this person", 20);
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

    public void setDialogues(int num) {
        if (lvl.levelNum == 1) {
            if (num == 1) {
                dialogue.add("Stranger#Ohh… I feel so sick.");
                dialogue.add("Stranger#I can’t believe I forgot my drugs at home…");
                dialogue.add("Stranger#Gotta hurry home and get them before these cravings kill me.");
            } else if (num == 2) {
                dialogue.add("Shady Person#Yo, you back for some more drugs?");
                dialogue.add("Shady Person#My prices rose but I got a little deal for ya.#I’ll sell you 3 bottles for the price of two.");
                dialogue.add("You#Yeah ok. Just gimme a sec… Wait, where did my money go?");
                dialogue.add("Shady Person#Huh?! Why are you even here if you don’t have any money?!#You wasted it all on buying drugs, didn’t you?!");
                dialogue.add("Shady Person#Get outta here if you’re broke! Stop wasting my time!");
            } else if (num == 3) {
                dialogue.add("Stranger#What am I going to do?");
                dialogue.add("Stranger#I thought I got away with shoplifting without anybody else noticing.#But the police are on my tail.");
                dialogue.add("Stranger#Oh no… How am I gonna get out of this mess?");
            }
        } else if (lvl.levelNum == 3) {
            if (num == 1) {
                dialogue.add("Stranger#Ohh… I feel so sick.");
                dialogue.add("Stranger#I can’t believe I forgot my drugs at home…");
                dialogue.add("Stranger#Gotta hurry home and get them before these cravings kill me.");
                dialogue.add("You#*You feel bad for this person and wish for them to get#help overcoming their addiction soon.#");
            } else if (num == 2) {
                dialogue.add("Shady Person#Yo, you back for some more drugs?");
                dialogue.add("Shady Person#My prices rose but I got a little deal for ya.#I’ll sell you 3 bottles for the price of two.");
                dialogue.add("You#No thank you, I've moved on from that chapter of my life.");
                dialogue.add("Shady Person#Aw damn. Pretty sad that I lost a customer.");
                dialogue.add("Shady Person#Gotta admit though, you look a lot better now.");
            } else if (num == 3) {
                dialogue.add("Stranger#Wait I've seen you before.");
                dialogue.add("Stranger#What happened to you?");
                dialogue.add("Stranger#You were in such a bad state last time and now to you look so#much healthier and happier.");
            }
        }
    }
}
