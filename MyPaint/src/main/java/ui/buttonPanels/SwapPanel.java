package ui.buttonPanels;

import ui.NewWindow;
import ui.io.MouseGuy;
import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.CardLayout;
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

public class SwapPanel extends JPanel {

    private static final String BRUSH = "BRUSH", CMD = "CMD", IMAGE = "IMAGE";
    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    NewWindow w;

    public SwapPanel(NewWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
        //    addKeyListener(k);
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setBackground(Color.red);

        CardLayout cl = new CardLayout();
        setLayout(cl);
        add(new CMDui(w, cu, m, p, this, width, height), CMD);
        add(new BrushUi(w, cu, m, p, this, width, height), BRUSH);
        add(new ImageControlUI(w, cu, m, p, this, width, height), IMAGE);
        cl.show(this, CMD);

        revalidate();

    }

    public void showBrushPanel() {
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, BRUSH);

        w.requestFocusInWindow();
    }

    public void showCMDPanel() {
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, CMD);

        w.requestFocusInWindow();
    }
    public void showImgControlPanel() {
        CardLayout cl = (CardLayout) getLayout();
        cl.show(this, IMAGE);

        w.requestFocusInWindow();
    }

}
