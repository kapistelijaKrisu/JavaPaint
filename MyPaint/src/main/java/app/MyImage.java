package app;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyImage {

    //  private ArrayDeque<BufferedImage> rewind;//later
    //  private ArrayDeque<BufferedImage> fastForward;//later
    private BufferedImage img;
    //BufferedImage toolLayer;
    private Graphics2D graphics;
    private PaintBrush brush;

    public MyImage(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        //     rewind = new ArrayDeque<>();
        //    fastForward = new ArrayDeque<>();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //  toolLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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

    /* completed on later date 
    
    public void saveProcess() {
        rewind.add(copyImage(img));
    }

    public void rewind() {
        if (!rewind.isEmpty()) {
            fastForward.add(img);
            img = rewind.pop();
        }
    }

    public void fastForward() {
        if (!fastForward.isEmpty()) {
            rewind.add(img);
            img = rewind.poll();
        }
    }

    private BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics2D g = b.createGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }*/
    public void setBrush(PaintBrush brush) {
        this.brush = brush;
        graphics.setColor(brush.getColor());
        graphics.setStroke(new BasicStroke(brush.getWidth()));
        graphics.setComposite(AlphaComposite.getInstance(brush.getComposite(), brush.getColor().getAlpha() / 255f));
    }

}
