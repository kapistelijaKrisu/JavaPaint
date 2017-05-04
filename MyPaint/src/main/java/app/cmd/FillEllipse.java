package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import tools.TwoPoint;

/**
 *
 * An implementation of CMD which fills an ellipse shaped area on call.
 */
public class FillEllipse implements CMD {

    /**
     *
     * Draws an ellipse based on area's getRectangle() to img.
     * @param img Target to be drawn on.
     * @param area information on where to draw.
     */
    @Override
    public void execute(MyImage img, TwoPoint area) {
        Rectangle r = area.getRectangle();
        img.getGraphics().fill(new Ellipse2D.Double(r.x, r.y,
                r.width, r.height));
    }
}
