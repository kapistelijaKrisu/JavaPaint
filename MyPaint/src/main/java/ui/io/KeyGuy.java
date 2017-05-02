package ui.io;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import ui.NewWindow;
import ui.PaintPanel;

public class KeyGuy extends KeyAdapter {

    private final ControlUnit cmd;
    private final NewWindow w;
    private final PaintPanel p;
    private final Random r = new Random();

    public KeyGuy(ControlUnit cmd, PaintPanel p, NewWindow w) {
        this.cmd = cmd;
        this.w = w;
        this.p = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                Color c = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat(), Math.min(1f, r.nextFloat() * 3));
                cmd.getImg().setColor(c);
                break;

            case KeyEvent.VK_Z:
                cmd.undo();
                w.repaint();
                break;
            case KeyEvent.VK_V:
                cmd.redo();
                w.repaint();
                break;

            case KeyEvent.VK_F4:
                cmd.getImg().thin();
                break;
            case KeyEvent.VK_F5:
                cmd.getImg().thicken();
                break;
            case KeyEvent.VK_F1:
                p.setScale(p.getScale() * 0.75f);
                w.repaint();
                break;
            case KeyEvent.VK_F2:
                p.setScale(p.getScale() * 1.5f);
                w.repaint();
                break;
            case KeyEvent.VK_7:
                cmd.getImg().setOverride(false);
                break;
            case KeyEvent.VK_8:
                cmd.getImg().setOverride(true);
                break;
        }
        w.revalidate();
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
