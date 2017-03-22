package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class ColorProfile {

    private static final int MAX_WIDTH = 30;
    private Graphics2D graphics;
    private int alphaComposite;

    public ColorProfile(Graphics2D g) {
        this.graphics = g;   
        setWidth(10);
        alphaComposite = AlphaComposite.SRC;
        setCurrentColor(Color.black);
    }
    public ColorProfile(Graphics2D g, int width, boolean override) {
        this.graphics = g;    
        setWidth(width);
        setOverride(override);
        setCurrentColor(Color.black);
    }

    public void setCurrentColor(Color color) {
        AlphaComposite composite = AlphaComposite.getInstance(alphaComposite, color.getAlpha() / 255f);
        graphics.setComposite(composite);
        graphics.setPaint(color);
    }
    
    public void setWidth(int width) {
        if (width > 0 && width < MAX_WIDTH) {
            graphics.setStroke(new BasicStroke(width));
        }
    }

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }
    
    public void setOverride(boolean override) {
        if (override) {
            alphaComposite = AlphaComposite.SRC;
        } else {
            alphaComposite = AlphaComposite.SRC_OVER;
        }
    }

}
