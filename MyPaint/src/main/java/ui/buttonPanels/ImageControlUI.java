package ui.buttonPanels;

import ui.NewWindow;
import ui.io.MouseGuy;
import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ui.NewWindow;
import ui.PaintPanel;

public class ImageControlUI extends JPanel {

    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    SwapPanel container;
    NewWindow w;

    public ImageControlUI(NewWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, SwapPanel container, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
        this.container = container;
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setBackground(Color.green);
        setLayout(new GridLayout(10, 1, 1, 1));
        addbuttons();

        revalidate();

    }

    public void addbuttons() {
        add(getSwapperButton());
        add(getSwapper2Button());
        BufferedImage img = cu.getImg().getImg();

        JSizePanel sp = new JSizePanel(0, 0, img.getWidth(), img.getHeight());
        add(sp);
        add(setSizePanel(sp));
        
        
    }

    public JButton getThickenBrushButton() {
        ActionListener a = (ActionEvent e) -> {

            w.requestFocusInWindow();
        };
        JButton b = new JButton("asd");
        b.addActionListener(a);
        return b;
    }

    public JButton getSwapperButton() {
        ActionListener a = (ActionEvent e) -> {
            container.showCMDPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Draw Settings");
        b.addActionListener(a);

        return b;
    }

    public JButton getSwapper2Button() {
        ActionListener a = (ActionEvent e) -> {
            container.showBrushPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Brush Settings");
        b.addActionListener(a);

        return b;
    }

    public JButton setSizePanel(JSizePanel p) {
        ActionListener a = (ActionEvent e) -> {
            try {
                int x = Integer.parseInt(p.startX.getText());
                int y = Integer.parseInt(p.startY.getText());
                int width = Integer.parseInt(p.width.getText());
                int height = Integer.parseInt(p.height.getText());

                BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g = resized.createGraphics();
                g.drawImage(cu.getImg().getImg(), x, y, null);
                cu.setImage(resized);

                w.revalidate();
                w.repaint();
            } catch (IllegalArgumentException ex) {
                p.startX.setText("" + 0);
                p.startY.setText("" + 0);
                p.width.setText("" + cu.getImg().getImg().getWidth());
                p.height.setText("" + cu.getImg().getImg().getHeight());
            }
            w.requestFocusInWindow();
        };
        JButton b = new JButton("APPLY");

        b.addActionListener(a);
        return b;
    }

    private class JSizePanel extends JPanel {

        JTextField startX;
        JTextField startY;
        JTextField width;
        JTextField height;

        public JSizePanel(int x, int y, int w, int h) {
            setLayout(new GridLayout(4, 2));

            startX = new JTextField("" + x);
            startY = new JTextField("" + y);
            width = new JTextField("" + w);
            height = new JTextField("" + h);

            JLabel xl = new JLabel("startX:");
            xl.setLabelFor(startX);
            JLabel xll = new JLabel("startY:");
            xll.setLabelFor(startY);

            JLabel xlll = new JLabel("width:");
            xlll.setLabelFor(startX);
            JLabel xllll = new JLabel("height:");
            xllll.setLabelFor(startY);

            add(xl);
            add(startX);
            add(xll);
            add(startY);

            add(xlll);
            add(width);
            add(xllll);
            add(height);
        }
    }
    
    
}
