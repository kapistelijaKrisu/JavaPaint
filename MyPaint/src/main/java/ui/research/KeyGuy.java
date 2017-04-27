package ui.research;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyGuy extends KeyAdapter {

    private final ControlUnit cmd;
    private final NewWindow w;
    private final Random r = new Random();

    public KeyGuy(ControlUnit cmd, NewWindow w) {
        this.cmd = cmd;
        this.w = w;
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
            case KeyEvent.VK_X:
                cmd.redo();
                w.repaint();
                break;

            case KeyEvent.VK_SUBTRACT:
                cmd.getImg().thin();
                break;
            case KeyEvent.VK_ADD:
                cmd.getImg().thicken();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
