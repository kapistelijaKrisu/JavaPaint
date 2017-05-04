package app;

import java.awt.AlphaComposite;
import java.awt.Color;

/**
 *
 * <p>
 * A container of graphical setting information.</p>
 * <p>
 * Holds width, composite, color values and assures their values are valid</p>
 */
public final class PaintBrush {

    private static final int MAXWIDTH = 20;

    private int width;
    private int composite;
    private Color color;

    /**
     *
     * @param width - minimum of 1 and maximum of 20 else throws
     * IllegalArgumentException <br>
     * @param override if true = src. false = dst_over see more at
     * alphacomposite
     */
    public PaintBrush(int width, boolean override) {
        if (!setWidth(width)) {
            throw new IllegalArgumentException();
        }
        setColor(Color.black);
        setWidth(width);
        setOverride(override);
    }

    /**
     *
     * @param color if null exception is thrown otherwise a normal setter
     */
    public void setColor(Color color) {
        if (color == null) {
            throw new NullPointerException();
        }
        this.color = color;
    }

    /**
     *
     * @param width - value to be checked between 1 and 20. If it is legal sets width else does nothing.<br>
     * @return true if width is legal
     */
    public boolean setWidth(int width) {
        if (width > 0 && width <= MAXWIDTH) {
            this.width = width;
            return true;
        }
        return false;
    }

    /**
     *
     * @param override if true = sets composite to AlphaComposite.SRC. false = sets composite to AlphaComposite.DST_OVER. 
     * 
     */
    public void setOverride(boolean override) {
        if (override) {
            this.composite = AlphaComposite.SRC;
        } else {
            this.composite = AlphaComposite.DST_OVER;
        }
    }

    public static int getMAX_WIDTH() {
        return MAXWIDTH;
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
