
package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.Area;

/**
 * 
 * <p>An implementation of CMD which fills a rectangular area to MyImage.</p>
 * 
 */
public class FillRect implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
        Rectangle rect = area.getRectangle();
        img.getGraphics().fill(new Rectangle.Float(rect.x, rect.y,
                 rect.width+1, rect.height+1));
    }
    
}
