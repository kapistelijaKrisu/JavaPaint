package ui;

import tools.Area;
import app.ControlUnit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private final ControlUnit cmd;
    MyWindow window;
    Area area;

    public MouseInput(ControlUnit cmd, MyWindow window) {
        this.cmd = cmd;
        this.window = window;
        area = new Area(0, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (int) (e.getX() / window.getScale());
        int y = (int) (e.getY() / window.getScale());
        area.init(x, y);
        if (cmd.getCurrentCMD() == ControlUnit.defaultDrawCMD) {
            cmd.execute(area);
            window.drawToolTip = false;
        } else if (cmd.getCurrentCMD() == ControlUnit.defaultRectCMD) {
            window.drawToolTip = true;
        }
        window.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (cmd.getCurrentCMD() == ControlUnit.defaultDrawCMD) {
            cmd.execute(area);
        } else if (cmd.getCurrentCMD() == ControlUnit.defaultRectCMD) {
            cmd.execute(area.getRectangle());
        }
        window.drawToolTip = false;
        window.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        area.udpate(e.getX(), e.getY());
        if (cmd.getCurrentCMD() == ControlUnit.defaultDrawCMD) {
            cmd.execute(area);
        }
        window.repaint();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Area getArea() {
        return area;
    }
    
    

}
