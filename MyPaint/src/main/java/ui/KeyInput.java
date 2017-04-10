package ui;

import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class KeyInput implements KeyListener {

    private final ControlUnit cmd;
    private final MyWindow w;

    public KeyInput(ControlUnit cmd, MyWindow w) {
        this.cmd = cmd;
        this.w = w;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                cmd.getImg().undo();
                w.repaint();
                break;
            case KeyEvent.VK_X:
                cmd.getImg().redo();
                w.repaint();
                break;
                
                
            case KeyEvent.VK_SUBTRACT:
                cmd.getImg().thin();
                break;
            case KeyEvent.VK_ADD:
                cmd.getImg().thicken();
                break;

            case KeyEvent.VK_F1:
                w.setScale(w.getScale() - 0.5f);
                w.repaint();
                break;
            case KeyEvent.VK_F2:
                w.setScale(w.getScale() + 0.5f);
                w.repaint();
                break;
            case KeyEvent.VK_LEFT:
                w.moveOffSet(-10, 0);
                w.repaint();
                break;
            case KeyEvent.VK_RIGHT:
                w.moveOffSet(10, 0);
                w.repaint();
                break;
            case KeyEvent.VK_UP:
                w.moveOffSet(0, -10);
                w.repaint();
                break;
            case KeyEvent.VK_DOWN:
                w.moveOffSet(0, 10);
                w.repaint();
                break;
            case KeyEvent.VK_1:
                cmd.getImg().setColor(Color.black);
                break;
            case KeyEvent.VK_2:
                cmd.getImg().setColor(Color.white);
                break;
            case KeyEvent.VK_3:
                Color c = new Color(1, 0, 0, 0f);
                cmd.getImg().setColor(c);
                break;
            case KeyEvent.VK_4:
                c = new Color(1, 0, 0, 0.5f);
                cmd.getImg().setColor(c);
                break;
            case KeyEvent.VK_5:
                Random r = new Random();
                c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), Math.min(1f, r.nextFloat() * 3));
                cmd.getImg().setColor(c);
                break;
            case KeyEvent.VK_F:
                cmd.getImg().setOverride(false);
                break;
            case KeyEvent.VK_G:
                cmd.getImg().setOverride(true);
                break;

            case KeyEvent.VK_Q:
                cmd.setActiveCMD(CommandMap.DRAWLINE);
                w.drawToolTip = false;
                break;

            case KeyEvent.VK_W:
                cmd.setActiveCMD(CommandMap.DRAWRECT);
                w.drawToolTip = true;
                break;
            case KeyEvent.VK_E:
                cmd.setActiveCMD(CommandMap.FILLRECT);
                w.drawToolTip = true;
                break;
            case KeyEvent.VK_R:
                cmd.setActiveCMD(CommandMap.PICKCOLOR);
                w.drawToolTip = true;
                break;
            case KeyEvent.VK_T:
                cmd.setActiveCMD(CommandMap.FILLCOLOR);
                w.drawToolTip = false;
                break;
            case KeyEvent.VK_Y:
                cmd.setActiveCMD(CommandMap.REPLACECOLOR);
                w.drawToolTip = false;
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
