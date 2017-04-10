package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * <p>
 * Basically a BufferedImage with added information and methods for easier
 * use</p>
 */
public class MyImage {

    private Log log;
    private BufferedImage img;
    private Graphics2D graphics;
    private PaintBrush brush;

    public MyImage(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        log = new Log();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = img.createGraphics();
        setBrush(new PaintBrush(1, true));
    }

    public void thin() {
        brush.setWidth(brush.getWidth() - 1);
        graphics.setStroke(new BasicStroke(brush.getWidth()));
    }

    public void thicken() {
        brush.setWidth(brush.getWidth() + 1);
        graphics.setStroke(new BasicStroke(brush.getWidth()));
    }

    public void setColor(Color c) {
        brush.setColor(c);
        graphics.setColor(brush.getColor());
    }

    public void setOverride(boolean override) {
        brush.setOverride(override);
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

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

    public void setBrush(PaintBrush brush) {
        this.brush = brush;
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    public void updateHistory() {
        log.addStep(img);
    }

    public void undo() {
        BufferedImage prev = log.getPrevious(img);
        if (prev != null) {
            img = prev;
            refreshGraphics();
        }
    }

    public void redo() {
        BufferedImage next = log.getNext(img);
        if (next != null) {
            img = next;
            refreshGraphics();
        }
    }

    private void refreshGraphics() {
        graphics = img.createGraphics();
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

    public Log getLog() {
        return log;
    }

    
}
