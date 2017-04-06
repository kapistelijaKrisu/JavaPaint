package app;

import app.cmd.CommandMap;
import app.cmd.DrawRect;
import java.awt.AlphaComposite;
import java.awt.Color;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tools.Area;

public class ControlUnitTest {

    ControlUnit cu;

    @Before
    public void SetUp() {
        cu = new ControlUnit();
    }

    @Test
    public void initWorks1() {
        Assert.assertFalse(cu.getInit());
        try {
            cu.run();
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu.init(0, 0);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu.init(1, 0);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
        try {
            cu.init(0, 1);
            Assert.assertFalse(true);
        } catch (IllegalStateException | IllegalArgumentException e) {
            Assert.assertTrue(true);
        }

        cu.init(1, 1);
        Area a = new Area(1, 1);
        Assert.assertTrue(a.getCurX() == 0);
        Assert.assertTrue(a.getCurY() == 0);

        Assert.assertTrue(cu.getImg().getImg().getHeight() == 1);
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 1);

        Assert.assertTrue(cu.getInit());

        cu.init(10, 5);
        a = new Area(20, 20);
        Assert.assertTrue(a.getCurX() == 9);
        Assert.assertTrue(a.getCurY() == 4);

        Assert.assertTrue(cu.getImg().getImg().getHeight() == 5);
        Assert.assertTrue(cu.getImg().getImg().getWidth() == 10);
        Assert.assertTrue(cu.getInit());
    }
    
    @Test
    public void keysWork() {
        cu.init(12, 10);
        cu.setActiveCMD(CommandMap.DRAWRECT);
        Assert.assertEquals(CommandMap.DRAWRECT, cu.getCurrentCMD());
        
        cu.setActiveCMD(15);
        Assert.assertEquals(CommandMap.DRAWRECT, cu.getCurrentCMD());
        
        cu.setActiveCMD(CommandMap.FILLCOLOR);
        Assert.assertEquals(CommandMap.FILLCOLOR, cu.getCurrentCMD());
    }
}
