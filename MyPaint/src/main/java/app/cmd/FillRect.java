
package app.cmd;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import tools.Area;

public class FillRect implements CMD {

    @Override
    public void execute(BufferedImage img, Area area) {
        if (area == null || img == null) return;
        img.createGraphics().fill(new Rectangle.Float(area.getStartX(), area.getStartY(),
                 area.getCurX(), area.getCurY()));
    }
    
}
