package app.cmd;

import app.MyImage;
import java.awt.Color;
import java.awt.Rectangle;
import tools.TwoPoint;

/**
 *
 * An implementation of CMD which calculates the average color and sets it onto
 * target.
 *
 */
public class SetAvgColor implements CMD {

    /**
     *
     * Calculates average color based on area's getRectangle() and sets it onto
     * img.
     *
     * @param img Target.
     * @param area Information on where to calculate new color.
     */
    @Override
    public void execute(MyImage img, TwoPoint area) {
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        Rectangle rect = area.getRectangle();
        for (int y = 0; y < rect.height + 1; y++) {
            for (int x = 0; x < rect.width + 1; x++) {

                int val = img.getImg().getRGB(x + rect.x, y + rect.y);
                a += (0xff000000 & val) >>> 24;
                r += (0x00ff0000 & val) >> 16;
                g += (0x0000ff00 & val) >> 8;
                b += (0x000000ff & val);
            }

        }

        int divider = (rect.height + 1) * (rect.width + 1);
        a /= divider;
        r /= divider;
        g /= divider;
        b /= divider;

        img.setColor(new Color(r, g, b, a));

    }

}
