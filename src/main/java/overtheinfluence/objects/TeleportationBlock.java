package objects;

import java.awt.*;

public class TeleportationBlock extends GameObject {
    public boolean requireInteract;
    public int targetX, targetY;

    public TeleportationBlock(int width, int height, int x, int y, int targetX, int targetY, boolean requireInteract) {
        name = "TeleportationBlock";
        area = new Rectangle(x, y, width, height);
        this.targetX = targetX;
        this.targetY = targetY;
        this.requireInteract = requireInteract;
    }
}
