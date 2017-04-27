package ui.research;

import app.ControlUnit;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import tools.Area;

public class MouseGuy implements MouseListener, MouseMotionListener {

    public static final int MODE_RECTANGLE = 1;
    public static final int MODE_BASIC = 0;

    public static final int UPDATE_CONSTANT = 2;
    public static final int UPDATE_ONRELEASE = 3;

    private JTextArea textArea;
    private PaintPanel p;
    private ControlUnit cu;

    private int areaMode = 0;
    private int refreshMode = 2;
    Area a;
    Area toolTip;
    private Random r;
    

    public MouseGuy(ControlUnit cu) {
        r = new Random();
        this.cu = cu;
        a = new Area(0, 0);
        toolTip = new Area(0, 0);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        a.set(e.getX(), e.getY());
        toolTip.set(e.getX(), e.getY());
        if (refreshMode == UPDATE_CONSTANT) {         
            giveTask();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            Color c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), Math.min(1f, r.nextFloat() * 2));
                cu.getImg().setColor(c);
        }
        finish(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (refreshMode == UPDATE_ONRELEASE) {          
            a.udpate(e.getX(), e.getY());
            giveTask();
        }
        p.pauseToolTip();
        
        finish(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        textArea.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight() + "      x:" + e.getX() + " y:" + e.getY());
            
        if (refreshMode == UPDATE_CONSTANT) {   
            a.udpate(e.getX(), e.getY());
            giveTask();
        }
        toolTip.udpateCurrents(e.getX(), e.getY());
        finish(e);
    }

    private void giveTask() {
        if (areaMode == MODE_BASIC) {
            cu.execute(a);
        } else if (areaMode == MODE_RECTANGLE) {
            cu.execute(a.getRectangle());
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        textArea.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight() + "      x:" + e.getX() + " y:" + e.getY());        
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

    public void setAreaMode(int areaMode) {
        if (areaMode != 0 && areaMode != 1) {
            throw new IllegalArgumentException();
        }
        this.areaMode = areaMode;
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
