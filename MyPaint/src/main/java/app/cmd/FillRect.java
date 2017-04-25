
package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.Area;
import tools.OneLineException;

/**
 * 
 * <p>An implementation of CMD which fills a rectangular area to MyImage.</p>
 * 
 */
public class FillRect implements CMD {

    @Override
    public void execute(MyImage img, Area rect) {
        img.getGraphics().fill(new Rectangle.Float(rect.getStartX(), rect.getStartY(),
                 rect.getCurX()+1, rect.getCurY()+1));
    }
    
}
