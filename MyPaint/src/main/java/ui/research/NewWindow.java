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
        setShit();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setSize(333, 222);
        pack();
        setVisible(true);
    }

    private void setShit() {
        JToolBar bar = getCustomToolBar();
        MouseGuy m = new MouseGuy(cu);
        JTextArea t = getCoordInfoArea(m);

        setLayout(new BorderLayout());
        add("North", bar);
        add("Center", getPaintPanel(m));
        add(t, BorderLayout.SOUTH);
        add(getSidePanel(), BorderLayout.EAST);
    }

    private JPanel getSidePanel() {
        JPanel p = new JPanel();

        Dimension dim = new Dimension(255, cu.getImg().getImg().getHeight());
        p.setPreferredSize(dim);
        p.setBackground(Color.red);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        return p;
    }

    private Component getPaintPanel(MouseGuy m) {
        PaintPanel pan = new PaintPanel(cu);

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
    bar.setPreferredSize(new Dimension(80,gd.getDisplayMode().getHeight()/40));

    bar.setOpaque(true);

        return bar;
    }

    public JTextArea getCoordInfoArea(MouseGuy m) {
        JTextArea t = new JTextArea();
        t.setText("0");
        t.setLineWrap(true);
        m.setA(t);
        t.setEditable(false);
        return t;
    }

    private class PaintPanel extends JPanel {

        private ControlUnit cu;
        int x = 0;

        public PaintPanel(ControlUnit cu) {
            this.cu = cu;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(x, 3, 33, 33);
            x++;
            g.drawImage(cu.getImg().getImg(), 0, 0, null);
            System.out.println(cu.getImg().getImg());
        }
    }

}
