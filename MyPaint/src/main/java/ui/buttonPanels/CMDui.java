package ui.buttonPanels;

import ui.io.MouseGuy;
import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import ui.TheWindow;
import ui.PaintPanel;

public class CMDui extends JPanel {

    ControlUnit cu;
    MouseGuy m;
    TheWindow w;

    public CMDui(TheWindow w, ControlUnit cu, MouseGuy m, int width, int height) {
        this.cu = cu;
        this.m = m;
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
        add(getBrushSwapButton());
        add(getImageSettingSwapButton());
        add(getDrawButton());
        add(getDrawLineButton());
        add(getFillRectButton());
        add(getDrawRectButton());
        add(getFillOvalButton());
        add(getDrawOvalButton());
        add(getFillColorButton());
        add(getReplaceColorButton());

    }

    private JButton getDrawButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWLINE);
            m.setRefreshMode(MouseGuy.UPDATE_CONSTANT);
            w.getPaintPanel().setToolBarMode(PaintPanel.NO_TOOLTIP);
            w.getInfo().getCmdInfo().setText("Brush");
            w.refresh();
            w.requestFocusInWindow();
        };

        JButton b = new JButton("DRAW");
        b.addActionListener(a);
        return b;
    }

    private JButton getFillRectButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLRECT);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.RECT);
            w.getInfo().getCmdInfo().setText("Fill Rect");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("FILL RECT");
        b.addActionListener(a);
        return b;
    }

    private JButton getDrawOvalButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWELLIPSE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.ELLIPSE);
            w.getInfo().getCmdInfo().setText("Draw Ellipse");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("DRAW ELLIPSE");
        b.addActionListener(a);
        return b;
    }

    private JButton getFillOvalButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLELLIPSE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.ELLIPSE);
            w.getInfo().getCmdInfo().setText("Fill Ellipse");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("FILL ELLIPSE");
        b.addActionListener(a);
        return b;
    }

    private JButton getDrawRectButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWRECT);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.RECT);
            w.getInfo().getCmdInfo().setText("Draw Rect");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("DRAW RECT");
        b.addActionListener(a);
        return b;
    }

    private JButton getDrawLineButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.DRAWLINE);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.LINE);
            w.getInfo().getCmdInfo().setText("Draw Line");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("DRAW LINE");
        b.addActionListener(a);
        return b;
    }

    private JButton getFillColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.FILLCOLOR);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.NO_TOOLTIP);
            w.getInfo().getCmdInfo().setText("Fill breadth search");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("FILL COLOR");
        b.addActionListener(a);
        return b;
    }

    private JButton getReplaceColorButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.REPLACECOLOR);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
            w.getPaintPanel().setToolBarMode(PaintPanel.NO_TOOLTIP);
            w.getInfo().getCmdInfo().setText("Replace all of Cclor");
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("REPLACE COLOR");
        b.addActionListener(a);
        return b;
    }

    private JButton getBrushSwapButton() {
        ActionListener a = (ActionEvent e) -> {
            w.getOptionPanel().showBrushPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Brush Settings");
        b.addActionListener(a);

        return b;
    }

    private JButton getImageSettingSwapButton() {
        ActionListener a = (ActionEvent e) -> {
            w.getOptionPanel().showImgControlPanel();
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Image Settings");
        b.addActionListener(a);

        return b;
    }
}
