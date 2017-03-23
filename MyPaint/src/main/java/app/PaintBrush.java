package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class PaintBrush {

    private static final int MAX_WIDTH = 30;
    private Graphics2D graphics;

    //test members
    private int width, composite;
    private Color color;
    
    public PaintBrush(Graphics2D g, int width, boolean override) {
        if (g == null) {
            throw new IllegalArgumentException();
        }
        this.graphics = g;
        if (!setWidth(width)) {
            throw new IllegalArgumentException();
        }           
        setCurrentColor(Color.black);
        setOverride(override);
    }

    public void setCurrentColor(Color color) {
        graphics.setPaint(color);
        this.color = color;//for tests
       
    }

    public boolean setWidth(int width) {
        if (width > 0 && width <= MAX_WIDTH) {
            graphics.setStroke(new BasicStroke(width));
            this.width = width;//for tests
            return true;
        }
        return false;
    }

    public void setGraphics(Graphics2D graphics) {
        if (graphics == null) {
            throw new IllegalArgumentException();
        }
        this.graphics = graphics;
    }

    public void setOverride(boolean override) {
        AlphaComposite composite;
        
        if (override) {
            composite = AlphaComposite.getInstance(AlphaComposite.SRC, graphics.getColor().getAlpha() / 255f);
            this.composite = AlphaComposite.SRC;//for tests
        } else {
            composite = AlphaComposite.getInstance(AlphaComposite.DST_OVER, graphics.getColor().getAlpha() / 255f);
            this.composite = AlphaComposite.DST_OVER;//for tests
        }
        graphics.setComposite(composite);
    }    
    //for tests

    public Color getColor() {
        return color;
    }

    public int getComposite() {
        return composite;
    }

    public int getWidth() {
        return width;
    }

    public static int getMAX_WIDTH() {
        return MAX_WIDTH;
    }  

    public Graphics2D getGraphics() {
        return graphics;
    }
    
}
