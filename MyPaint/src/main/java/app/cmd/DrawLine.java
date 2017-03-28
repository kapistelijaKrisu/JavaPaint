package app.cmd;

import tools.Area;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class DrawLine implements CMD {

    @Override
    public void execute(BufferedImage img, Area area) {
        if (area == null || img == null) return;

        img.createGraphics().draw(new Line2D.Float(area.getLastX(), area.getLastY(),
                 area.getCurX(), area.getCurY()));
    }
}
