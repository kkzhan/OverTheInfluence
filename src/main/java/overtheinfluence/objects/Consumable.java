package objects;

import entity.*;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class Consumable extends Entity {
    public Consumable(Level lvl, int x, int y, int num) {
        super(lvl);
        if(num == 0) {
            name = "Food";
            try {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/entities/consumables/food.png")));
            } catch (IOException e) {
            }
        } else if(num == 1) {
            name = "Water";
            try {
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/entities/consumables/water.png")));
            } catch (IOException e) {
            }
        }
        int drawWidth = 36;
        int drawHeight = 36;
        down1 = scaleImage(down1, drawWidth, drawHeight);
        collision = false;
        worldX = x * lvl.tileSize + 6;
        worldY = y * lvl.tileSize + 6;
        area = new Rectangle(worldX, worldY, drawWidth, drawHeight);
    }
}
