package ui.research;

import app.ControlUnit;
import java.awt.*;
import javax.swing.*;

public class NewWindow extends JFrame {

    private final ControlUnit cu;

    public NewWindow(ControlUnit cu) {
        this.cu = cu;
        installFrame();
    }

    private void installFrame() {
        setComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 300));
        revalidate();
        pack();
        setVisible(true);
    }

    private void setComponents() {
        JToolBar bar = getCustomToolBar();
        MouseGuy m = new MouseGuy(cu);

        JTextArea t = getCoordInfoArea(m);
        PaintPanel pan = new PaintPanel(cu, m.getToolTip());
        m.setPaintArea(pan);

        setLayout(new BorderLayout());
        add("North", bar);
        add("Center", getPaintBoard(m, pan));
        add(t, BorderLayout.SOUTH);
        add(new SidePanel(this, cu, m, pan, 255, cu.getImg().getImg().getHeight()), BorderLayout.EAST);

        KeyGuy k = new KeyGuy(cu, this);
        addKeyListener(k);
    }

    private JScrollPane getPaintBoard(MouseGuy m, PaintPanel pan) {

        JScrollPane scroll = new JScrollPane(pan);
        //scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        pan.setBackground(Color.white);
        pan.addMouseListener(m);
        pan.addMouseMotionListener(m);
        m.setBoard(pan);

        Dimension dim = new Dimension(cu.getImg().getImg().getWidth(), cu.getImg().getImg().getHeight());
        pan.setPreferredSize(dim);

        pan.setAutoscrolls(true);
        return scroll;
    }

    private JToolBar getCustomToolBar() {
        JToolBar bar = new JToolBar();
        bar.setSize(bar.getSize().width, 500);
        ImageIcon image1 = new ImageIcon("button1.gif");
        JButton button1 = new JButton(image1);
        ImageIcon image2 = new ImageIcon("button2.gif");
        JButton button2 = new JButton(image2);
        ImageIcon image3 = new ImageIcon("button3.gif");
        JButton button3 = new JButton(image3);
        bar.add(button1);
        bar.add(button2);
        bar.add(button3);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        bar.setPreferredSize(new Dimension(80, gd.getDisplayMode().getHeight() / 40));

        bar.setOpaque(true);

        return bar;
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
