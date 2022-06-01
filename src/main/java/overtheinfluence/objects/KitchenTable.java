package objects;

import main.AssetSetter;

import javax.imageio.*;
import java.awt.*;
import java.io.*;

public class KitchenTable extends GameObject {
    public KitchenTable(AssetSetter assetSetter) {
        name = "KitchenTable";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1KitchenTable.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3KitchenTable.png"));
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 64;
        drawHeight = 64;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}

