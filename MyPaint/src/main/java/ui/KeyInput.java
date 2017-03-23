package ui;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private ControlUnit cmd;

    public KeyInput(ControlUnit cmd) {
        this.cmd = cmd;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                cmd.getColors().setCurrentColor(Color.black);
                break;
            case KeyEvent.VK_2:
                cmd.getColors().setCurrentColor(Color.white);
                break;
            case KeyEvent.VK_3:
                Color c = new Color(1, 0, 0, 0f);
                cmd.getColors().setCurrentColor(c);
                break;
            case KeyEvent.VK_4:
                c = new Color(1, 0, 0, 0.5f);
                cmd.getColors().setCurrentColor(c);
                break;
            case KeyEvent.VK_F:
                cmd.getColors().setOverride(false);
                break;
            case KeyEvent.VK_T:
                cmd.getColors().setOverride(true);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}