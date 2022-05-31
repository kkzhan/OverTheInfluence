package objects;

import javax.imageio.*;
import java.io.*;

public class Door extends GameObject {
    public Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/door.png"));
        } catch (IOException e) {
        }
        collision = true;
    }
}
