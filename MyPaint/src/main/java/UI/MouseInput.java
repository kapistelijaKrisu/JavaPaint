
package UI;

import app.Area;
import app.AreaMaker;
import app.ControlUnit;
import app.MyImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    private ControlUnit cmd;
    MyWindow window;

    public MouseInput(ControlUnit cmd, MyWindow window) {
        this.cmd = cmd;
        this.window = window;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
  
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        window.repaint();
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
        Area a = AreaMaker.convert(e.getX(), e.getY(), 10);
        cmd.execute(a);
        window.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
   
    }
    
}
