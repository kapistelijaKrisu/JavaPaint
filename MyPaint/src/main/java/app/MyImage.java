package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import tools.TwoPoint;

/**
 *
 * Basically a BufferedImage Container with consistant graphics setting kept in
 * PaintBrush object. Use its own getgraphics method to be working on same
 * graphics over time.
 */
public class MyImage {

    private BufferedImage img;
    private Graphics2D graphics;
    private PaintBrush brush;

    /**
     *
     * Creates BufferedImage and sets its type to BufferedImage.TYPE_INT_ARGB
     * and creates graphics object of it to be used constantly.<br>
     * Sets static TwoPoint maximum limits accordingly to image size.<br>
     * Creates PaintBrush object which default settings are width = 1, override
     * = SRC, Color = Color.black. Sets settings from brush to graphics.
     *
     * @param width - width of image <br>
     * @param height - height of image<br>
     *
     * throws exception either of params if below 1.
     */
    public MyImage(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        TwoPoint.setBounds(width - 1, height - 1);
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = img.createGraphics();
        setBrush(new PaintBrush(1, true));
    }

    /**
     * reduces graphics draw stroke width by 1. <br>Will not go below 1.
     */
    public void thin() {
        brush.setWidth(brush.getWidth() - 1);
        graphics.setStroke(new BasicStroke(brush.getWidth()));
    }

    /**
     * increases graphics draw stroke width by 1.<br> Will not go above of max
     * width set in brush.
     */
    public void thicken() {
        brush.setWidth(brush.getWidth() + 1);
        graphics.setStroke(new BasicStroke(brush.getWidth()));
    }

    public void setColor(Color c) {
        brush.setColor(c);
        graphics.setColor(c);
    }

    /**
     *
     * @param override true = composite will be SRC. <br>false = composite will
     * be DST_OVER
     */
    public void setOverride(boolean override) {
        brush.setOverride(override);
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    /**
     *
     * @param brush sets brush to be current setting container and will also
     * install said settings from it to itself.
     */
    public void setBrush(PaintBrush brush) {
        this.brush = brush;
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    /**
     * Sets all settings from brush onto itself.
     */
    private void refreshSettings() {
        graphics = img.createGraphics();
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    /**
     *
     * @param img sets own image to this. Updates Area.class max values. Calls
     * refreshSettings() to set graphical settings from brush onto new img.
     * <br> throws exception if null
     */
    public void setImg(BufferedImage img) {
        this.img = img;
        refreshSettings();
        TwoPoint.setBounds(img.getWidth() - 1, img.getHeight() - 1);
    }

    /**
     *
     * @param width - setter that will not break limits from brush.
     */
    public void setBrushWidth(int width) {
        brush.setWidth(width);
        graphics.setStroke(new BasicStroke(brush.getWidth()));
    }

    public BufferedImage getImg() {
        return img;
    }

    public Graphics2D getGraphics() {
        return graphics;
    }

    public Color getColor() {
        return brush.getColor();
    }

    public int getBrushWidth() {
        return brush.getWidth();
    }

    public int getBrushComposite() {
        return brush.getComposite();
    }
}
