package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class PaintBrush {

    private static final int MAX_WIDTH = 30;

    private int width, composite;
    private Color color;

    public PaintBrush(int width, boolean override) {
        if (!setWidth(width)) {
            throw new IllegalArgumentException();
        }
        setCurrentColor(Color.black);
        setOverride(override);
    }

    public void installSetting(Graphics2D target, boolean setColor, boolean setComposite, boolean setWidth) {
        if (setColor) {
            target.setColor(color);
        }
        if (setWidth) {
            target.setStroke(new BasicStroke(width));
        }
        if (setComposite) {
            target.setComposite(AlphaComposite.getInstance(composite, color.getAlpha() / 255f));
        }

    }

    public void setCurrentColor(Color color) {
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


}
