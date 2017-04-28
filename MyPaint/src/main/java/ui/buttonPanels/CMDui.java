
package ui.buttonPanels;
        
import ui.NewWindow;
import ui.io.MouseGuy;
import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import ui.NewWindow;
import ui.PaintPanel;

public class CMDui extends JPanel {

    ControlUnit cu;
    MouseGuy m;
 
    PaintPanel p;
    SwapPanel container;
    NewWindow w;

    public CMDui(NewWindow w, ControlUnit cu, MouseGuy m,PaintPanel p, SwapPanel container, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
        this.container = container;
      //  addKeyListener(k);
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setBackground(Color.red);
        setLayout(new GridLayout(10, 1, 1, 1));
        addbuttons();

        revalidate();
        repaint();

    }

    public void addbuttons() {
        add(getDrawButton());
        add(getDrawLineButton());
        add(getFillRectButton());
        add(getDrawRectButton());
        add(getFillColorButton());
        add(getReplaceColorButton());
        add(getSetAvgColorButton());

        add(getSwapperButton());
        add(getSaveButton());
        add(getLoadButton());

    }

    public JButton getDrawButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWLINE);
            m.setRefreshMode(MouseGuy.UPDATE_CONSTANT);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);
            w.requestFocusInWindow();

        };

        JButton b = new JButton("DRAW");
      

        b.addActionListener(a);
        return b;
    }

    public JButton getFillRectButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLRECT);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.RECT);
            w.requestFocusInWindow();
        };
        JButton b = new JButton("FILL RECT");
        b.addActionListener(a);
        return b;
    }

    public JButton getDrawRectButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWRECT);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.RECT);
            w.requestFocusInWindow();
        };
        JButton b = new JButton("DRAW RECT");
        b.addActionListener(a);
        return b;
    }

    public JButton getDrawLineButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWLINE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.LINE);
            w.requestFocusInWindow();
        };
        JButton b = new JButton("DRAW LINE");
        b.addActionListener(a);
        return b;
    }

    public JButton getFillColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLCOLOR);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);
            w.requestFocusInWindow();
        };
        JButton b = new JButton("FILL COLOR");
        b.addActionListener(a);
        return b;
    }

    public JButton getReplaceColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.REPLACECOLOR);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);
            w.requestFocusInWindow();
        };
        JButton b = new JButton("REPLACE COLOR");
        b.addActionListener(a);
        return b;
    }

    public JButton getSetAvgColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.PICKCOLOR);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.RECT);
            w.requestFocusInWindow();
        };
        JButton b = new JButton("SET AVG COLOR");
        b.addActionListener(a);
        return b;
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

    public JButton getSaveButton() {
        ActionListener a = (ActionEvent e) -> {
            //     JPopupMenu
            w.requestFocusInWindow();
        };
        JButton b = new JButton("SAVE");
        b.addActionListener(a);
        return b;
    }

    public JButton getLoadButton() {
        ActionListener a = (ActionEvent e) -> {
            //     JPopupMenu
            w.requestFocusInWindow();
        };
        JButton b = new JButton("LOAD");
        b.addActionListener(a);
        return b;
    }
}
