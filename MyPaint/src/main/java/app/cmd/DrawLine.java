package app.cmd;

import app.MyImage;
import tools.Area;
import java.awt.geom.Line2D;

/**
 * 
 * <p>An implementation of CMD which draws a line on MyImage using breadth search.</p>
 * 
 */

public class DrawLine implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
        if (area == null || img == null) return;
        
        img.getGraphics().draw(new Line2D.Float(area.getLastX(), area.getLastY(),
                 area.getCurX(), area.getCurY()));
    }

}
