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
            case KeyEvent.VK_SUBTRACT:
                cmd.getBrush().setWidth(cmd.getBrush().getWidth() - 1);
                cmd.activateSettings(false, false, true);
                break;
            case KeyEvent.VK_ADD:
                cmd.getBrush().setWidth(cmd.getBrush().getWidth() + 1);
                cmd.activateSettings(false, false, true);
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
                cmd.getBrush().setColor(Color.black);
                cmd.activateSettings(true, false, false);
                break;
            case KeyEvent.VK_2:
                cmd.getBrush().setColor(Color.white);
                cmd.activateSettings(true, false, false);
                break;
            case KeyEvent.VK_3:
                Color c = new Color(1, 0, 0, 0f);
                cmd.activateSettings(true, false, false);
                cmd.getBrush().setColor(c);
                break;
            case KeyEvent.VK_4:
                c = new Color(1, 0, 0, 0.5f);
                cmd.activateSettings(true, false, false);
                cmd.getBrush().setColor(c);
                break;
            case KeyEvent.VK_5:
                Random r = new Random();
                c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), Math.min(1f, r.nextFloat() * 3));
                cmd.activateSettings(true, false, false);
                cmd.getBrush().setColor(c);
                break;
            case KeyEvent.VK_F:
                cmd.getBrush().setOverride(false);
                cmd.activateSettings(false, true, false);
                break;
            case KeyEvent.VK_G:
                cmd.getBrush().setOverride(true);
                cmd.activateSettings(false, true, false);
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

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
