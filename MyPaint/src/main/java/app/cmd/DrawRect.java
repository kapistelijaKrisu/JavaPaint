package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.Area;
import ui.OneLineException;

/**
 * 
 * <p>An implementation of CMD which draws a rectangular area to MyImage.</p>
 * 
 */

public class DrawRect implements CMD {

    @Override
    public void execute(MyImage img, Area rect) {
        img.getGraphics().draw(new Rectangle.Float(rect.getStartX(), rect.getStartY(),
                rect.getCurX(), rect.getCurY()));
    }

}
