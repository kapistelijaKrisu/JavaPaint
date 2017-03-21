package app.cmd;

import tools.Area;
import app.ControlUnit;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;;

public class FillPoint extends CMD {

    private final int UNINITIALIZED_VALUE = -1;
    private int prevX = UNINITIALIZED_VALUE, prevY = UNINITIALIZED_VALUE;

    public FillPoint(ControlUnit controller) {
        super(controller);
    }

    @Override
    public void execute(Area area) {
        Graphics2D g2 = controller.getImg().getGraphics();
        if (prevX == UNINITIALIZED_VALUE) {
            prevX = area.updateX;
            prevY = area.updateY;
        }
        g2.draw(new Line2D.Float(area.updateX, area.updateY, prevX, prevY));
        prevX = area.updateX;
        prevY = area.updateY;
    }

    @Override
    public void reset() {
        prevX = UNINITIALIZED_VALUE;
        prevY = UNINITIALIZED_VALUE;
    }
}
