package app;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaintBrushTest {

    PaintBrush brush;
    Graphics2D testGraphics;

    @Before
    public void setUp() {
        BufferedImage testImg = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        testGraphics = (Graphics2D) testImg.createGraphics();
        System.out.println(testGraphics);
        brush = new PaintBrush(5, true);
    }



 /*   @Test
    public void testWidthInConstructor() {
        try {
            brush = new PaintBrush(testGraphics, 0, true);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        try {
            brush = new PaintBrush(testGraphics, PaintBrush.getMAX_WIDTH() + 1, true);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        brush = new PaintBrush(testGraphics, 1, true);
        Assert.assertTrue(brush.getWidth() == 1);

        brush = new PaintBrush(testGraphics, PaintBrush.getMAX_WIDTH(), true);
        Assert.assertTrue(brush.getWidth() == PaintBrush.getMAX_WIDTH());

        brush = new PaintBrush(testGraphics, 5, true);
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
    public void setGraphicsWorks() {
        Graphics2D g = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB).createGraphics();
        brush.setGraphics(g);
        Assert.assertTrue(brush.getGraphics().equals(g));

        try {
            brush.setGraphics(null);
            Assert.assertTrue(false);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
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
        brush.setCurrentColor(Color.BLACK);
        Assert.assertEquals(Color.BLACK, brush.getColor());

        brush.setCurrentColor(Color.white);
        Assert.assertEquals(Color.white, brush.getColor());

    }*/

}
