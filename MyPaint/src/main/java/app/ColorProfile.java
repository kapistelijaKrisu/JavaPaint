package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class ColorProfile {

    private static final int MAX_WIDTH = 30;
    private Graphics2D graphics;

    public ColorProfile(Graphics2D g) {
        this.graphics = g;
        setCurrentColor(Color.black);
        setWidth(10);
    }

    public void setCurrentColor(Color color) {
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC, color.getAlpha() / 255f);
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
    
    

}
