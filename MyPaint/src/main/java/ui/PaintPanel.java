package ui;

import app.MyImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import tools.TwoPoint;
import ui.tools.BackGroundCreator;

public class PaintPanel extends JPanel implements Scrollable {

    public static final int NO_TOOLTIP = 0;
    public static final int LINE = 1;
    public static final int RECT = 2;
    public static final int ELLIPSE = 3;
    private static final int XTRA_SPACE = 50;
    private int toolBarMode;
    private int previousMode;
    
    private final float MINSCALE = 0.1f, MAXSCALE = 32.0f;
    private float scale;

    private MyImage image;
    private BufferedImage backg; 
    private TwoPoint toolTip;

    

    public PaintPanel(MyImage image, TwoPoint toolTip) {
        this.image = image;
        backg = BackGroundCreator.create(image.getImg().getWidth(), image.getImg().getHeight(), 10);
        toolBarMode = 0;
        this.toolTip = toolTip;
        this.scale = 1.0f;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
           Dimension imgSize = getImageSize();
        imgSize.width = image.getImg().getWidth();
        imgSize.height = image.getImg().getHeight();
        
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale / 1, scale / 1);
        if (backg.getWidth() != imgSize.width || backg.getHeight() != imgSize.height) {
            backg = BackGroundCreator.create(imgSize.width, imgSize.height, 10);
        }
        g.drawImage(backg, 0, 0, null);
        g.drawImage(image.getImg(), 0, 0, null);

        if (toolBarMode == LINE) {
            g.drawLine(toolTip.getPrevX(), toolTip.getPrevY(), toolTip.getCurX(), toolTip.getCurY());
        } else if (toolBarMode == RECT) {
            Rectangle r = toolTip.getRectangle();
            g.drawRect(r.x, r.y, r.width, r.height);
        } else if (toolBarMode == ELLIPSE) {
            Rectangle r = toolTip.getRectangle();
            g.drawOval(r.x, r.y, r.width, r.height);
        }

    }

    private Dimension getImageSize() {
        return new Dimension(image.getImg().getWidth(), image.getImg().getHeight());
    }
    
    public void setToolBarMode(int toolBarMode) {

        this.toolBarMode = toolBarMode;
        previousMode = toolBarMode;
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
        scale = Math.min(MAXSCALE, scale);
        scale = Math.max(MINSCALE, scale);
        this.scale = scale;
        
       
    }
    @Override
    public Dimension getPreferredSize() {
        Dimension d = getImageSize();
        d.width *= scale;
        d.height *= scale;
        d.width += XTRA_SPACE;
        d.height+= XTRA_SPACE;
        return d;
    }

@Override
    public Dimension getMinimumSize() {
        Dimension min = getImageSize();
        min.height /= 2;
        min.width /= 2;
        return min;
    }    

    @Override
    public Dimension getPreferredScrollableViewportSize() {
       Dimension d = getImageSize();
        d.width += XTRA_SPACE;
        d.height+= XTRA_SPACE;
        return d;
        
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 80;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 80;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

}
