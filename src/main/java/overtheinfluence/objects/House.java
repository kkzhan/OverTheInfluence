package objects;

import main.AssetSetter;

import javax.imageio.*;
import java.awt.*;
import java.io.IOException;

public class House extends GameObject {
    public TeleportationBlock door;

    public House(AssetSetter assetSetter, int targetX, int targetY) {
        name = "Couch";
        try {
            if (assetSetter.lvl.levelNum == 1) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/lvl1House.png"));
            } else if (assetSetter.lvl.levelNum == 4) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/lvl3House.png"));
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 160;
        drawHeight = 215;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
        door = new TeleportationBlock(40, 45, area.x + 85, area.y + 150, targetX, targetY, true);
    }

    @Override
    public void setPosition(int x, int y) {
        area.x = x;
        area.y = y;
        door.setPosition(x + 85, y + 150);
    }
}
