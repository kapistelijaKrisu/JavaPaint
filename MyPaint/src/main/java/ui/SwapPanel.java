package ui;

import ui.tools.Refreshable;
import ui.io.MouseGuy;
import app.ControlUnit;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import ui.buttonPanels.*;

public class SwapPanel extends JPanel implements Refreshable {

    private static final String BRUSH = "BRUSH", CMD = "CMD", IMAGE = "IMAGE";
    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    TheWindow w;

    private CMDui cmdUi;
    private BrushUi brushUi;
    private ImageControlUI imgUi;

    public SwapPanel(TheWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, int width, int height) {
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

        cmdUi = new CMDui(w, cu, m, width, height);
        brushUi = new BrushUi(w, cu, m, width, height);
        imgUi = new ImageControlUI(w, cu, m, width, height);
        add(cmdUi, CMD);
        add(brushUi, BRUSH);
        add(imgUi, IMAGE);
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

    @Override
    public void refresh() {
  
        brushUi.refresh();
        imgUi.refresh();
    }

    public CMDui getCmdUi() {
        return cmdUi;
    }

    public BrushUi getBrushUi() {
        return brushUi;
    }

    public ImageControlUI getImgUi() {
        return imgUi;
    }
    
    

}
