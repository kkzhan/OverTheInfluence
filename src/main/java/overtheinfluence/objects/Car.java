package objects;

import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.IOException;

public class Car extends GameObject {
    public Car(int carNum) {
        name = "Car";
        try {
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
            } else if (carNum == 4) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/greyCar.png"));
            } else if (carNum == 5) {
                image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/cars/greyCar.png"));
                image = reflectImage(image);
            }
        } catch (IOException e) {
        }
        collision = true;
        drawWidth = 96;
        drawHeight = 48;
        area = new Rectangle(0, 0, drawWidth, drawHeight);
    }

    private BufferedImage reflectImage(BufferedImage image) {
        AffineTransform trans = AffineTransform.getScaleInstance(-1, 1);
        trans.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }
}
