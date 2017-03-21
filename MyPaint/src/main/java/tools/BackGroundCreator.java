
package tools;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BackGroundCreator {
    public static BufferedImage create(int width, int height) {
        BufferedImage bg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int area = 50;
        Color a = new Color(123, 123, 123);
        Color b = new Color(55, 55, 55);
        Color active = a;
        for (int j = 0; j < bg.getHeight(); j += area) {
            if (bg.getWidth() / area % 2 == 0) {
                if (active == a) {
                    active = b;
                } else {
                    active = a;
                }
            }
            for (int k = 0; k < bg.getWidth(); k += area) {
                if (active == a) {
                    active = b;
                } else {
                    active = a;
                }
                for (int l = 0; l < area && j + l < bg.getHeight(); l++) {
                    for (int m = 0; m < area && k + m < bg.getWidth(); m++) {
                        bg.setRGB(k + m, j + l, active.getRGB());
                    }
                }
            }
        }
        return bg;
    }
}
