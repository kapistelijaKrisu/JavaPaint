package app.cmd;

import tools.Area;
import app.ControlUnit;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class DrawLine extends CMD {

    public DrawLine(ControlUnit controller) {
        super(controller);
    }

    @Override
    public void execute(Area area) {
        if (areaOrControllerIsNull(area)) return;
        
        Graphics2D g2 = controller.getImg().getGraphics();
        g2.draw(new Line2D.Float(area.getLastX(), area.getLastY(),
                 area.getCurX(), area.getCurY()));
    }
}
