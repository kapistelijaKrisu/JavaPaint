package ui.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BackGroundCreator {

    public static BufferedImage create(int width, int height, int rectSize) {

        BufferedImage bg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Color a = new Color(222, 222, 222);
        Color b = new Color(135, 135, 135);
        Color swap = a;
        for (int rectY = 0; rectY * rectSize < bg.getHeight(); rectY++) {
                if (swap.equals(a)) {
                    swap = b;
                } else {
                    swap = a;
                }
            for (int rectX = 0; rectX * rectSize < bg.getWidth(); rectX++) {
                if (swap.equals(a)) {
                    swap = b;
                } else {
                    swap = a;
                }

                for (int pixelY = 0; pixelY < rectSize; pixelY++) {
                    for (int pixelX = 0; pixelX < rectSize; pixelX++) {

                        bg.setRGB(Math.min(width - 1, rectX * rectSize + pixelX), Math.min(height - 1, rectY * rectSize + pixelY), swap.getRGB());

                    }
                }

            }
        }
        return bg;
    }
}
