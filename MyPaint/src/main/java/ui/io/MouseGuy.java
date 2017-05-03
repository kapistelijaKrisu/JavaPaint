package ui.io;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import tools.TwoPoint;
import ui.NewWindow;
import ui.PaintPanel;
import ui.tools.Refreshable;

public class MouseGuy implements MouseListener, MouseMotionListener {

    public static final int UPDATE_CONSTANT = 2;
    public static final int UPDATE_ONRELEASE = 3;

    private NewWindow w;
    private PaintPanel p;
    private final ControlUnit cu;

    private int refreshMode = 2;
    TwoPoint usageArea;  
    TwoPoint toolTip;
    
    private final Random r;

    public MouseGuy(ControlUnit cu, NewWindow w) {
        //  int x = (int) ((e.getX() - w.getxOffSet()) / w.getScale());
        //    int y = (int) ((e.getY() - w.getyOffSet()) / w.getScale());
        r = new Random();
        this.cu = cu;
        this.w = w;
        usageArea = new TwoPoint(0, 0);
        toolTip = new TwoPoint(0, 0);

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());

        p.resumeToolTip();
        usageArea.setAll(x, y);
        toolTip.setAll(x, y);
        if (refreshMode == UPDATE_CONSTANT) {
            cu.execute(usageArea);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            Color c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat());
            cu.getImg().setColor(c);

        }
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        cu.setLogging(true);
        p.pauseToolTip();

        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());
        if (refreshMode == UPDATE_ONRELEASE) {
            usageArea.udpate(x, y);
            cu.execute(usageArea);
            w.refresh();
        }    
        e.consume();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());
        if (refreshMode == UPDATE_CONSTANT) {
            cu.setLogging(false);
            usageArea.udpate(x, y);
            cu.execute(usageArea);
            w.refresh();
        }
        toolTip.udpateCurrents(x, y);   
        w.getPaintPanel().repaint();
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());
        toolTip.udpateCurrents(x, y);
        w.refresh();
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

    public void setBoard(PaintPanel p) {
        this.p = p;
    }

    public void setRefreshMode(int refreshMode) {
        if (refreshMode != 2 && refreshMode != 3) {
            throw new IllegalArgumentException();
        }
        this.refreshMode = refreshMode;
    }

    public TwoPoint getToolTip() {
        return toolTip;
    }
}
