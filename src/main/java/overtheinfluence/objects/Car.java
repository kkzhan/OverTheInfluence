package objects;

import main.*;

import javax.imageio.*;
import java.awt.*;
import java.io.IOException;

/**
 * Over the Influence is a game by Digital Athletics Inc. intended to educate individuals about the dangers of
 * drug addiction and alcoholism, as well as reinforce concepts related to overcoming and avoiding addiction.
 *
 * <p>This class represents Car objects in the world.</p>
 *
 * <p>Work Allocation:<ul>
 *     <li>Car class - Kevin Zhan</li>
 * </ul></p>
 *
 * <h2>ICS4U0 -with Krasteva, V.</h2>
 *
 * @author Kevin Zhan, Alexander Peng
 * @version 1.0
 */

public class Car extends GameObject {
    /**
     * the constructor for car objects
     *
     * @param assetSetter the asset setter used to set the car in the world
     * @param carNum the number used to identify the car to add to the world
     */
    public Car(AssetSetter assetSetter, int carNum) {
        name = "Car";
        try {
            if(assetSetter.lvl.levelNum == 1) {
                if (carNum == 0) {
                    image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/redCar.png"));
                } else if (carNum == 1) {
                    image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/redCar.png"));
                    image = reflectImage(image);
                } else if (carNum == 2) {
                    image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/blueCar.png"));
                } else if (carNum == 3) {
                    image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/blueCar.png"));
                    image = reflectImage(image);
                }
            } else if(assetSetter.lvl.levelNum == 4) {
                if (carNum == 0 || carNum == 2) {
                    image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/greyCar.png"));
                } else if (carNum == 1 || carNum == 3) {
                    image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/greyCar.png"));
                    image = reflectImage(image);
                }
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 96;
        drawHeight = 48;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }
}
