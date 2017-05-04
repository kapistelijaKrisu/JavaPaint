package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import tools.TwoPoint;

/**
 *
 * CMD implementation that draws an ellipse on call.
 */
public class DrawEllipse implements CMD {

    /**
     *
     * Draws an ellipse based on area's getRectagnle() to MyImage object.<br>
     * Due to Graphics2D draw method it may not paint very small areas like 1x1.
     * @param img Target to be drawn on.
     * @param area information on where to draw.
     */
    @Override
    public void execute(MyImage img, TwoPoint area) {
        Rectangle r = area.getRectangle();
        img.getGraphics().draw(new Ellipse2D.Float(r.x, r.y,
                r.width, r.height));
    }

}
