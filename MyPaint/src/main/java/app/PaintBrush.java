package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class PaintBrush {

    private static final int MAX_WIDTH = 30;
    private Graphics2D graphics;

    public PaintBrush(Graphics2D g) {
        this.graphics = g;
        setWidth(10);
        setCurrentColor(Color.black);
        setOverride(true);
    }

    public PaintBrush(Graphics2D g, int width, boolean override) {
        this.graphics = g;
        setWidth(width);
        
        setCurrentColor(Color.black);
        setOverride(override);
    }

    public void setCurrentColor(Color color) {
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
        AlphaComposite composite;
        
        if (override) {
            composite = AlphaComposite.getInstance(AlphaComposite.SRC, graphics.getColor().getAlpha() / 255f);

        } else {
            composite = AlphaComposite.getInstance(AlphaComposite.DST_OVER, graphics.getColor().getAlpha() / 255f);
        }
         graphics.setComposite(composite);
    }

}
