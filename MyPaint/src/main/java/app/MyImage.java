package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyImage {

    //  private ArrayDeque<BufferedImage> rewind;//later
    //  private ArrayDeque<BufferedImage> fastForward;//later
    private BufferedImage img;
    //BufferedImage toolLayer;
    private Graphics2D graphics;

    public MyImage(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        //     rewind = new ArrayDeque<>();
        //    fastForward = new ArrayDeque<>();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //  toolLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = img.createGraphics();

    }

    public BufferedImage getImg() {
        return img;
    }

    public Graphics2D getGraphics() {
        return graphics;
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
}
