package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BackGroundCreator {

    public static BufferedImage create(int width, int height) {
        BufferedImage bg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int area = width / 50;
        Color a = new Color(222, 222, 222);
        Color b = new Color(135, 135, 135);

        for (int j = 0; j < bg.getHeight(); j += area*2) {
            for (int k = 0; k < bg.getWidth(); k += area*2 ) {
              
                for (int l = 0; l < area && j + l < bg.getHeight() - area *2; l++) {
                    for (int m = 0; m < area && k + m < bg.getWidth()-area*2; m++) {
                        bg.setRGB(k + m, j + l, a.getRGB());
                        bg.setRGB(k + m+area, j + l, b.getRGB());
                        bg.setRGB(k + m, j + l+area, b.getRGB());
                        bg.setRGB(k + m+area, j + l+area, a.getRGB());
                    }
                }
              
            }
        }
        return bg;
    }
}
