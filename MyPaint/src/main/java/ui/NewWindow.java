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
    NewWindow w;
    JTextArea t;

    public NewWindow(ControlUnit cu, int preferredWidth, int preferredHeight) {
        this.cu = cu;
        installFrame(preferredWidth, preferredHeight);
    }

    private void installFrame(int width, int height) {
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

        t = getCoordInfoArea(m);
        pan = new PaintPanel(cu, m.getToolTip());
        m.setPaintArea(pan);

        setLayout(new BorderLayout());
        add("Center", getPaintBoard());
        add(t, BorderLayout.SOUTH);
        add(new SwapPanel(this, cu, m, k, pan, 255, cu.getImg().getImg().getHeight()), BorderLayout.EAST);

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

        m.setBoard(pan);

        Dimension dim = new Dimension(cu.getImg().getImg().getWidth(), cu.getImg().getImg().getHeight());
        pan.setPreferredSize(dim);

        return scroll;
    }

    public JTextArea getCoordInfoArea(MouseGuy m) {
        JTextArea t = new JTextArea();
        t.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight());
        t.setLineWrap(true);
        m.setA(t);
        t.setEditable(false);
        return t;
    }

}
