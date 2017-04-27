package ui.research;

import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class SidePanel extends JPanel {

    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    NewWindow w;

    public SidePanel(NewWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
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
        
        add(getSetColorButton());
        add(getSaveButton());
        add(getLoadButton());

    }

    public JButton getDrawButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWLINE);
            m.setAreaMode(MouseGuy.MODE_BASIC);
            m.setRefreshMode(MouseGuy.UPDATE_CONSTANT);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);
            
            
        };

        JButton b = new JButton("DRAW");

        b.addActionListener(a);
        return b;
    }

    public JButton getFillRectButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLRECT);
            m.setAreaMode(MouseGuy.MODE_RECTANGLE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.RECT);
        };
        JButton b = new JButton("FILL RECT");
        b.addActionListener(a);
        return b;
    }

    public JButton getDrawRectButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWRECT);
            m.setAreaMode(MouseGuy.MODE_RECTANGLE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.RECT);
        };
        JButton b = new JButton("DRAW RECT");
        b.addActionListener(a);
        return b;
    }

    public JButton getDrawLineButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWLINE);
            m.setAreaMode(MouseGuy.MODE_BASIC);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.LINE);

        };
        JButton b = new JButton("DRAW LINE");
        b.addActionListener(a);
        return b;
    }

    public JButton getFillColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLCOLOR);
            m.setAreaMode(MouseGuy.MODE_BASIC);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);

        };
        JButton b = new JButton("FILL COLOR");
        b.addActionListener(a);
        return b;
    }

    public JButton getReplaceColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.REPLACECOLOR);
            m.setAreaMode(MouseGuy.MODE_BASIC);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);

        };
        JButton b = new JButton("REPLACE COLOR");
        b.addActionListener(a);
        return b;
    }

    public JButton getSetAvgColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.PICKCOLOR);
            m.setAreaMode(MouseGuy.MODE_RECTANGLE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            p.setToolBarMode(PaintPanel.NO_TOOLTIP);

        };
        JButton b = new JButton("SET AVG COLOR");
        b.addActionListener(a);
        return b;
    }

    public JButton getSetColorButton() {
        ActionListener a = (ActionEvent e) -> {
       //     JPopupMenu
        };
        JButton b = new JButton("SET COLOR");
        b.addActionListener(a);
        return b;
    }
    public JButton getSaveButton() {
        ActionListener a = (ActionEvent e) -> {
       //     JPopupMenu
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
