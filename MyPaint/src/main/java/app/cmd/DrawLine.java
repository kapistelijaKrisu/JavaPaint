package app.cmd;

import app.MyImage;
import tools.TwoPoint;
import java.awt.geom.Line2D;

/**
 *
 * An implementation of CMD which draws a line on call.
 *
 */
public class DrawLine implements CMD {

    /**
     *
     * Draws a line based on area to img.
     * @param img Target to be drawn on.
     * @param area information on where to draw.
     */
    @Override
    public void execute(MyImage img, TwoPoint area) {
        img.getGraphics().draw(new Line2D.Float(area.getPrevX(), area.getPrevY(),
                area.getCurX(), area.getCurY()));
    }
}
