package ui;

import ui.tools.Refreshable;
import ui.io.KeyGuy;
import ui.io.MouseGuy;
import app.ControlUnit;
import java.awt.*;
import javax.swing.*;

public class TheWindow extends JFrame implements Refreshable{

    private final ControlUnit cu;
    MouseGuy m;
    KeyGuy k;
    PaintPanel paintPanel;
    SwapPanel options;
    InfoPanel info;

    public TheWindow(ControlUnit cu, int preferredWidth, int preferredHeight) {
        this.cu = cu;
        installFrame(preferredWidth, preferredHeight);
     
    }

    private void installFrame(int width, int height) {
        setTitle("LePaint v1.1");
        setComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setMaximumSize(new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight()));
        setMinimumSize(new Dimension(800, 400));
        setPreferredSize(new Dimension(width, height));
        autoScale();
        revalidate();
        refresh();
        pack();      
        requestFocus();
    }

    private void setComponents() {
        m = new MouseGuy(cu, this);

        paintPanel = new PaintPanel(cu.getImg(), m.getToolTip());
        info = new InfoPanel(cu, paintPanel, m);
        
        m.setBoard(paintPanel);
        setLayout(new BorderLayout());
        
        JScrollPane scroll = getPaintBoard();
        getContentPane().add(scroll, BorderLayout.CENTER);
        m.setBoard(paintPanel);
        getContentPane().add(info, BorderLayout.SOUTH);
        options = new SwapPanel(this, cu, m, paintPanel, 255, paintPanel.getHeight());
        getContentPane().add(options, BorderLayout.EAST);

        k = new KeyGuy(cu, paintPanel, this);
        addKeyListener(k);
    }

    private JScrollPane getPaintBoard() {

        JScrollPane scroll = new JScrollPane(paintPanel);
        scroll.setWheelScrollingEnabled(true);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        paintPanel.setBackground(Color.white);
        paintPanel.addMouseListener(m);
        paintPanel.addMouseMotionListener(m);
        scroll.addMouseListener(m);
        return scroll;
    }

    @Override
    public void refresh() {
        paintPanel.revalidate();
        repaint();
        paintPanel.repaint();
        info.refresh();
        options.refresh();
    }

    public InfoPanel getInfo() {
        return info;
    }

    public SwapPanel getOptionsPanel() {
        return options;
    }

    public void autoScale() {
        paintPanel.setScale(Math.max(1, getPreferredSize().width / cu.getImg().getImg().getWidth() - 1));
    }

    public SwapPanel getOptionPanel() {
        return options;
    }

    public PaintPanel getPaintPanel() {
        return paintPanel;
    }
    
    
    

}
