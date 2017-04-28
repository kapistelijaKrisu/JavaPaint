package ui.io;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JTextArea;
import tools.Area;
import ui.PaintPanel;

public class MouseGuy implements MouseListener, MouseMotionListener {

    public static final int MODE_RECTANGLE = 1;
    public static final int MODE_BASIC = 0;

    public static final int UPDATE_CONSTANT = 2;
    public static final int UPDATE_ONRELEASE = 3;

    private JTextArea textArea;
    private PaintPanel p;
    private final ControlUnit cu;

    private int refreshMode = 2;
    Area a;
    Area toolTip;
    private final Random r;

    public MouseGuy(ControlUnit cu) {
        //  int x = (int) ((e.getX() - w.getxOffSet()) / w.getScale());
        //    int y = (int) ((e.getY() - w.getyOffSet()) / w.getScale());
        r = new Random();
        this.cu = cu;
        a = new Area(0, 0);
        toolTip = new Area(0, 0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());

        p.resumeToolTip();
        a.setAll(x, y);
        toolTip.setAll(x, y);
        if (refreshMode == UPDATE_CONSTANT) {
            cu.execute(a);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            Color c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), Math.min(1f, r.nextFloat() * 2));
            cu.getImg().setColor(c);
        }
        finish(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        cu.setLogging(true);
        p.pauseToolTip();

        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());
        if (refreshMode == UPDATE_ONRELEASE) {
            a.udpate(x, y);
            cu.execute(a);
        }

        finish(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        textArea.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight() + "      x:" + e.getX() + " y:" + e.getY());
        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());
        if (refreshMode == UPDATE_CONSTANT) {
            cu.setLogging(false);
            a.udpate(x, y);
            cu.execute(a);
        }
        toolTip.udpateCurrents(x, y);
        finish(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        textArea.setText(
                "width:" + cu.getImg().getImg().getWidth()
                + " height: " + cu.getImg().getImg().getHeight()
                + "      x:"
                + Math.min((int) (e.getX() / p.getScale()), cu.getImg().getImg().getWidth())
                + " y:" + Math.min((int) (e.getY() / p.getScale()), cu.getImg().getImg().getHeight()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setA(JTextArea a) {
        this.textArea = a;
    }

    public void setBoard(PaintPanel p) {
        this.p = p;
    }

    private void finish(MouseEvent e) {
        e.consume();
        p.repaint();
    }

    public void setRefreshMode(int refreshMode) {
        if (refreshMode != 2 && refreshMode != 3) {
            throw new IllegalArgumentException();
        }
        this.refreshMode = refreshMode;
    }

    public Area getToolTip() {
        return toolTip;
    }

    public void setPaintArea(PaintPanel p) {
        this.p = p;

    }

}
