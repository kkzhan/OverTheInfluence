package objects;

import main.AssetSetter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Couch extends GameObject {
    public Couch(AssetSetter assetSetter) {
        name = "Couch";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl1Couch.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/furniture/lvl3Couch.png"));
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 32;
        drawHeight = 96;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
