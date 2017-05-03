package ui.tools;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BackGroundCreator {

    private static final Color A = new Color(222, 222, 222);
    private static final Color B = new Color(135, 135, 135);
    private static Color swap = new Color(222, 222, 222);

    public static BufferedImage create(int width, int height, int rectSize) {
        BufferedImage bg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int rectY = 0; rectY * rectSize < bg.getHeight(); rectY++) {
            swap();
            if (width % rectSize == 0) {
                swap();
            }
            for (int rectX = 0; rectX * rectSize < bg.getWidth(); rectX++) {
                swap();

                for (int pixelY = 0; pixelY < rectSize; pixelY++) {
                    for (int pixelX = 0; pixelX < rectSize; pixelX++) {

                        bg.setRGB(Math.min(width - 1, rectX * rectSize + pixelX), Math.min(height - 1, rectY * rectSize + pixelY), swap.getRGB());

                    }
                }

            }
        }
        return bg;
    }

    private static void swap() {
        if (swap.equals(A)) {
            swap = B;
        } else {
            swap = A;
        }
    }
}
