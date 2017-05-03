package ui.io;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import tools.TwoPoint;
import ui.PaintPanel;
import ui.buttonPanels.Refreshable;

public class MouseGuy implements MouseListener, MouseMotionListener, Refreshable {

    public static final int UPDATE_CONSTANT = 2;
    public static final int UPDATE_ONRELEASE = 3;

    private PaintPanel p;
    private final ControlUnit cu;

    private int refreshMode = 2;
    TwoPoint usageArea;  
    TwoPoint toolTip;
    
    private final Random r;
    private ArrayList<Refreshable> refreshOnClick;

    public MouseGuy(ControlUnit cu) {
        //  int x = (int) ((e.getX() - w.getxOffSet()) / w.getScale());
        //    int y = (int) ((e.getY() - w.getyOffSet()) / w.getScale());
        r = new Random();
        this.cu = cu;
        usageArea = new TwoPoint(0, 0);
        toolTip = new TwoPoint(0, 0);
        refreshOnClick = new ArrayList<>();

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
            refresh();
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
            refresh();
        }
        toolTip.udpateCurrents(x, y);      
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = (int) (e.getX() / p.getScale());
        int y = (int) (e.getY() / p.getScale());
        toolTip.udpateCurrents(x, y);
        refresh();
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

    public void addRefreshOnClick(Refreshable r) {
        refreshOnClick.add(r);
    }
    
    @Override
    public void refresh() {
        refreshOnClick.forEach((refreshable) -> {
            refreshable.refresh();
        });
    }
}
