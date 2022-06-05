package objects;

import entity.Entity;
import main.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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

public class Car extends Entity {
    /**
     * the constructor for car objects
     *
     * @param assetSetter the asset setter used to set the car in the world
     * @param carNum the number used to identify the car to add to the world
     */
    public Car(AssetSetter assetSetter, int carNum) {
        super(assetSetter.lvl);
        int drawWidth = 96;
        int drawHeight = 48;
        name = "Car";
        try {
            if(assetSetter.lvl.levelNum == 1) {
                if (carNum == 0) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/redCar.png"));
                } else if (carNum == 1) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/redCar.png"));
                    down1 = reflectImage(down1);
                } else if (carNum == 2) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/blueCar.png"));
                } else if (carNum == 3) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/blueCar.png"));
                    down1 = reflectImage(down1);
                }
            } else if(assetSetter.lvl.levelNum == 4) {
                if (carNum == 0 || carNum == 2) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/greyCar.png"));
                } else if (carNum == 1 || carNum == 3) {
                    down1 = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/greyCar.png"));
                    down1 = reflectImage(down1);
                }
            }
        } catch (IOException e) {
        }
        down1 = util.scaleImage(down1, drawWidth, drawHeight);
        collision = true;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }

    /**
     * reflects the image of the object
     *
     * @param image the image of the object
     * @return the reflected image
     */
    public BufferedImage reflectImage(BufferedImage image) {
        AffineTransform trans = AffineTransform.getScaleInstance(-1, 1);
        trans.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }
}
