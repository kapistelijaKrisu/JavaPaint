package ui.research;

import app.ControlUnit;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import tools.Area;
import ui.BackGroundCreator;

public class PaintPanel extends JPanel {

    public static final int NO_TOOLTIP = 0;
    private static final int DELAY = -1;
    public static final int LINE = 1;
    public static final int RECT = 2;

    private ControlUnit cu;
    private BufferedImage bg;
    private int toolBarMode;
    private int previousMode;
    private Area toolTip;

    public PaintPanel(ControlUnit cu, Area toolTip) {
        this.cu = cu;
        bg = BackGroundCreator.create(cu.getImg().getImg().getWidth(), cu.getImg().getImg().getHeight());
        toolBarMode = 0;
        this.toolTip = toolTip;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
        g.drawImage(cu.getImg().getImg(), 0, 0, null);

        if (toolBarMode == LINE) {
            g.drawLine(toolTip.getStartX(), toolTip.getStartY(), toolTip.getCurX(), toolTip.getCurY());
        } else if (toolBarMode == RECT) {
            Area r = toolTip.getRectangle();
            g.drawRect(r.getStartX(), r.getStartY(), r.getCurX(), r.getCurY());
        }
        if (toolBarMode == DELAY) {
            toolBarMode = previousMode;
        }
        
    }

    public void setToolBarMode(int toolBarMode) {
        this.toolBarMode = toolBarMode;
    }
    
    public void pauseToolTip() {
        previousMode = toolBarMode;
        toolBarMode = DELAY;
    }
    
    
}
