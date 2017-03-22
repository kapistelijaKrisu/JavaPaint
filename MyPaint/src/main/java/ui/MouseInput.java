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
        cmd.execute(area);
        window.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        cmd.execute(area);
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
        cmd.execute(area);
        window.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
