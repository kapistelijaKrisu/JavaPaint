package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.Area;

/**
 * 
 * <p>An implementation of CMD which draws a rectangular area to MyImage.</p>
 * 
 */

public class DrawRect implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
        Rectangle r = area.getRectangle();
        img.getGraphics().draw(new Rectangle.Float(r.x, r.y,
                r.width, r.height));
    }

}
