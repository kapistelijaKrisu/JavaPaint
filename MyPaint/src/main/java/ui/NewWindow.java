package ui;

import ui.buttonPanels.SwapPanel;
import ui.io.KeyGuy;
import ui.io.MouseGuy;
import app.ControlUnit;
import java.awt.*;
import javax.swing.*;

public class NewWindow extends JFrame {

    private final ControlUnit cu;
    MouseGuy m;
    KeyGuy k;
    PaintPanel pan;
    SwapPanel container;
    InfoPanel t;

    public NewWindow(ControlUnit cu, int preferredWidth, int preferredHeight) {
        this.cu = cu;
        installFrame(preferredWidth, preferredHeight);
     
    }

    private void installFrame(int width, int height) {
        setTitle("LePaint v1.0");
        setComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setMaximumSize(new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight()));
        setMinimumSize(new Dimension(800, 400));
        setPreferredSize(new Dimension(width, height));
        revalidate();
        pack();
        setVisible(true);
        requestFocus();
    }

    private void setComponents() {
        m = new MouseGuy(cu);

        
      
        pan = new PaintPanel(cu, m.getToolTip());
        t = new InfoPanel(cu.getImg(), pan);
        m.setInfo(t);
        m.setBoard(pan);
        setLayout(new BorderLayout());
        
        JScrollPane scroll = getPaintBoard();
        getContentPane().add(scroll, BorderLayout.CENTER);
        m.setBoard(pan);
        getContentPane().add(t, BorderLayout.SOUTH);
        getContentPane().add(new SwapPanel(this, cu, m, pan, 255, pan.getHeight()), BorderLayout.EAST);

        k = new KeyGuy(cu, pan, this);

        addKeyListener(k);
    }

    private JScrollPane getPaintBoard() {

        JScrollPane scroll = new JScrollPane(pan);
        scroll.setWheelScrollingEnabled(true);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        pan.setBackground(Color.white);
        pan.addMouseListener(m);
        pan.addMouseMotionListener(m);
        scroll.addMouseListener(m);
        return scroll;
    }

    public void refresh() {
        repaint();
        pan.repaint();
        t.refresh();
    }

}
