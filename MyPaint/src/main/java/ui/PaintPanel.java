package ui;

import app.ControlUnit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import tools.Area;
import ui.tools.BackGroundCreator;

public class PaintPanel extends JPanel {

    public static final int NO_TOOLTIP = 0;
    public static final int LINE = 1;
    public static final int RECT = 2;

    private float minScale = 0.1f, maxScale = 32.0f;

    private ControlUnit cu;
    private BufferedImage bg;
    private int toolBarMode;
    private int previousMode;
    private Area toolTip;

    private float scale;

    public PaintPanel(ControlUnit cu, Area toolTip) {
        this.cu = cu;
        bg = BackGroundCreator.create(cu.getImg().getImg().getWidth(), cu.getImg().getImg().getHeight(), 10);

        toolBarMode = 0;
        this.toolTip = toolTip;
        this.scale = 1.0f;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int imgWidth = cu.getImg().getImg().getWidth();
        int imgHeight = cu.getImg().getImg().getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale / 1, scale / 1);
        if (bg.getWidth() != imgWidth || bg.getHeight() != imgHeight) {
            bg = BackGroundCreator.create(imgWidth, imgHeight, 10);
        }
        g.drawImage(bg, 0, 0, null);
        g.drawImage(cu.getImg().getImg(), 0, 0, null);

        if (toolBarMode == LINE) {
            g.drawLine(toolTip.getPrevX(), toolTip.getPrevY(), toolTip.getCurX(), toolTip.getCurY());
        } else if (toolBarMode == RECT) {
            Rectangle r = toolTip.getRectangle();
            g.drawRect(r.x, r.y, r.width, r.height);
        }

    }

    public void setToolBarMode(int toolBarMode) {

        this.toolBarMode = toolBarMode;
        pauseToolTip();

    }

    public void pauseToolTip() {
        if (toolBarMode != NO_TOOLTIP) {
            previousMode = toolBarMode;
            toolBarMode = NO_TOOLTIP;
        }
    }

    public void resumeToolTip() {
        toolBarMode = previousMode;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        scale = Math.min(maxScale, scale);
        scale = Math.max(minScale, scale);
        this.scale = scale;
    }

}
