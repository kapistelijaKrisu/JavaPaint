package app;

import java.awt.AlphaComposite;
import java.awt.Color;
/**
 * 
 * <p>A container of information for MyImage.</p>
 * <p>Holds width, composite, color values and assures their values are valid</p> 
 */
public final class PaintBrush {

    private static final int MAX_WIDTH = 30;

    private int width, composite;
    private Color color;

    /**
     * 
     * @param width - minimum of 1 and maximum of 30 else throws IllegalArgumentException <br>
     * @param override  - see setOverride
     */
    public PaintBrush(int width, boolean override) {
        if (!setWidth(width)) {
            throw new IllegalArgumentException();
        }
        setColor(Color.black);
        setWidth(width);
        setOverride(override);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 
     * @param width - value to be evaluated<br>
     * @return true if width is legal
     */
    public boolean setWidth(int width) {
        if (width > 0 && width <= MAX_WIDTH) {
            this.width = width;
            return true;
        }
        return false;
    }

    public void setOverride(boolean override) {
        if (override) {
            this.composite = AlphaComposite.SRC;
        } else {
            this.composite = AlphaComposite.DST_OVER;
        }
    }

    public static int getMAX_WIDTH() {
        return MAX_WIDTH;
    }

    public int getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }

    public int getComposite() {
        return composite;
    }

}
