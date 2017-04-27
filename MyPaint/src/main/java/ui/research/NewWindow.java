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
        requestFocus();
    }

    private void setComponents() {
        MouseGuy m = new MouseGuy(cu);

        JTextArea t = getCoordInfoArea(m);
        PaintPanel pan = new PaintPanel(cu, m.getToolTip());
        m.setPaintArea(pan);

        setLayout(new BorderLayout());
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

    

    public JTextArea getCoordInfoArea(MouseGuy m) {
        JTextArea t = new JTextArea();
        t.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight());
        t.setLineWrap(true);
        m.setA(t);
        t.setEditable(false);
        return t;
    }

}
