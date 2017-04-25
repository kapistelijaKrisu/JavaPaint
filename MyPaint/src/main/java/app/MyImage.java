package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import tools.Area;
import tools.OneLineException;

/**
 *
 * <p>
 * Basically a BufferedImage with consistent graphics setting. PaintBrush object
 * contains graphical settings</p>
 */
public class MyImage {

    private BufferedImage img;
    private Graphics2D graphics;
    private PaintBrush brush;

    /**
     * <p>
     * creates BufferedImage and creates graphics object of it <br>
     * Sets Area.class limits accordingly.<br>
     * creates PaintBrush object which default settings are width = 1, override
     * = SRC, Color = black </p>
     * sets settings from brush to graphics
     * <p>
     * @param width - width of image <br>
     * @param height - height of image<br>
     *
     * throws exception if below 1</p>
     */
    public MyImage(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        Area.setBounds(width - 1, height - 1);
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
     * increases graphics draw stroke width by 1.<br> Will not go above of max width
     * set in brush.
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
     * @param override true = composite will be SRC. <br>false = composite will be
     * DST_OVER
     */
    public void setOverride(boolean override) {
        brush.setOverride(override);
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    /**
     *
     * @param width - will not break limits from brush
     */
    public void setWidth(int width) {
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

    /**
     *
     * @param brush sets brush to be current setting container and will also
     * install said settings to graphics.
     */
    public void setBrush(PaintBrush brush) {
        this.brush = brush;
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    /**
     * sets all settings from brush onto graphics
     */
    private void refreshSettings() {
        graphics = img.createGraphics();
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    /**
     *
     * @param img sets image to this. Updates Area.class max values. calls
     * refreshSettings().
     * <br> throws exception if null
     */
    public void setImg(BufferedImage img) {
        this.img = img;
        refreshSettings();
        Area.setBounds(img.getHeight() - 1, img.getWidth() - 1);
    }
}
