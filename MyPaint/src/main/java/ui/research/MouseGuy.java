package ui.research;

import app.ControlUnit;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import tools.Area;

public class MouseGuy implements MouseListener, MouseMotionListener {

    private JTextArea a;
    private Component p;
    private ControlUnit cu;

    public MouseGuy(ControlUnit cu) {

        this.cu = cu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        cu.execute(new Area(e.getX(), e.getY()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getPoint());
        a.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight() + "      x:" + e.getX() + " y:" + e.getY());
        p.repaint();

    }

    public void setA(JTextArea a) {
        this.a = a;
    }

    public void setBoard(Component p) {
        this.p = p;
    }

}
