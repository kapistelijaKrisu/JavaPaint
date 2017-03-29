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

public class PaintBrushTest {
    BufferedImage testImg;
    PaintBrush brush;
    Graphics2D testGraphics;

    @Before
    public void setUp() {
        testImg = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        testGraphics = (Graphics2D) testImg.createGraphics();

        brush = new PaintBrush(5, true);
    }



    @Test
    public void testWidthInConstructor() {
        try {
            brush = new PaintBrush(0, true);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        try {
            brush = new PaintBrush(PaintBrush.getMAX_WIDTH() + 1, true);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        brush = new PaintBrush(1, true);
        Assert.assertTrue(brush.getWidth()== 1);

        brush = new PaintBrush(PaintBrush.getMAX_WIDTH(), true);
        Assert.assertTrue(brush.getWidth() == PaintBrush.getMAX_WIDTH());

        brush = new PaintBrush(5, true);
        Assert.assertTrue(brush.getWidth() == 5);

    }

    @Test
    public void setOverrideWorks() {
        brush.setOverride(true);
        Assert.assertTrue(brush.getComposite() == AlphaComposite.SRC);

        brush.setOverride(false);
        Assert.assertTrue(brush.getComposite() == AlphaComposite.DST_OVER);

        brush.setOverride(true);
        Assert.assertTrue(brush.getComposite() == AlphaComposite.SRC);

        brush.setOverride(false);
        Assert.assertTrue(brush.getComposite() == AlphaComposite.DST_OVER);
    }

   
    @Test
    public void setWidthWorks() {
        brush.setWidth(1);
        Assert.assertEquals(1, brush.getWidth());

        brush.setWidth(PaintBrush.getMAX_WIDTH());
        Assert.assertEquals(PaintBrush.getMAX_WIDTH(), brush.getWidth());

        brush.setWidth(PaintBrush.getMAX_WIDTH() + 1);
        Assert.assertEquals(PaintBrush.getMAX_WIDTH(), brush.getWidth());

        brush.setWidth(0);
        Assert.assertEquals(PaintBrush.getMAX_WIDTH(), brush.getWidth());

        brush.setWidth(-10);
        Assert.assertEquals(PaintBrush.getMAX_WIDTH(), brush.getWidth());
    }

    @Test
    public void setColorWorks() {
        brush.setColor(Color.BLACK);
        Assert.assertEquals(Color.BLACK, brush.getColor());

        brush.setColor(Color.white);
        Assert.assertEquals(Color.white, brush.getColor());

    }
    
    @Test
    public void installSettingWorks() {
        brush.setColor(Color.RED);
        brush.setOverride(false);
        brush.setWidth(3);
        
        brush.installSetting(testGraphics, true, true, true);
        Assert.assertEquals(Color.RED, testGraphics.getColor());
        testGraphics.draw(new Line2D.Float(1, 1, 5, 1));
        
        int val = 0;
        int[] rgbs = testImg.getRGB(0, 0, 5, 3, null, 0, 5);
        for (int i = 0; i < rgbs.length; i++) {
            val += rgbs[i];   
        }
        val /= rgbs.length;
        Assert.assertEquals(Color.RED.getRGB(), val);
        
        val = 0;
        int[] rgbs2 = testImg.getRGB(0, 0, 5, 5, null, 0, 5);
        for (int i = 0; i < rgbs2.length; i++) {
            val += rgbs2[i];   
        }
        Assert.assertFalse(Color.RED.getRGB() == val);
        
        Color c = new Color(0,0,0,0);
        testGraphics.setColor(c);
        val = 0;
        int[] rgbs3 = testImg.getRGB(0, 0, 5, 3, null, 0, 5);
        for (int i = 0; i < rgbs3.length; i++) {
            val += rgbs3[i];   
        }
        val /= rgbs.length;
        Assert.assertFalse(val == c.getRGB());
        
        brush.setOverride(true);
        brush.setColor(Color.BLUE);
        brush.installSetting(testGraphics, false, true, true);
        val = 0;
        testGraphics.draw(new Line2D.Float(1, 1, 5, 1));
        int[] rgbs4 = testImg.getRGB(0, 0, 5, 3, null, 0, 5);
        for (int i = 0; i < rgbs4.length; i++) {
            val += rgbs4[i];   
        }
        val /= rgbs.length;
        Assert.assertTrue(val == c.getRGB());
    }

}
