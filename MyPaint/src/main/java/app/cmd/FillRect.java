package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.TwoPoint;

/**
 * An implementation of CMD which fills a rectangular area on call.
 */
public class FillRect implements CMD {

    /**
     *
     * Fills a rectangle based on area's getRectangle() to img.
     *
     * @param img Target to be drawn on.
     * @param area information on where to draw.
     */
    @Override
    public void execute(MyImage img, TwoPoint area) {
        Rectangle rect = area.getRectangle();
        img.getGraphics().fill(new Rectangle.Float(rect.x, rect.y,
                rect.width + 1, rect.height + 1));
    }

}
