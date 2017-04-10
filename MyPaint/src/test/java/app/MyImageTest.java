package app;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class MyImageTest {

    MyImage img;
    int width;
    int height;

    @Before
    public void setUp() {
        width = 20;
        height = 10;
        img = new MyImage(width, height);
    }

    @Test
    public void constructorWorks() {
        Assert.assertTrue(img.getImg().getWidth() == width);
        Assert.assertTrue(img.getImg().getHeight() == height);

        Assert.assertTrue(img.getImg().getGraphics() != null);

        try {
            img = new MyImage(0, 1);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            img = new MyImage(1, 0);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        try {
            img = new MyImage(0, 0);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        img = new MyImage(1, 1);
        Assert.assertTrue(img.getImg().getWidth() == 1);
        Assert.assertTrue(img.getImg().getHeight() == 1);
    }

    @Test
    public void paintSettings() {
        Assert.assertTrue(Color.black == img.getColor());

        PaintBrush p = new PaintBrush(2, false);
        p.setColor(Color.yellow);
        img.setBrush(p);
        Assert.assertTrue(Color.yellow == img.getColor());

        img.thicken();
        Assert.assertEquals(3, p.getWidth());

        img.thin();
        img.thin();
        Assert.assertEquals(1, p.getWidth());

    }

    @Test
    public void graphicsTest() {
        ControlUnit cu = new ControlUnit();
        cu.init(5, 5);
        PaintBrush p = new PaintBrush(1, true);
        p.setColor(Color.yellow);
        cu.getImg().setBrush(p);
        cu.getImg().thicken();
        cu.getImg().thicken();
        Area a = new Area(1, 1);
        cu.execute(a);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assert.assertEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));

            }

        }
        for (int i = 3; i < 5; i++) {
            for (int j = 3; j < 5; j++) {
                Assert.assertNotEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(i, j));

            }

        }

        cu.getImg().thin();
        cu.getImg().thin();
        cu.getImg().setOverride(false);
        cu.getImg().setColor(Color.red);
        cu.execute(a);
        Assert.assertNotEquals(Color.red.getRGB(), cu.getImg().getImg().getRGB(1, 1));
        cu.getImg().setOverride(true);
        cu.execute(a);
        Assert.assertEquals(Color.red.getRGB(), cu.getImg().getImg().getRGB(1, 1));      
        Assert.assertNotEquals(Color.red.getRGB(), cu.getImg().getImg().getRGB(2, 1));
        
        
        cu.getImg().setWidth(10);
        cu.getImg().setColor(Color.BLUE);
        cu.execute(a);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Assert.assertEquals(Color.BLUE.getRGB(), cu.getImg().getImg().getRGB(i, j));

            }

        }
        
        
        PaintBrush b = new PaintBrush(1, false);
        b.setColor(Color.yellow);
        cu.getImg().setBrush(b);
        cu.execute(a);
        Assert.assertNotEquals(Color.yellow.getRGB(), cu.getImg().getImg().getRGB(1, 1));
        b.setOverride(true);
        cu.execute(a);
        Assert.assertEquals(Color.blue.getRGB(), cu.getImg().getImg().getRGB(2, 1));
        
   /*     p.setWidth(10);
        cu.getImg().setBrush(p);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Assert.assertEquals(Color.BLUE.getRGB(), cu.getImg().getImg().getRGB(i, j));

            }

        }*/
    }
    
    @Test
    public void resetGraphicsTestUndo() {
        PaintBrush brush = new PaintBrush(3, false);
        brush.setColor(Color.yellow);        
        img.setBrush(brush);
        
        img.getGraphics().draw(new Line2D.Float(0, 0,
                 1, 1));
        
        img.updateHistory();
        
        img.setColor(Color.red);
        img.setWidth(3);
        img.setOverride(true);
        
        img.undo();
        
       img.getGraphics().draw(new Line2D.Float(0, 0,
                 0, 0));
        
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(0, 0));
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(1, 0));
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(0, 1));
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(1, 1));
        
    }
    
    @Test
    public void resetGraphicsTestRedo() {
        PaintBrush brush = new PaintBrush(1, false);
        brush.setColor(Color.yellow);        
        img.setBrush(brush);
        
        img.getGraphics().draw(new Rectangle.Float(0, 0,
                 1, 1));
        
        img.updateHistory();
        
        img.setColor(Color.red);
        img.setWidth(3);
        img.setOverride(true);
        img.undo();
        img.redo();
        
       img.getGraphics().draw(new Line2D.Float(0, 0,
                 0, 0));
        
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(0, 0));
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(1, 0));
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(0, 1));
        Assert.assertEquals(Color.red.getRGB(), img.getImg().getRGB(1, 1));
        
    }
    
    @Test
    public void fuckthis() {
        PaintBrush brush = new PaintBrush(1, true);
        brush.setColor(Color.yellow);        
        img.setBrush(brush);
        
        img.getGraphics().draw(new Rectangle.Float(0, 0,
                 1, 1));
        
        img.updateHistory();
        
        img.setColor(Color.red);
        img.setWidth(3);
        img.setOverride(false);
        
        img.undo();
        
       img.getGraphics().draw(new Line2D.Float(0, 0,
                 0, 0));
        
        Assert.assertNotEquals(Color.red.getRGB(), img.getImg().getRGB(0, 0));
        Assert.assertNotEquals(Color.red.getRGB(), img.getImg().getRGB(1, 0));
        Assert.assertNotEquals(Color.red.getRGB(), img.getImg().getRGB(0, 1));
        Assert.assertNotEquals(Color.red.getRGB(), img.getImg().getRGB(1, 1));
    }

}
