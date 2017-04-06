package app;

import java.awt.AlphaComposite;
import java.awt.Color;

public final class PaintBrush {

    private static final int MAX_WIDTH = 30;

    private int width, composite;
    private Color color;

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
