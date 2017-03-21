package app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import tools.BackGroundCreator;

public class MyImage {

  //  private ArrayDeque<BufferedImage> rewind;//later
  //  private ArrayDeque<BufferedImage> fastForward;//later
    BufferedImage img;
    BufferedImage bg;
    //BufferedImage toolLayer;
    Graphics2D graphics;

    public MyImage(int width, int height) {
   //     rewind = new ArrayDeque<>();
    //    fastForward = new ArrayDeque<>();
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      //  toolLayer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = img.createGraphics();
        bg = BackGroundCreator.create(width, height);
        
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

    public void draw(Graphics g, int xOffSet, int yOffSet) {
        g.drawImage(bg, xOffSet, yOffSet, null);
        g.drawImage(img, xOffSet, yOffSet, null);
    //    g.drawImage(toolLayer, 0, 0, null);
    }
}
