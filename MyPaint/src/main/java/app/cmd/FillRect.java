
package app.cmd;

import app.ControlUnit;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import tools.Area;

public class FillRect extends CMD {

    public FillRect(ControlUnit controller) {
        super(controller);
    }

    @Override
    public void execute(Area area) {
        if (areaOrControllerIsNull(area)) return;
        Graphics2D g2 = controller.getImg().getGraphics();
        g2.draw(new Rectangle.Float(area.getStartX(), area.getStartY(),
                 area.getCurX(), area.getCurY()));
    }
    
}
