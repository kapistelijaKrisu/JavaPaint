package app.cmd;

import app.MyImage;
import tools.Area;
import java.awt.geom.Line2D;

public class DrawLine implements CMD {

    @Override
    public void execute(MyImage img, Area area) {
        if (area == null || img == null) return;
        
        img.getGraphics().draw(new Line2D.Float(area.getLastX(), area.getLastY(),
                 area.getCurX(), area.getCurY()));
    }

}
