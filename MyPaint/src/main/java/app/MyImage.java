package app;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyImage {

  //  private ArrayDeque<BufferedImage> rewind;//later
  //  private ArrayDeque<BufferedImage> fastForward;//later
    BufferedImage img;

    public MyImage(int width, int height) {
   //     rewind = new ArrayDeque<>();
    //    fastForward = new ArrayDeque<>();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = img.createGraphics();

        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, img.getWidth(), img.getHeight());

    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
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
