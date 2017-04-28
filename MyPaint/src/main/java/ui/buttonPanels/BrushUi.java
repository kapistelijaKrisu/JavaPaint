
package ui.buttonPanels;

import ui.NewWindow;
import ui.io.MouseGuy;
import app.ControlUnit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import ui.NewWindow;
import ui.PaintPanel;

public class BrushUi extends JPanel {
    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    SwapPanel container;
    NewWindow w;
    
    public BrushUi(NewWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, SwapPanel container, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
        this.container = container;
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setBackground(Color.red);
        setLayout(new GridLayout(10, 1, 1, 1));
        addbuttons();

        revalidate();
        repaint();

    }

    public void addbuttons() {

        add(getSwapperButton());

    }
    
    public JButton getSwapperButton() {
        ActionListener a = (ActionEvent e) -> {
            container.swap();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("SET COLOR");
        b.addActionListener(a);

        return b;
    }
}
