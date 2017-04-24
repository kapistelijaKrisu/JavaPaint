package app;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
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
    public void setColorWorks() {
        try {
            brush.setColor(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {
            
        }
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

}
