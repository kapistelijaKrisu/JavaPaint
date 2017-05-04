package app.cmd;

import app.MyImage;
import tools.TwoPoint;

/**
 * An implementation of CMD which replaces all occurences of a color by another
 * color on call.
 *
 */
public class ReplaceColor implements CMD {

    /**
     *
     * Finds all pixels of same color as point's current values are pointing to
     * and replaces their color by img's current color.
     *
     * @param img Target to be drawn on.
     * @param point information on where to pick color to be replaced.
     */
    @Override
    public void execute(MyImage img, TwoPoint point) {
        int toReplace = img.getImg().getRGB(point.getCurX(), point.getCurY());
        int height = img.getImg().getHeight();
        int width = img.getImg().getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (img.getImg().getRGB(x, y) == toReplace) {
                    img.getImg().setRGB(x, y, img.getColor().getRGB());
                }

            }

        }

    }

}
