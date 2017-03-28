
package app.cmd;

import app.MyImage;
import java.awt.Rectangle;
import tools.Area;

public class DrawRect implements CMD {


    @Override
    public void execute(MyImage img, Area area) {
        if (area == null || img == null) return;
        
        img.getGraphics().draw(new Rectangle.Float(area.getStartX(), area.getStartY(),
                 area.getCurX(), area.getCurY()));
    }
    
}
