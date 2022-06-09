package main;

import java.awt.*;
import java.awt.image.*;

public class UtilTool {
    /**
     * scales the image
     * @param image the image to be scaled
     * @param width the width of the scaled image
     * @param height the height of the scaled image
     * @return the scaled image
     */
    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(image, 0, 0, width, height, null);
        g2D.dispose();
        return scaledImage;
    }
}
