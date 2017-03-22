package UI;

import app.ControlUnit;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.scene.input.KeyCode;

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
            case KeyEvent.VK_0:
                cmd.getColors().setOverride(false);
                break;
            case KeyEvent.VK_9:
                cmd.getColors().setOverride(true);
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
