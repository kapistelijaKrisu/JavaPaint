package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.TwoPoint;

/**
 *
 * An implementation of CMD which draws a rectangle on call. 
 *
 */
public class DrawRect implements CMD {

    /**
     *
     * Draws a rectangle based on area's getRectangle() to img.
     * @param img Target to be drawn on.
     * @param area information on where to draw.
     */
    @Override
    public void execute(MyImage img, TwoPoint area) {
        Rectangle r = area.getRectangle();
        img.getGraphics().draw(new Rectangle.Float(r.x, r.y,
                r.width, r.height));
    }

}
