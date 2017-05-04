package app;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.TwoPoint;

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
    public void constructorTest() {
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
    public void paintSettingsTest() {
        Assert.assertTrue(Color.black == img.getColor());

        PaintBrush p = new PaintBrush(3, false);
        p.setColor(Color.yellow);
        img.setBrush(p);
        Assert.assertTrue(Color.yellow == img.getColor());
        img.getGraphics().draw(new Line2D.Float(1, 1, 1, 1));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assert.assertEquals(Color.yellow.getRGB(), img.getImg().getRGB(j, i));

            }

        }

        img.thicken();
        Assert.assertEquals(4, p.getWidth());

        img.thin();
        img.thin();
        Assert.assertEquals(2, p.getWidth());

    }

    @Test
    public void graphicsTest() {
        ControlUnit cu = new ControlUnit(5, 5);
        PaintBrush p = new PaintBrush(1, true);
        p.setColor(Color.yellow);
        cu.getImg().setBrush(p);
        cu.getImg().thicken();
        cu.getImg().thicken();
        TwoPoint a = new TwoPoint(1, 1);
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

        cu.getImg().setBrushWidth(10);
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

    }

    @Test
    public void resetGraphicsTest() {
        PaintBrush brush = new PaintBrush(2, false);
        brush.setColor(Color.yellow);
        img.setBrush(brush);

        BufferedImage dummy = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = dummy.createGraphics();
        g.setColor(Color.red);
        g.fillRect(0, 0, dummy.getWidth(), dummy.getHeight());
        img.setImg(dummy);

        for (int i = 0; i < img.getImg().getHeight(); i++) {
            for (int j = 0; j < img.getImg().getWidth(); j++) {
                Assert.assertEquals(Color.red.getRGB(), dummy.getRGB(j, i));
            }
        }

        TwoPoint a = new TwoPoint(33, 33);
        img.getGraphics().draw(new Line2D.Float(a.getPrevX(), a.getPrevY(), a.getCurX(), a.getCurY()));

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                Assert.assertNotEquals(Color.yellow.getRGB(), dummy.getRGB(j, i));
            }
        }

        img.setOverride(true);
        img.getGraphics().draw(new Line2D.Float(0, a.getPrevY(), a.getCurX(), a.getCurY()));
        for (int i = 30; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                Assert.assertEquals(Color.yellow.getRGB(), dummy.getRGB(j, i));

            }

        }

    }
    
    @Test
    public void getBrushWidthTest() {
        Assert.assertEquals(1, img.getBrushWidth());
        img.setBrushWidth(3);
        Assert.assertEquals(3, img.getBrushWidth());
    }
    
    @Test
    public void getCompositeTest() {

        
        Assert.assertEquals(AlphaComposite.SRC, img.getBrushComposite());
        img.setOverride(false);
        Assert.assertEquals(AlphaComposite.DST_OVER, img.getBrushComposite());
    }

}
