package app;

import app.cmd.CommandMap;
import java.awt.image.BufferedImage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class ControlUnitTest {

    ControlUnit cu;
    MyImage tImg;

    @Before
    public void SetUp() {
        cu = new ControlUnit();

    }

    @Test
    public void testNull() {
        try {
            cu.setImage(null);
            Assert.assertTrue(false);
        } catch (NullPointerException e) {

        }

    }

    @Test
    public void testSetImg() {
        int oldWidth = cu.getImg().getImg().getWidth();
        BufferedImage ttImg = new BufferedImage(10, 10, BufferedImage.TYPE_4BYTE_ABGR);
        cu.setImage(ttImg);
        Assert.assertNotEquals(ttImg, tImg);
        Assert.assertEquals(ttImg, cu.getImg().getImg());
        cu.undo();
        Assert.assertEquals(oldWidth, cu.getImg().getImg().getWidth());
    }

    @Test
    public void testParams() {
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 256);
        Assert.assertTrue(cu.getImg().getImg().getHeight() == 256);

        try {
            cu = new ControlUnit(0, 0);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu = new ControlUnit(1, 0);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu = new ControlUnit(0, 1);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        cu = new ControlUnit(1, 1);
        Area a = new Area(1, 1);
        Assert.assertTrue(a.getCurX() == 0);
        Assert.assertTrue(a.getCurY() == 0);

        Assert.assertTrue(cu.getImg().getImg().getHeight() == 1);
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 1);

        cu = new ControlUnit(10, 5);
        a = new Area(20, 20);
        Assert.assertTrue(a.getCurX() == 9);
        Assert.assertTrue(a.getCurY() == 4);

        Assert.assertTrue(cu.getImg().getImg().getHeight() == 5);
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 10);
    }

    @Test
    public void keysWork() {
        cu = new ControlUnit(12, 10);
        cu.setActiveCMD(CommandMap.DRAWRECT);
        Assert.assertEquals(CommandMap.DRAWRECT, cu.getCurrentCMD());

        cu.setActiveCMD(15);
        Assert.assertEquals(CommandMap.DRAWRECT, cu.getCurrentCMD());

        cu.setActiveCMD(CommandMap.FILLCOLOR);
        Assert.assertEquals(CommandMap.FILLCOLOR, cu.getCurrentCMD());
    }
    
    @Test
    public void logModeTest() {
        cu.execute(new Area(0, 0));
        Assert.assertEquals(1, cu.getLog().getHistorySize());
        cu.execute(new Area(0, 0));
        Assert.assertEquals(2, cu.getLog().getHistorySize());
        cu.setLogging(false);
        cu.execute(new Area(0, 0));
        Assert.assertEquals(2, cu.getLog().getHistorySize());
        cu.setLogging(true);
        cu.execute(new Area(0, 0));
        Assert.assertEquals(3, cu.getLog().getHistorySize());
    }
}
