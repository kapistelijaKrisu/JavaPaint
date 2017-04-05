package ui;

import tools.Area;
import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private final ControlUnit cmd;
    MyWindow w;
    Area area;

    public MouseInput(ControlUnit cmd, MyWindow window) {
        this.cmd = cmd;
        this.w = window;
        area = new Area(0, 0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (int) ((e.getX() - w.getxOffSet()) / w.getScale());
        int y = (int) ((e.getY() - w.getyOffSet()) / w.getScale());
        area.init(x, y);
        if (cmd.getCurrentCMD() == CommandMap.DRAWLINE) {
            cmd.execute(area);
            w.drawToolTip = false;
        } else if (cmd.getCurrentCMD() != CommandMap.DRAWLINE) {
            w.drawToolTip = true;
        }
        w.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (cmd.getCurrentCMD() == CommandMap.DRAWLINE || cmd.getCurrentCMD() == CommandMap.FILLCOLOR) {
            cmd.execute(area);
        } else if (cmd.getCurrentCMD() != CommandMap.DRAWLINE) {
            cmd.execute(area.getRectangle());
        }
        w.drawToolTip = false;
        w.repaint();

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = (int) ((e.getX() - w.getxOffSet()) / w.getScale());
        int y = (int) ((e.getY() - w.getyOffSet()) / w.getScale());
        
        area.udpate(x, y);
        if (cmd.getCurrentCMD() == CommandMap.DRAWLINE) {
            cmd.execute(area);
        }
        w.repaint();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public Area getArea() {
        return area;
    }
    
    

}
